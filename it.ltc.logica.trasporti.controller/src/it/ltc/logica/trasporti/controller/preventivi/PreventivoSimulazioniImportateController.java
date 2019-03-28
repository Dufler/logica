package it.ltc.logica.trasporti.controller.preventivi;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.ServizioCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class PreventivoSimulazioniImportateController extends PreventivoController {
	
	private static PreventivoSimulazioniImportateController instance;
	
	private final ListiniSimulazioneController controllerListiniSimulazione;
	private final ControllerAmbitiFatturazione controllerAmbiti;
	
	//Criteri di selezione delle spedizioni
	private Date da;
	private Date a;
	private Corriere corriere;
	private CodiceClienteCorriere codiceCliente;
	private ServizioCorriere servizio;
	private TipoSpedizione tipoSpedizione;
	private Boolean contrassegno;
	
	private Integer idDocumento;
	
	private boolean pezziNecessari;
	private boolean colliNecessari;
	private boolean pesoNecessario;
	private boolean volumeNecassario;
	
	private final List<SpedizioneSimulazione> listaSpedizioniSelezionate;
	
	private PreventivoSimulazioniImportateController() {
		listaSpedizioniSelezionate = new ArrayList<SpedizioneSimulazione>();
		controllerListiniSimulazione = ListiniSimulazioneController.getInstance();
		controllerAmbiti = ControllerAmbitiFatturazione.getInstance();
	}
	
	public static PreventivoSimulazioniImportateController getInstance() {
		if (instance == null) {
			instance = new PreventivoSimulazioniImportateController();
		}
		return instance;
	}
	
	/**
	 * Metodo da chiamare solo dentro il wizard per resettare eventuali dati sporchi rimasti da precedenti utilizzi.
	 * @return una nuova istanza vuota.
	 */
	public static PreventivoSimulazioniImportateController resetInstance() {
		instance = new PreventivoSimulazioniImportateController();
		return instance;
	}
	
	public List<SpedizioneSimulazione> getListaSpedizioniSelezionate() {
		return listaSpedizioniSelezionate;
	}

	public void setSpedizioniSelezionate(Object[] spedizioniSelezionate) {
		listaSpedizioniSelezionate.clear();
		for (Object o : spedizioniSelezionate) {
			SpedizioneSimulazione spedizione = (SpedizioneSimulazione) o;
			listaSpedizioniSelezionate.add(spedizione);
		}
	}

	public void setDataDa(Date da) {
		this.da = da;		
	}
	
	public void setDataA(Date a) {
		this.a = a;		
	}

	public Date getDataDa() {
		return da;
	}
	
	public Date getDataA() {
		return a;
	}

	public Corriere getCorriere() {
		return corriere;
	}

	public void setCorriere(Corriere corriere) {
		this.corriere = corriere;
	}
	
	public CodiceClienteCorriere getCodice() {
		return codiceCliente;
	}
	
	public void setCodice(CodiceClienteCorriere codiceCliente) {
		this.codiceCliente = codiceCliente;		
	}

	public ServizioCorriere getServizio() {
		return servizio;
	}

	public void setServizio(ServizioCorriere servizio) {
		this.servizio = servizio;
	}
	
	public TipoSpedizione getTipoSpedizione() {
		return tipoSpedizione;
	}
	
	public void setTipoSpedizione(TipoSpedizione tipo) {
		this.tipoSpedizione = tipo;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}
	
	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public boolean isPezziNecessari() {
		return pezziNecessari;
	}

	public void setPezziNecessari(boolean pezziNecessari) {
		this.pezziNecessari = pezziNecessari;
	}

	public boolean isColliNecessari() {
		return colliNecessari;
	}

	public void setColliNecessari(boolean colliNecessari) {
		this.colliNecessari = colliNecessari;
	}

	public boolean isPesoNecessario() {
		return pesoNecessario;
	}

	public void setPesoNecessario(boolean pesoNecessario) {
		this.pesoNecessario = pesoNecessario;
	}

	public boolean isVolumeNecassario() {
		return volumeNecassario;
	}

	public void setVolumeNecassario(boolean volumeNecassario) {
		this.volumeNecassario = volumeNecassario;
	}
	
	private class ProcessoCalcolo extends Processo {
		
		private static final String title = "Calcolo del preventivo";
		
		private EsitoSimulazione simulazione;

		public ProcessoCalcolo() {
			super(title, listaSpedizioniSelezionate.size());
		}
		
		public EsitoSimulazione getSimulazione() {
			return simulazione;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			int spedizioni = 0;
			int colli = 0;
			int pezzi = 0;
			double totaleCosti = 0;
			boolean costiPresenti = true;
			//Carico e calcolo le singole spedizioni
			List<SpedizioneModel> calcoli = new LinkedList<SpedizioneModel>();
			for (SpedizioneSimulazione spedizione  : listaSpedizioniSelezionate) {
				SpedizioneModel model = SpedizioneModel.caricaSpedizioneSimulazione(spedizione);
				calcoli.add(model);
				spedizioni += 1;
				Spedizione s = model.getSpedizione();
				colli += s.getColli();
				pezzi += s.getPezzi();
				if (s.getCosto() != null) {
					totaleCosti += s.getCosto();
				} else {
					costiPresenti = false;
				}
				calcola(model);
				aumentaProgresso(1);
			}
			//Calcolo i totali: listini cliente
			List<TotaleListino> listaTotali = new LinkedList<TotaleListino>();
			for (ListinoCommessa listino : listiniCliente) {
				String nome = listino.getNome();
				AmbitoFattura ambito = controllerAmbiti.getAmbito(listino.getTipo());
				String tipoListino = ambito != null ? ambito.getNome() : "-";
				double[] totali = getTotali(calcoli, listino.getId());
				TotaleListino totaleListino = new TotaleListino(listino.getId(), Scopo.RICAVO);
				totaleListino.setNome(nome);
				totaleListino.setTipoListino(tipoListino);
				totaleListino.setTotale(totali[0]);
				totaleListino.setNolo(totali[1]);
				totaleListino.setContrassegno(totali[2]);
				totaleListino.setExtra(totali[3]);
				listaTotali.add(totaleListino);
			}
			//Calcolo i totali: listini corriere
			for (ListinoCorriere listino : listiniCorriere) {
				String nome = listino.getNome();
				AmbitoFattura ambito = controllerAmbiti.getAmbito(listino.getTipo());
				String tipoListino = ambito != null ? ambito.getNome() : "-";
				double[] totali = getTotali(calcoli, listino.getId());
				TotaleListino totaleListino = new TotaleListino(listino.getId(), Scopo.COSTO);
				totaleListino.setNome(nome);
				totaleListino.setTipoListino(tipoListino);
				totaleListino.setTotale(totali[0]);
				totaleListino.setNolo(totali[1]);
				totaleListino.setContrassegno(totali[2]);
				totaleListino.setExtra(totali[3]);
				listaTotali.add(totaleListino);
			}
			//Calcolo i totali: listini di simulazione
			for (ListinoSimulazione listino : listiniSimulazione) {
				String nome = listino.getNome();
				AmbitoFattura ambito = controllerAmbiti.getAmbito(listino.getTipo());
				String tipoListino = ambito != null ? ambito.getNome() : "-";
				double[] totali = getTotali(calcoli, listino.getId());
				TotaleListino totaleListino = new TotaleListino(listino.getId(), controllerListiniSimulazione.getScopo(listino.getId()));
				totaleListino.setNome(nome);
				totaleListino.setTipoListino(tipoListino);
				totaleListino.setTotale(totali[0]);
				totaleListino.setNolo(totali[1]);
				totaleListino.setContrassegno(totali[2]);
				totaleListino.setExtra(totali[3]);
				listaTotali.add(totaleListino);
			}
			//Marco i totali di interesse
			trovaTotaliMigliori(listaTotali);
			simulazione = new EsitoSimulazione(colli, pezzi, spedizioni, totaleCosti, calcoli, listaTotali);
			simulazione.setCostiPresenti(costiPresenti);
		}
		
	}
	
	public EsitoSimulazione calcola() {
		ProcessoCalcolo processo = new ProcessoCalcolo();
		DialogProgresso dialog = new DialogProgresso("Calcolo dei preventivi");
		dialog.esegui(processo);
		EsitoSimulazione esito = processo.getSimulazione();
		return esito;
	}

	private void trovaTotaliMigliori(List<TotaleListino> listaTotali) {
		TotaleListino costoMinore = null;
		TotaleListino ricavoMaggiore = null;
		for (TotaleListino totale : listaTotali) {
			if (totale.getScopo() == Scopo.COSTO) {
				if (costoMinore == null || costoMinore.getTotale() > totale.getTotale()) {
					costoMinore = totale;
				}
			} else if (totale.getScopo() == Scopo.RICAVO) {
				if (ricavoMaggiore == null || ricavoMaggiore.getTotale() < totale.getTotale()) {
					ricavoMaggiore = totale;
				}
			}
		}
		//Segno i migliori trovati, se trovati
		if (costoMinore != null)
			costoMinore.setCostoMinore(true);
		if (ricavoMaggiore != null)
			ricavoMaggiore.setRicavoMaggiore(true);
	}

	/**
	 * Restituisce un array di double contenente i totali dei calcoli fatti per il listino.
	 * 0 - il totale
	 * 1 - il totale del nolo
	 * 2 - il totale dei contrassegni
	 * 3 - il totale degli extra
	 * @param calcoli
	 * @param idListino
	 * @return
	 */
	private double[] getTotali(List<SpedizioneModel> calcoli, Integer idListino) {
		double[] totali = new double[4];
		for (SpedizioneModel model : calcoli) {
			//Calcolo i listini clienti
			Calcolo calcolo = model.getCalcolo(idListino);
			for (VoceCalcolata voce : calcolo.getVoci()) {
				totali[0] += voce.getCosto();
				switch (voce.getCategoriaAmbito()) {
					case "NOLO_BASE" : totali[1] += voce.getCosto(); break;
					case "NOLO_EXTRA" : totali[1] += voce.getCosto(); break;
					case "CONTRASSEGNO" : totali[2] += voce.getCosto(); break;
					default : totali[3] += voce.getCosto(); break;
				}
			}
		}
		return totali;
	}

}

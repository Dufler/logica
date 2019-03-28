package it.ltc.logica.trasporti.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.fatturazione.FatturazioneController;
import it.ltc.logica.common.controller.processi.ProcessoInserimentoDati;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.logica.trasporti.calcolo.algoritmi.CalcolatoreTrasporti;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.calcolo.algoritmi.VoceDiListinoTrasporti;
import it.ltc.logica.trasporti.calcolo.ambiti.trasporti.FactoryAmbitiTrasporti;
import it.ltc.logica.utilities.excel.FileExcel;

public class FatturazioneSpedizioniController extends FatturazioneController {
	
	private static FatturazioneSpedizioniController instance;
	
	private final ControllerSpedizioni controllerSpedizioni;
	
	private final HashMap<String, ListinoCommessa> listiniTrasporti;
	private final HashMap<String, List<SpedizioneModel>> modelTrasporti;
	
	private ListinoCommessa listinoSelezionato;
	private CodiceClienteCorriere codiceSelezionato;
	private TipoSpedizione tipoSelezionato;
	
	private FatturazioneSpedizioniController() {
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		listiniTrasporti = new HashMap<String, ListinoCommessa>();
		modelTrasporti = new HashMap<String, List<SpedizioneModel>>();
	}

	public static FatturazioneSpedizioniController getInstance() {
		if (instance == null) {
			instance = new FatturazioneSpedizioniController();
		}
		return instance;
	}
	
	public void setListinoSelezionato(ListinoCommessa listino) {
		this.listinoSelezionato = listino;
	}
	
	public ListinoCommessa getListinoSelezionato() {
		return listinoSelezionato;
	}
	
	public void setCodiceClienteCorriereSelezionato(CodiceClienteCorriere codice) {
		this.codiceSelezionato = codice;
	}
	
	public void setTipoSpedizioniSelezionato(TipoSpedizione tipo) {
		this.tipoSelezionato = tipo;
	}
	
	public HashMap<String, List<SpedizioneModel>> getSpedizioniFatturate() {
		return modelTrasporti;
	}
	
//	public List<ListinoCommessa> getListiniTrasportiUtilizzati() {
//		List<ListinoCommessa> listiniUtilizzati = new ArrayList<ListinoCommessa>();
//		for (String codice : listiniTrasporti.keySet()) {
//			ListinoCommessa listino = listiniTrasporti.get(codice);
//			listiniUtilizzati.add(listino);
//		}
//		return listiniUtilizzati;
//	}
	
	private List<Spedizione> getSpedizioni() {
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setFatturazione(Spedizione.Fatturazione.FATTURABILE);
		criteri.setCommessa(commessa.getId());
		if (codiceSelezionato != null)
			criteri.setCodiceCliente(codiceSelezionato.getCodiceCliente());
		criteri.setDataDa(inizio);
		criteri.setDataA(fine);
		if (tipoSelezionato != null)
			criteri.setTipo(tipoSelezionato);
//		criteri.setTipo(TipoSpedizione.ITALIA);
		List<Spedizione> listaSpedizioni = controllerSpedizioni.selezionaSpedizioni(criteri);
		return listaSpedizioni;
	}
	
	public List<Spedizione> getSpedizioniNonAncoraFatturabili() {
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setFatturazione(Spedizione.Fatturazione.IN_DEFINIZIONE);
		criteri.setCommessa(commessa.getId());
		if (codiceSelezionato != null)
			criteri.setCodiceCliente(codiceSelezionato.getCodiceCliente());
		criteri.setDataDa(inizio);
		criteri.setDataA(fine);
		if (tipoSelezionato != null)
			criteri.setTipo(tipoSelezionato);
//		criteri.setTipo(TipoSpedizione.ITALIA);
		List<Spedizione> listaSpedizioni = controllerSpedizioni.selezionaSpedizioni(criteri);
		return listaSpedizioni;
	}
	
//	private ListinoCommessa getListinoTrasporti(String codiceCliente) {
//		if (!listiniTrasporti.containsKey(codiceCliente)) {
//			CodiceClienteCorriere codice = ControllerCodiciClienteCorriere.getInstance().getCodiceCliente(codiceCliente);
//			if (codice != null) {
//				ListinoCommessa listino = controllerListini.getListinoPerAmbitoECliente(AmbitoFattura.ID_SPEDIZIONI, codice.getCommessa());
//				if (listino != null)
//					listiniTrasporti.put(codiceCliente, listino);
//			} else {
//				String messaggio = MESSAGGIO_ERRORE_CODICE_NON_TROVATO.replace("[codice]", codiceCliente);
//				DialogMessaggio.openError(TITOLO_ERRORE_CODICE_NON_TROVATO, messaggio);
//			}
//		}
//		return listiniTrasporti.get(codiceCliente);
//	}
	
	private List<SpedizioneModel> getListaSpedizioniTrasporti(String codiceCliente) {
		if (!modelTrasporti.containsKey(codiceCliente)) {
			ArrayList<SpedizioneModel> lista = new ArrayList<SpedizioneModel>();
			modelTrasporti.put(codiceCliente, lista);
		}
		return modelTrasporti.get(codiceCliente);
	}
	
	public void ricalcolaSpedizione(SpedizioneModel spedizione) {
		spedizione.resettaCalcoli();
//		String codiceCliente = spedizione.getSpedizione().getCodiceCliente();
		CalcolatoreTrasporti.getInstance().calcolaRicavo(spedizione, listinoSelezionato);
//		ListinoCommessa listinoCommessa = getListinoTrasporti(codiceCliente);
//		CalcolatoreTrasporti.getInstance().calcolaRicavo(spedizione, listinoCommessa);
	}
	
	protected class ProcessoCalcolo extends Processo {
		
		private static final String title = "Calcolo della fatturazione trasporti";
		
		private final List<Spedizione> spedizioni;

		public ProcessoCalcolo(List<Spedizione> spedizioni) {
			super(title, spedizioni.size());
			this.spedizioni = spedizioni;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			CalcolatoreTrasporti calcolatore = CalcolatoreTrasporti.getInstance();
			listiniTrasporti.clear();
			modelTrasporti.clear();
			for (Spedizione spedizione : spedizioni) {
//				String codiceCliente = spedizione.getCodiceCliente();
//				ListinoCommessa listinoCommessa = getListinoTrasporti(codiceCliente);
//				if (listinoCommessa == null) {
//					throwListinoMancanteException(codiceCliente);
//				}
//				List<SpedizioneModel> listaModel = getListaSpedizioniTrasporti(codiceCliente);
//				SpedizioneModel model = SpedizioneModel.caricaSpedizione(spedizione);
//				calcolatore.calcolaRicavo(model, listinoCommessa);
//				listaModel.add(model);
//				aumentaProgresso(1);
				String codiceCliente = spedizione.getCodiceCliente();
				if (listinoSelezionato == null) {
					throwListinoMancanteException(codiceCliente);
				}
				List<SpedizioneModel> listaModel = getListaSpedizioniTrasporti(codiceCliente);
				SpedizioneModel model = SpedizioneModel.caricaSpedizione(spedizione);
				calcolatore.calcolaRicavo(model, listinoSelezionato);
				listaModel.add(model);
				aumentaProgresso(1);
			}
		}
		
	}
	
	public void calcolaFatturazioneTrasporti() {
		List<Spedizione> spedizioni = getSpedizioni();
		ProcessoCalcolo processo = new ProcessoCalcolo(spedizioni);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
	}
	
	/**
	 * Crea e lancia una eccezione per mostrare all'utente il fatto che manchi un listino appropriato alla fatturazione.
	 * @param codiceCliente il codice cliente per cui non è ancora stato inserito un listino.
	 * @throws InvocationTargetException L'eccezione incaricata di mostrare il messaggio.
	 */
	private void throwListinoMancanteException(String codiceCliente) throws InvocationTargetException {
		String message = "Inserire ad associare un listino per il codice cliente: " + codiceCliente;
		RuntimeException re = new RuntimeException("Listino Mancante");
		InvocationTargetException ite = new InvocationTargetException(re, message);
		throw ite;
	}
	
	private String getNomeFile(String path) {
		String tipoFile = "Fatturazione";
		String nomeCommessa = commessa.getNome();
		String periodo = meseEAnno.format(inizio);
		String estensione = ".xls";
		String nomeFile = path  + "\\"  + tipoFile + " " + nomeCommessa + " " + periodo + estensione;
		return nomeFile;
	}
	
	public boolean salvaDatiTrasporti() {
		boolean salvataggio;
		//Preparo le entity da salvare
		int idDocumento = getDocumentoFatturazione(commessa.getId(), AmbitoFattura.ID_SPEDIZIONI, inizio);
		if (idDocumento != -1) {
			List<ElementoFatturazioneJSON> elementi = new LinkedList<>();
			for (String codice : modelTrasporti.keySet()) {
				List<SpedizioneModel> spedizioni = modelTrasporti.get(codice);
				for(SpedizioneModel spedizione : spedizioni) {
					//Se è stata annullata passo alla successiva.
					if (spedizione.isFatturazioneAnnullata())
						continue;
					int idSpedizione = spedizione.getSpedizione().getId();
					Calcolo preventivo = spedizione.getPreventivoRicavo();
					List<VoceFattura> voci = new LinkedList<>();
					for (VoceCalcolata voce : preventivo.getVoci()) {
						//Voce di fatturazione
						VoceFattura nuovaVoce = new VoceFattura();
						nuovaVoce.setIdAmbito(AmbitoFattura.ID_SPEDIZIONI);
						nuovaVoce.setIdSottoAmbito(voce.getIdAmbito());
						nuovaVoce.setIdCommessa(commessa.getId());
						nuovaVoce.setIdDocumento(idDocumento);
						nuovaVoce.setIdListino(preventivo.getIdListino());
						nuovaVoce.setIdRiferimento(idSpedizione);
						nuovaVoce.setIdVoce(voce.getIdVoce());
						nuovaVoce.setImporto(voce.getCosto());
						voci.add(nuovaVoce);
					}
					ElementoFatturazioneJSON elemento = new ElementoFatturazioneJSON();
					elemento.setAmbito(AmbitoFattura.ID_SPEDIZIONI);
					elemento.setRiferimento(idSpedizione);
					elemento.setTotale(preventivo.getTotale());
					elemento.setVoci(voci);
					elementi.add(elemento);
				}
			}
			//Chiamo il ws per inserire tutti gli elementi di fatturazione
			ProcessoInserimentoElementiFatturazione processo = new ProcessoInserimentoElementiFatturazione(elementi);
			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
			dialog.esegui(processo);
			salvataggio = processo.getEsito();
		} else {
			salvataggio = false;
		}
		return salvataggio;
	}

	public void esportaDatiTrasporti_excel(String path) {
		CalcolatoreTrasporti calcolatore = CalcolatoreTrasporti.getInstance();
		String nomeFile = getNomeFile(path);
		FileExcel workbook = FileExcel.getFileExcel(nomeFile);
		boolean successo = false;
		if (workbook != null && !modelTrasporti.isEmpty()) {
			for (String codice : modelTrasporti.keySet()) {
				workbook.creaFoglio(codice);
				//Titolo - riga 1
				workbook.aggiungiTitolo(codice, 0, 0, commessa.getNome() + " - " + codice);
				//Sotto titoli - riga 2
				int colonnaSottoTitoli = 0;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Data");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Riferimento");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Destinatario");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Localita'");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Provincia");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Colli");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Pezzi");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Peso");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Volume");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Contrassegno");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Totale");
				colonnaSottoTitoli += 1;
				workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, "Nolo");
				//colonnaSottoTitoli += 1;
//				ListinoCommessa listinoCommessa = getListinoTrasporti(codice);
//				List<VoceDiListinoTrasporti> voci = calcolatore.getTutteLeVociDiListino(listinoCommessa.getId());
				List<VoceDiListinoTrasporti> voci = calcolatore.getTutteLeVociDiListino(listinoSelezionato.getId());
				HashMap<Integer, Integer> colonneVoci = new HashMap<Integer, Integer>();
				for (VoceDiListinoTrasporti voce : voci) {
					if (!(FactoryAmbitiTrasporti.getInstance().isAmbitoNoloBase(voce.getAmbito().getId()))) {
						colonnaSottoTitoli += 1;
						workbook.aggiungiSottoTitolo(codice, colonnaSottoTitoli, 1, voce.getNome());
						colonneVoci.put(voce.getIdVoce(), colonnaSottoTitoli);
					}
				}
				//Spedizioni - righe successive
				int riga = 1;
				List<SpedizioneModel> spedizioni = modelTrasporti.get(codice);
				for(SpedizioneModel spedizione : spedizioni) {
					riga += 1;
					workbook.aggiungiTesto(codice, 0, riga, semplice.format(spedizione.getSpedizione().getDataPartenza()));
					workbook.aggiungiTesto(codice, 1, riga, spedizione.getSpedizione().getRiferimentoCliente());
					workbook.aggiungiTesto(codice, 2, riga, spedizione.getDestinazione().getRagioneSociale());
					workbook.aggiungiTesto(codice, 3, riga, spedizione.getDestinazione().getLocalita());
					workbook.aggiungiTesto(codice, 4, riga, spedizione.getDestinazione().getProvincia());
					workbook.aggiungiInteger(codice, 5, riga, spedizione.getSpedizione().getColli());
					workbook.aggiungiInteger(codice, 6, riga, spedizione.getSpedizione().getPezzi());
					workbook.aggiungiDouble(codice, 7, riga, spedizione.getSpedizione().getPeso());
					workbook.aggiungiDouble(codice, 8, riga, spedizione.getSpedizione().getVolume());
					if (spedizione.getContrassegno() != null) {
						workbook.aggiungiValutaDueCifre(codice, 9, riga, spedizione.getContrassegno().getValore());
					} else {
						//workbook.aggiungiTesto(codice, 7, riga, "-");
					}
					Calcolo preventivo = spedizione.getPreventivoRicavo();
					workbook.aggiungiValutaTreCifre(codice, 10, riga, preventivo.getTotale());
					workbook.aggiungiValutaTreCifre(codice, 11, riga, spedizione.getCostoNoloBase(Scopo.RICAVO));
					for (VoceCalcolata voce : preventivo.getVoci()) {
						Integer colonna = colonneVoci.get(voce.getIdVoce());
						if (colonna != null)
							workbook.aggiungiValutaTreCifre(codice, colonna, riga, voce.getCosto());
					}
				}
			}		
			successo = workbook.salvaEChiudi();
		}
		if (successo) {
			DialogMessaggio.openInformation("Esportazione completata", "Esportazione completata!\r\nE' stato generato il file:\r\n" + nomeFile);
		} else {
			DialogMessaggio.openError("Errore nella esportazione", "Non \u00E8 stato possibile esportare i risultati.");
		}
	}
	
	private class ProcessoInserimentoElementiFatturazione extends ProcessoInserimentoDati<List<ElementoFatturazioneJSON>> {
		
		private final static String title = "Salvataggio dati fatturazione";
		private final static String resource = "vocifatturazione/salvafattura";

		public ProcessoInserimentoElementiFatturazione(List<ElementoFatturazioneJSON> object) {
			super(title, resource, object, false, null, null, null);
		}
		
	}

	public boolean aggiornaStatoFatturabili(List<Spedizione> spedizioniSelezionate) {
		ProcessoAggiornamentoSpedizioniNonFatturabili processo = new ProcessoAggiornamentoSpedizioniNonFatturabili(spedizioniSelezionate);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.isSuccesso();
	}
	
	private class ProcessoAggiornamentoSpedizioniNonFatturabili extends Processo {
		
		private final static String title = "Aggiornamento dati fatturazione";
		
		private final List<Spedizione> spedizioni;
		
		private boolean successo;

		public ProcessoAggiornamentoSpedizioniNonFatturabili(List<Spedizione> spedizioni) {
			super(title, spedizioni.size());
			this.spedizioni = spedizioni;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			successo = true;
			for (Spedizione spedizione : spedizioni) {
				spedizione.setFatturazione(Fatturazione.FATTURABILE);
				boolean update = controllerSpedizioni.aggiornaSenzaProgressDialog(spedizione);
				if (!update)
					successo = false;
				aumentaProgresso(1);
			}			
		}
		
		public boolean isSuccesso() {
			return successo;
		}
		
	}
	
}

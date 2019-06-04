package it.ltc.logica.common.controller.listini;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceFissa;
import it.ltc.logica.common.calcolo.algoritmi.MVocePercentuale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioniRipetuti;
import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.processi.ProcessoAggiornamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCancellazioneDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.common.controller.processi.ProcessoInserimentoDati;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoFissa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoPercentuale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoProporzionale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioni;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioniRipetuti;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.utilities.excel.FileExcel;

/**
 * Controller adibito alla gestione dei listini dei clienti LTC.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ControllerListiniClienti {
	
	private static final String titleListini = "Listini Cliente";
	private static final String resourceListini = "listinocliente";
	private static final String titleVoci = "Voci di Listino";
	private static final String resourceVoci = "vocilistinocliente";
	
	private static ControllerListiniClienti instance;
	
	private final HashMap<Integer, ListinoCommessa> listini;
	private final HashMap<Integer, List<VoceDiListino>> mappaListinoEVoci;
	private final HashMap<Integer, VoceDiListino> vociDiListino;

	private ControllerListiniClienti() {
		listini = new HashMap<Integer, ListinoCommessa>();
		vociDiListino = new HashMap<Integer, VoceDiListino>();
		mappaListinoEVoci = new HashMap<Integer, List<VoceDiListino>>();
		caricaListini();
	}

	public static ControllerListiniClienti getInstance() {
		if (instance == null) {
			instance = new ControllerListiniClienti();
		}
		return instance;
	}
	
	/**
	 * Metodo sincronizzato per il caricamento delle informazioni sui listini.
	 */
	public synchronized void caricaListini() {
		ProcessoCaricamentoListini pcl = new ProcessoCaricamentoListini();
		DialogProgresso dialogL = new DialogProgresso("Caricamento Listini", false);
		dialogL.esegui(pcl);
		List<ListinoCommessa> listL = pcl.getLista();
		for (ListinoCommessa listino : listL) {
			listini.put(listino.getId(), listino);
			mappaListinoEVoci.put(listino.getId(), new LinkedList<VoceDiListino>());
		}
		
		ProcessoCaricamentoVociDiListino pcvl = new ProcessoCaricamentoVociDiListino();
		DialogProgresso dialogVL = new DialogProgresso("Caricamento delle Listini", false);
		dialogVL.esegui(pcvl);
		List<VoceDiListino> listVL = pcvl.getLista();
		for (VoceDiListino voce : listVL) {
			vociDiListino.put(voce.getId(), voce);
			List<VoceDiListino> listaVoci = mappaListinoEVoci.get(voce.getIdListino());
			listaVoci.add(voce);
		}
	}
	
	/**
	 * Restituisce tutti i listini dei clienti LTC.
	 * @return Una collezione di listini.
	 */
	public Collection<ListinoCommessa> getListini() {
		return listini.values();
	}
	
	/**
	 * Restituisce tutti i listini collegati ad un particolare cliente.
	 * @param commessa La commessa di cui si vuole avere i listini.
	 * @return Una collezione di listini appartenenti al cliente specificato.
	 */
	public List<ListinoCommessa> getListiniDaCliente(Commessa commessa) {
		List<ListinoCommessa> listiniPerCommessa = new LinkedList<ListinoCommessa>();
		if (commessa != null) {
			for (Integer idListino : listini.keySet()) {
				ListinoCommessa listino = listini.get(idListino);
				if (listino.getIdCommessa().equals(commessa.getId()))
					listiniPerCommessa.add(listino);
			}
		}
		return listiniPerCommessa;
	}
	
	/**
	 * Restituisce tutti i listini cliente di un certo tipo.
	 * @param tipo Il tipo di listino
	 * @return Una collezione di listini del tipo specificato.
	 */
	public List<ListinoCommessa> getListiniPerTipo(Integer tipo) {
		List<ListinoCommessa> listiniPerTipo = new LinkedList<ListinoCommessa>();
		for (Integer idListino : listini.keySet()) {
			ListinoCommessa listino = listini.get(idListino);
			if (listino.getTipo().equals(tipo))
				listiniPerTipo.add(listino);
		}
		return listiniPerTipo;
	}
	
	/**
	 * Metodo di convenienza per ottenere tutti i listini che hanno un tipo affine ai trasporti.
	 * @return Una collezione di listini dell'ambito dei trasporti.
	 */
	public List<ListinoCommessa> getListiniClientiPerTrasporti() {
		List<ListinoCommessa> lista = new LinkedList<ListinoCommessa>();
		for (ListinoCommessa listino : listini.values()) {
			int tipo = listino.getTipo();
			if (tipo == AmbitoFattura.ID_SPEDIZIONI || tipo == AmbitoFattura.ID_SPEDIZIONI_UE || tipo == AmbitoFattura.ID_SPEDIZIONI_EXTRA_UE || tipo == AmbitoFattura.ID_GIACENZE || tipo == AmbitoFattura.ID_RITIRI)
				lista.add(listino);
		}
		return lista;
	}
	
	/**
	 * Metodo di convenienza per ottenere tutti i listini che hanno un tipo affine ai trasporti per la commessa selezionata.
	 * @return Una collezione di listini dell'ambito dei trasporti.
	 */
	public List<ListinoCommessa> getListiniClientePerTrasporti(int idCommessa) {
		List<ListinoCommessa> lista = new LinkedList<ListinoCommessa>();
		for (ListinoCommessa listino : listini.values()) {
			//Se il listino non appartiene alla commessa specificata passo direttamente al prossimo.
			if (listino.getIdCommessa() != idCommessa)
				continue;
			int tipo = listino.getTipo();
			if (tipo == AmbitoFattura.ID_SPEDIZIONI || tipo == AmbitoFattura.ID_GIACENZE || tipo == AmbitoFattura.ID_RITIRI)
				lista.add(listino);
		}
		return lista;
	}
	
	/**
	 * Metodo di convenienza per ottenere tutti i listini che hanno un tipo affine alla logistica.
	 * @return Una collezione di listini dell'ambito logistico.
	 */
	public List<ListinoCommessa> getListiniClientiPerLogisitica() {
		List<ListinoCommessa> lista = new LinkedList<ListinoCommessa>();
		for (ListinoCommessa listino : listini.values()) {
			int tipo = listino.getTipo();
			if (tipo == AmbitoFattura.ID_LOGISTICA || tipo == AmbitoFattura.ID_LOGISTICA_GIACENZE_SPAZI || tipo == AmbitoFattura.ID_EXTRA)
				lista.add(listino);
		}
		return lista;
	}
	
	public ListinoCommessa getListinoPerAmbitoECliente(int idAmbito, int idCommessa) {
		ListinoCommessa listinoPerAmbitoECliente = null;
		for (ListinoCommessa listino : listini.values()) {
			if (listino.getIdCommessa() == idCommessa && listino.getTipo() == idAmbito) {
				listinoPerAmbitoECliente = listino;
				break;
			}
		}
		return listinoPerAmbitoECliente;
	}
	
	/**
	 * Restuisce tutte le voci di un determinato listino.
	 * @param idListino L'ID del listino di cui si vuol ottenere le voci.
	 * @return Una lista di voci di listino.
	 */
	public List<VoceDiListino> getVociDiListino(int idListino) {
		return mappaListinoEVoci.get(idListino);
	}
	
	public VoceDiListino getVoceDiListino(int idVoce) {
		return vociDiListino.get(idVoce);
	}
	
	public boolean inserisciListino(ListinoCommessa listino) {
		ProcessoInserimentoListino processo = new ProcessoInserimentoListino(listino);
		DialogProgresso dialog = new DialogProgresso("Inserimento Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			ListinoCommessa inserito = processo.getObject();
			listini.put(inserito.getId(), inserito);
			mappaListinoEVoci.put(inserito.getId(), new LinkedList<>());
		}
		return esito;
	}
	
	public boolean aggiornaListino(ListinoCommessa listino) {
		ProcessoAggiornamentoListino processo = new ProcessoAggiornamentoListino(listino);
		DialogProgresso dialog = new DialogProgresso("Aggiornamento Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			ProcessoRefreshListino pr = new ProcessoRefreshListino(listino);
			DialogProgresso dialogR = new DialogProgresso("Aggiornamento Listino", false);
			dialogR.esegui(pr);
			ListinoCommessa vecchio = pr.getObject();
			if (vecchio != null) {
				listini.put(vecchio.getId(), vecchio);
			}
		}
		return esito;
	}
	
	public boolean eliminaListino(ListinoCommessa listino) {
		ProcessoEliminazioneListino processo = new ProcessoEliminazioneListino(listino);
		DialogProgresso dialog = new DialogProgresso("Eliminazione Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			listini.remove(listino.getId());
			List<VoceDiListino> voci = mappaListinoEVoci.remove(listino.getId());
			for (VoceDiListino voce : voci) {
				vociDiListino.remove(voce.getId());
			}
		}
		return esito;
	}
	
	public boolean inserisciVoce(VoceDiListino voce) {
		ProcessoInserimentoVoceDiListino processo = new ProcessoInserimentoVoceDiListino(voce);
		DialogProgresso dialog = new DialogProgresso("Inserimento Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			VoceDiListino inserita = processo.getObject();
			vociDiListino.put(inserita.getId(), inserita);
			mappaListinoEVoci.get(inserita.getIdListino()).add(inserita);
		}
		return esito;
	}
	
	public boolean aggiornaVoce(VoceDiListino voce) {
		ProcessoAggiornamentoVoceDiListino processo = new ProcessoAggiornamentoVoceDiListino(voce);
		DialogProgresso dialog = new DialogProgresso("Aggiornamento Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			ProcessoRefreshVoceDiListino pr = new ProcessoRefreshVoceDiListino(voce);
			DialogProgresso dialogR = new DialogProgresso("Aggiornamento Voce Di Listino", false);
			dialogR.esegui(pr);
			VoceDiListino vecchia = pr.getObject();
			if (vecchia != null) {
				vociDiListino.put(vecchia.getId(), vecchia);
				List<VoceDiListino> voci = mappaListinoEVoci.get(vecchia.getIdListino());
				voci.remove(voce);
				voci.add(vecchia);
			}
		}
		return esito;
	}
	
	public boolean eliminaVoce(VoceDiListino voce) {
		ProcessoEliminazioneVoceDiListino processo = new ProcessoEliminazioneVoceDiListino(voce);
		DialogProgresso dialog = new DialogProgresso("Eliminazione Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			vociDiListino.remove(voce.getId());
			mappaListinoEVoci.get(voce.getIdListino()).remove(voce);
		}
		return esito;
	}
	
	private String getNomeFile(ListinoCommessa listino, String path) {
		String nomeListino = "listino";
		String estensione = ".xls";
		String nomeFile = path + "\\"  + nomeListino + estensione;
		return nomeFile;
	}

	/**
	 * Genera un foglio excel nel path selezionato contenente le informazioni sul listino specificato
	 * Il nome del file sara' determinato dal nome del listino specificato.
	 * @param listino Il listino da esportare in excel
	 * @param path Il percorso dove verrà salvato il file.
	 */
	public void esportaListino(ListinoCommessa listino, String path) {
		String nomeFile = getNomeFile(listino, path);
		FileExcel workbook = FileExcel.getFileExcel(nomeFile);
		boolean successo = false;
		if (workbook != null) {
			workbook.creaFoglio(listino.getNome());
			//Titolo - riga 1
			workbook.aggiungiTitolo(listino.getNome(), 0, 0, "Listino di ...");
			workbook.aggiungiTitolo(listino.getNome(), 0, 3, "N.B. Il listino e' stato reso anonimo!");
			//Sotto titolo, tipo e descrizione - riga 2
			workbook.aggiungiSottoTitolo(listino.getNome(), 0, 1, "Tipo: " + ControllerAmbitiFatturazione.getInstance().getAmbito(listino.getTipo()));
			workbook.aggiungiSottoTitolo(listino.getNome(), 1, 1, listino.getDescrizione());
			//Nomi delle colonne per le voci di listino, salto una riga
			workbook.aggiungiSottoTitolo(listino.getNome(), 0, 3, "Voce");
			workbook.aggiungiSottoTitolo(listino.getNome(), 1, 3, "Ambito");
			workbook.aggiungiSottoTitolo(listino.getNome(), 2, 3, "Tipo Calcolo");
			workbook.aggiungiSottoTitolo(listino.getNome(), 3, 3, "Valori");
			//Inserisco le voci di listino
			int riga = 4;
			for (VoceDiListino voce : getVociDiListino(listino.getId())) {
				workbook.aggiungiTesto(listino.getNome(), 0, riga, voce.getNome());
				workbook.aggiungiTesto(listino.getNome(), 1, riga, getDescrizioneAmbito(voce));
				workbook.aggiungiTesto(listino.getNome(), 2, riga, voce.getTipoCalcolo());
				List<String> valori = getDescrizioneValori(voce);
				for (String valore : valori) {
					workbook.aggiungiTesto(listino.getNome(), 3, riga, valore);
					riga += 1;
				}
			}
			successo = workbook.salvaEChiudi();
			
		}
		if (successo) {
			DialogMessaggio.openInformation("Esportazione completata", "Esportazione completata!\r\nE' stato generato il file:\r\n" + nomeFile);
		} else {
			DialogMessaggio.openError("Errore nella esportazione", "Non \u00E8 stato possibile esportare il listino.");
		}
	}
	
	private String getDescrizioneAmbito(VoceDiListino voce) {
		SottoAmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(voce.getIdSottoAmbito());
		String nomeAmbito = ambito.getNome();
		return nomeAmbito;
	}
	
	private List<String> getDescrizioneValori(VoceDiListino voce) {
		List<String> valori = new LinkedList<String>();
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		switch (tipo) {
			case FISSO : {valori.add(stampaVoceFissa(voce)); break;}
			case PROPORZIONALE : {valori.addAll(stampaVoceProporzionale(voce)); break;}
			case SCAGLIONI : {valori.addAll(stampaVociScaglioni(voce)); break;}
			case SCAGLIONI_RIPETUTI : {valori.addAll(stampaVoceScaglioniRipetuti(voce)); break;}
			case PERCENTUALE : {valori.addAll(stampaVocePercentuale(voce)); break;}
			default : break;
		}
		return valori;
	}
	
	private String stampaVoceFissa(VoceDiListino voce) {
		VoceDiListinoFissa fissa = voce.getFissa();
		String stampa = Decorator.getEuroValue(fissa.getValore());
		return stampa;
	}
	
	private List<String> stampaVoceProporzionale(VoceDiListino voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoProporzionale proporzionale = voce.getProporzionale();
		String tipo = "a: " + voce.getStrategiaCalcolo();
		valori.add(tipo);
		String stampa = " " + Decorator.getEuroValue(proporzionale.getValore());
		if (proporzionale.getMinimo() != null)
			stampa += " minimo: " + proporzionale.getMinimo();
		if (proporzionale.getMassimo() != null)
			stampa += " massimo: " + proporzionale.getMassimo();
		valori.add(stampa);
		return valori;
	}
	
	private List<String> stampaVocePercentuale(VoceDiListino voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoPercentuale percentuale = voce.getPercentuale();
		String tipo = "Calcolato su: " + voce.getStrategiaCalcolo();
		valori.add(tipo);
		String stampa = percentuale.getValore() + " %";
		if (percentuale.getValoreMinimo() != null)
			stampa += " minimo: " + Decorator.getEuroValue(percentuale.getValoreMinimo());
		if (percentuale.getValoreMassimo() != null)
			stampa += " massimo: " + Decorator.getEuroValue(percentuale.getValoreMassimo());
		valori.add(stampa);
		return valori;
	}
	
	private List<String> stampaVoceScaglioniRipetuti(VoceDiListino voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoScaglioniRipetuti sr = voce.getRipetuti();
		String tipo = voce.getStrategiaCalcolo(); //sr.get;
		String um = (tipo.equals("COLLI")) ? " Colli" : " Kg";
		valori.add("Calcolato su: " + tipo);
		String stampa = Decorator.getEuroValue(sr.getValore());
		stampa += " ogni " + sr.getIntervallo() + um;
		if (sr.getMinimo() != null)
			stampa += " minimo: " + sr.getMinimo() + um;
		if (sr.getMassimo() != null)
			stampa += " massimo: " + sr.getMassimo() + um;
		valori.add(stampa);
		return valori;
	}
	
	private List<String> stampaVociScaglioni(VoceDiListino voce) {
		List<String> valori = new LinkedList<String>();
		List<VoceDiListinoScaglioni> voci = voce.getScaglioni();
		String tipo = voce.getStrategiaCalcolo();
		String um = (tipo.equals("COLLI")) ? " Colli" : " Kg";
		valori.add("Calcolato su: " + tipo);
		for (VoceDiListinoScaglioni v : voci) {
			String stampa = " da: " + v.getInizio() + um;
			stampa += " a: " + v.getFine() + um;
			stampa += " = " + Decorator.getEuroValue(v.getValore());
			valori.add(stampa);
		}
		return valori;
	}
	
	public MDettaglioVoce getDettagliVoce(VoceDiListino voce) {
		MDettaglioVoce dettagli;
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		switch (tipo) {
			case FISSO : {VoceDiListinoFissa fissa = voce.getFissa(); dettagli = new MVoceFissa(fissa.getValore());} break;
			case PERCENTUALE : {VoceDiListinoPercentuale percentuale = voce.getPercentuale(); dettagli = new MVocePercentuale(percentuale.getValore(), percentuale.getValoreMinimo(), percentuale.getValoreMassimo()); } break;
			case PROPORZIONALE : {VoceDiListinoProporzionale proporzionale = voce.getProporzionale(); dettagli = new MVoceProporzionale(proporzionale.getValore(), proporzionale.getMinimo(), proporzionale.getMassimo()); } break;
			case SCAGLIONI : {List<VoceDiListinoScaglioni> lista = voce.getScaglioni(); MVoceScaglioni vs = new MVoceScaglioni(); for (VoceDiListinoScaglioni v : lista) {Scaglione s = new Scaglione(); s.setValore(v.getValore()); s.setInizio(v.getInizio()); s.setFine(v.getFine()); vs.addScaglione(s);} dettagli = vs;} break;
			case SCAGLIONI_RIPETUTI : {VoceDiListinoScaglioniRipetuti sr = voce.getRipetuti(); dettagli = new MVoceScaglioniRipetuti(sr.getValore(), sr.getIntervallo(), sr.getMinimo(), sr.getMassimo()); } break;
			default : dettagli = null;
		}
		return dettagli;
	}
	
	private class ProcessoCaricamentoListini extends ProcessoCaricamentoDati<ListinoCommessa> {
		
		public ProcessoCaricamentoListini() {
			super(titleListini, resourceListini, ListinoCommessa[].class);
		}
		
	}
	
	private class ProcessoRefreshListino extends ProcessoCaricamentoDato<ListinoCommessa> {

		public ProcessoRefreshListino(ListinoCommessa listino) {
			super(titleListini, resourceListini + "/" + listino.getId(), ListinoCommessa.class);
		}
		
	}
	
	private class ProcessoInserimentoListino extends ProcessoInserimentoDati<ListinoCommessa> {

		public ProcessoInserimentoListino(ListinoCommessa object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoAggiornamentoListino extends ProcessoAggiornamentoDati<ListinoCommessa> {
		
		public ProcessoAggiornamentoListino(ListinoCommessa object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoEliminazioneListino extends ProcessoCancellazioneDati<ListinoCommessa> {

		public ProcessoEliminazioneListino(ListinoCommessa object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoCaricamentoVociDiListino extends ProcessoCaricamentoDati<VoceDiListino> {
		
		public ProcessoCaricamentoVociDiListino() {
			super(titleVoci, resourceVoci, VoceDiListino[].class);
		}
		
	}
	
	private class ProcessoRefreshVoceDiListino extends ProcessoCaricamentoDato<VoceDiListino> {

		public ProcessoRefreshVoceDiListino(VoceDiListino voce) {
			super(titleVoci, resourceVoci + "/" + voce.getId(), VoceDiListino.class);
		}
		
	}
	
	private class ProcessoInserimentoVoceDiListino extends ProcessoInserimentoDati<VoceDiListino> {

		public ProcessoInserimentoVoceDiListino(VoceDiListino object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}
	
	private class ProcessoAggiornamentoVoceDiListino extends ProcessoAggiornamentoDati<VoceDiListino> {

		public ProcessoAggiornamentoVoceDiListino(VoceDiListino object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}
	
	private class ProcessoEliminazioneVoceDiListino extends ProcessoCancellazioneDati<VoceDiListino> {

		public ProcessoEliminazioneVoceDiListino(VoceDiListino object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}

}

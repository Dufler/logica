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
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereFissa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorrierePercentuale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereProporzionale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereScaglioni;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereScaglioniRipetuti;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.utilities.excel.FileExcel;

public class ControllerListiniCorrieri {
	
	private static final String titleListini = "Listini Corrieri";
	private static final String resourceListini = "listinocorriere";
	private static final String titleVoci = "Voci di Listino";
	private static final String resourceVoci = "vocilistinocorriere";
	
	private static ControllerListiniCorrieri instance;
	
	private final HashMap<Integer, ListinoCorriere> listini;
	private final HashMap<Integer, List<VoceDiListinoCorriere>> mappaListinoEVoci;
	private final HashMap<Integer, VoceDiListinoCorriere> vociDiListino;

	private ControllerListiniCorrieri() {
		listini = new HashMap<Integer, ListinoCorriere>();
		vociDiListino = new HashMap<Integer, VoceDiListinoCorriere>();
		mappaListinoEVoci = new HashMap<Integer, List<VoceDiListinoCorriere>>();
		caricaListini();
	}

	public static ControllerListiniCorrieri getInstance() {
		if (instance == null) {
			instance = new ControllerListiniCorrieri();
		}
		return instance;
	}
	
	public synchronized void caricaListini() {
		ProcessoCaricamentoListini pcl = new ProcessoCaricamentoListini();
		DialogProgresso dialogL = new DialogProgresso("Caricamento Listini", false);
		dialogL.esegui(pcl);
		List<ListinoCorriere> listL = pcl.getLista();
		for (ListinoCorriere listino : listL) {
			listini.put(listino.getId(), listino);
			mappaListinoEVoci.put(listino.getId(), new LinkedList<VoceDiListinoCorriere>());
		}
		
		ProcessoCaricamentoVociDiListino pcvl = new ProcessoCaricamentoVociDiListino();
		DialogProgresso dialogVL = new DialogProgresso("Caricamento delle Listini", false);
		dialogVL.esegui(pcvl);
		List<VoceDiListinoCorriere> listVL = pcvl.getLista();
		for (VoceDiListinoCorriere voce : listVL) {
			vociDiListino.put(voce.getId(), voce);
			List<VoceDiListinoCorriere> listaVoci = mappaListinoEVoci.get(voce.getIdListino());
			listaVoci.add(voce);
		}
	}
	
	/**
	 * Restituisce tutti i listini dei clienti LTC.
	 * @return Una collezione di listini.
	 */
	public Collection<ListinoCorriere> getListini() {
		return listini.values();
	}
	
	/**
	 * Restituisce tutti i listini collegati ad un particolare cliente.
	 * @param commessa La commessa di cui si vuole avere i listini.
	 * @return Una collezione di listini appartenenti al cliente specificato.
	 */
	public List<ListinoCorriere> getListiniDaCorriere(Corriere corriere) {
		List<ListinoCorriere> listiniPerCorriere = new LinkedList<ListinoCorriere>();
		if (corriere != null && corriere.getId() != null) {
			for (Integer idListino : listini.keySet()) {
				ListinoCorriere listino = listini.get(idListino);
				if (listino.getIdCorriere().equals(corriere.getId()))
					listiniPerCorriere.add(listino);
			}
		}
		return listiniPerCorriere;
	}
	
	/**
	 * Restituisce tutti i listini cliente di un certo tipo.
	 * @param tipo Il tipo di listino
	 * @return Una collezione di listini del tipo specificato.
	 */
	public List<ListinoCorriere> getListiniPerTipo(Integer tipo) {
		List<ListinoCorriere> listiniPerTipo = new LinkedList<ListinoCorriere>();
		for (Integer idListino : listini.keySet()) {
			ListinoCorriere listino = listini.get(idListino);
			if (listino.getTipo().equals(tipo))
				listiniPerTipo.add(listino);
		}
		return listiniPerTipo;
	}
	
	/**
	 * Metodo di convenienza per ottenere tutti i listini che hanno un tipo affine ai trasporti.
	 * @return Una collezione di listini dell'ambito dei trasporti.
	 */
	public List<ListinoCorriere> getListiniClientiPerTrasporti() {
		List<ListinoCorriere> lista = new LinkedList<ListinoCorriere>();
		for (ListinoCorriere listino : listini.values()) {
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
	public List<ListinoCorriere> getListiniClientiPerLogisitica() {
		List<ListinoCorriere> lista = new LinkedList<ListinoCorriere>();
		for (ListinoCorriere listino : listini.values()) {
			int tipo = listino.getTipo();
			if (tipo == AmbitoFattura.ID_LOGISTICA || tipo == AmbitoFattura.ID_LOGISTICA_GIACENZE_SPAZI)
				lista.add(listino);
		}
		return lista;
	}
	
	/**
	 * Restuisce tutte le voci di un determinato listino.
	 * @param idListino L'ID del listino di cui si vuol ottenere le voci.
	 * @return Una lista di voci di listino.
	 */
	public List<VoceDiListinoCorriere> getVociDiListino(int idListino) {
		return mappaListinoEVoci.get(idListino);
	}
	
	public boolean inserisciListino(ListinoCorriere listino) {
		ProcessoInserimentoListino processo = new ProcessoInserimentoListino(listino);
		DialogProgresso dialog = new DialogProgresso("Inserimento Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			ListinoCorriere inserito = processo.getObject();
			listini.put(inserito.getId(), inserito);
			mappaListinoEVoci.put(inserito.getId(), new LinkedList<>());
		}
		return esito;
	}
	
	public boolean aggiornaListino(ListinoCorriere listino) {
		ProcessoAggiornamentoListino processo = new ProcessoAggiornamentoListino(listino);
		DialogProgresso dialog = new DialogProgresso("Aggiornamento Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			ProcessoRefreshListino pr = new ProcessoRefreshListino(listino);
			DialogProgresso dialogR = new DialogProgresso("Aggiornamento Listino", false);
			dialogR.esegui(pr);
			ListinoCorriere vecchio = pr.getObject();
			if (vecchio != null) {
				listini.put(vecchio.getId(), vecchio);
			}
		}
		return esito;
	}
	
	public boolean eliminaListino(ListinoCorriere listino) {
		ProcessoEliminazioneListino processo = new ProcessoEliminazioneListino(listino);
		DialogProgresso dialog = new DialogProgresso("Eliminazione Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			listini.remove(listino.getId());
			List<VoceDiListinoCorriere> voci = mappaListinoEVoci.remove(listino.getId());
			for (VoceDiListinoCorriere voce : voci) {
				vociDiListino.remove(voce.getId());
			}
		}
		return esito;
	}
	
	public boolean inserisciVoce(VoceDiListinoCorriere voce) {
		ProcessoInserimentoVoceDiListinoCorriere processo = new ProcessoInserimentoVoceDiListinoCorriere(voce);
		DialogProgresso dialog = new DialogProgresso("Inserimento Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			VoceDiListinoCorriere inserita = processo.getObject();
			vociDiListino.put(inserita.getId(), inserita);
			mappaListinoEVoci.get(inserita.getIdListino()).add(inserita);
		}
		return esito;
	}
	
	public boolean aggiornaVoce(VoceDiListinoCorriere voce) {
		ProcessoAggiornamentoVoceDiListinoCorriere processo = new ProcessoAggiornamentoVoceDiListinoCorriere(voce);
		DialogProgresso dialog = new DialogProgresso("Aggiornamento Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (!esito) {
			ProcessoRefreshVoceDiListinoCorriere pr = new ProcessoRefreshVoceDiListinoCorriere(voce);
			DialogProgresso dialogR = new DialogProgresso("Aggiornamento Voce Di Listino", false);
			dialogR.esegui(pr);
			VoceDiListinoCorriere vecchia = pr.getObject();
			if (vecchia != null) {
				vociDiListino.put(vecchia.getId(), vecchia);
				List<VoceDiListinoCorriere> voci = mappaListinoEVoci.get(vecchia.getIdListino());
				voci.remove(voce);
				voci.add(vecchia);
			}
		}
		return esito;
	}
	
	public boolean eliminaVoce(VoceDiListinoCorriere voce) {
		ProcessoEliminazioneVoceDiListinoCorriere processo = new ProcessoEliminazioneVoceDiListinoCorriere(voce);
		DialogProgresso dialog = new DialogProgresso("Eliminazione Voce Di Listino", false);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		if (esito) {
			vociDiListino.remove(voce.getId());
			mappaListinoEVoci.get(voce.getIdListino()).remove(voce);
		}
		return esito;
	}
	
	private String getNomeFile(ListinoCorriere listino, String path) {
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
	public void esportaListino(ListinoCorriere listino, String path) {
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
			for (VoceDiListinoCorriere voce : getVociDiListino(listino.getId())) {
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
	
	private String getDescrizioneAmbito(VoceDiListinoCorriere voce) {
		SottoAmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(voce.getIdSottoAmbito());
		String nomeAmbito = ambito.getNome();
		return nomeAmbito;
	}
	
	private List<String> getDescrizioneValori(VoceDiListinoCorriere voce) {
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
	
	private String stampaVoceFissa(VoceDiListinoCorriere voce) {
		VoceDiListinoCorriereFissa fissa = voce.getFissa();
		String stampa = Decorator.getEuroValue(fissa.getValore());
		return stampa;
	}
	
	private List<String> stampaVoceProporzionale(VoceDiListinoCorriere voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoCorriereProporzionale proporzionale = voce.getProporzionale();
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
	
	private List<String> stampaVocePercentuale(VoceDiListinoCorriere voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoCorrierePercentuale percentuale = voce.getPercentuale();
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
	
	private List<String> stampaVoceScaglioniRipetuti(VoceDiListinoCorriere voce) {
		List<String> valori = new LinkedList<String>();
		VoceDiListinoCorriereScaglioniRipetuti sr = voce.getRipetuti();
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
	
	private List<String> stampaVociScaglioni(VoceDiListinoCorriere voce) {
		List<String> valori = new LinkedList<String>();
		List<VoceDiListinoCorriereScaglioni> voci = voce.getScaglioni();
		String tipo = voce.getStrategiaCalcolo();
		String um = (tipo.equals("COLLI")) ? " Colli" : " Kg";
		valori.add("Calcolato su: " + tipo);
		for (VoceDiListinoCorriereScaglioni v : voci) {
			String stampa = " da: " + v.getInizio() + um;
			stampa += " a: " + v.getFine() + um;
			stampa += " = " + Decorator.getEuroValue(v.getValore());
			valori.add(stampa);
		}
		return valori;
	}
	
	public MDettaglioVoce getDettagliVoce(VoceDiListinoCorriere voce) {
		MDettaglioVoce dettagli;
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		switch (tipo) {
			case FISSO : {VoceDiListinoCorriereFissa fissa = voce.getFissa(); dettagli = new MVoceFissa(fissa.getValore());} break;
			case PERCENTUALE : {VoceDiListinoCorrierePercentuale percentuale = voce.getPercentuale(); dettagli = new MVocePercentuale(percentuale.getValore(), percentuale.getValoreMinimo(), percentuale.getValoreMassimo()); } break;
			case PROPORZIONALE : {VoceDiListinoCorriereProporzionale proporzionale = voce.getProporzionale(); dettagli = new MVoceProporzionale(proporzionale.getValore(), proporzionale.getMinimo(), proporzionale.getMassimo()); } break;
			case SCAGLIONI : {List<VoceDiListinoCorriereScaglioni> lista = voce.getScaglioni(); MVoceScaglioni vs = new MVoceScaglioni(); for (VoceDiListinoCorriereScaglioni v : lista) {Scaglione s = new Scaglione(); s.setValore(v.getValore()); s.setInizio(v.getInizio()); s.setFine(v.getFine()); vs.addScaglione(s);} dettagli = vs;} break;
			case SCAGLIONI_RIPETUTI : {VoceDiListinoCorriereScaglioniRipetuti sr = voce.getRipetuti(); dettagli = new MVoceScaglioniRipetuti(sr.getValore(), sr.getIntervallo(), sr.getMinimo(), sr.getMassimo()); } break;
			default : dettagli = null;
		}
		return dettagli;
	}
	
	private class ProcessoCaricamentoListini extends ProcessoCaricamentoDati<ListinoCorriere> {
		
		public ProcessoCaricamentoListini() {
			super(titleListini, resourceListini, ListinoCorriere[].class);
		}
		
	}
	
	private class ProcessoRefreshListino extends ProcessoCaricamentoDato<ListinoCorriere> {

		public ProcessoRefreshListino(ListinoCorriere listino) {
			super(titleListini, resourceListini + "/" + listino.getId(), ListinoCorriere.class);
		}
		
	}
	
	private class ProcessoInserimentoListino extends ProcessoInserimentoDati<ListinoCorriere> {

		public ProcessoInserimentoListino(ListinoCorriere object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoAggiornamentoListino extends ProcessoAggiornamentoDati<ListinoCorriere> {

		public ProcessoAggiornamentoListino(ListinoCorriere object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoEliminazioneListino extends ProcessoCancellazioneDati<ListinoCorriere> {

		public ProcessoEliminazioneListino(ListinoCorriere object) {
			super(titleListini, resourceListini, object);
		}
		
	}
	
	private class ProcessoCaricamentoVociDiListino extends ProcessoCaricamentoDati<VoceDiListinoCorriere> {
		
		public ProcessoCaricamentoVociDiListino() {
			super(titleVoci, resourceVoci, VoceDiListinoCorriere[].class);
		}
		
	}
	
	private class ProcessoRefreshVoceDiListinoCorriere extends ProcessoCaricamentoDato<VoceDiListinoCorriere> {

		public ProcessoRefreshVoceDiListinoCorriere(VoceDiListinoCorriere voce) {
			super(titleVoci, resourceVoci + "/" + voce.getId(), VoceDiListinoCorriere.class);
		}
		
	}
	
	private class ProcessoInserimentoVoceDiListinoCorriere extends ProcessoInserimentoDati<VoceDiListinoCorriere> {

		public ProcessoInserimentoVoceDiListinoCorriere(VoceDiListinoCorriere object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}
	
	private class ProcessoAggiornamentoVoceDiListinoCorriere extends ProcessoAggiornamentoDati<VoceDiListinoCorriere> {

		public ProcessoAggiornamentoVoceDiListinoCorriere(VoceDiListinoCorriere object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}
	
	private class ProcessoEliminazioneVoceDiListinoCorriere extends ProcessoCancellazioneDati<VoceDiListinoCorriere> {

		public ProcessoEliminazioneVoceDiListinoCorriere(VoceDiListinoCorriere object) {
			super(titleVoci, resourceVoci, object);
		}
		
	}

}

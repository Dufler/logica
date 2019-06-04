package it.ltc.logica.common.controller.listini;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.ListinoSimulazioneDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVoceDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVoceFissaDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVocePercentualeDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVoceProporzionaleDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVoceScaglioniDao;
import it.ltc.database.dao.locali.ListinoSimulazioneVoceScaglioniRipetutiDao;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceFissa;
import it.ltc.logica.common.calcolo.algoritmi.MVocePercentuale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceProporzionale;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioni;
import it.ltc.logica.common.calcolo.algoritmi.MVoceScaglioniRipetuti;
import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereFissa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorrierePercentuale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereProporzionale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereScaglioni;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereScaglioniRipetuti;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoFissa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoPercentuale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoProporzionale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioni;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioniRipetuti;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceFissa;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVocePercentuale;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceProporzionale;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioni;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioniRipetuti;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.logica.utilities.excel.FileExcel;

/**
 * Controller per il caricamento e la gestione dei listini di simulazione conservati nel DB locale.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ListiniSimulazioneController {
	
	public static final String MESSAGGIO_ERRORE_CREAZIONE_LISTINO = "Impossibile creare il listino.";
	
	private static ListiniSimulazioneController instance;
	
	private final ListinoSimulazioneDao managerListini;
	private final ListinoSimulazioneVoceDao managerVociDiListino;
	private final ListinoSimulazioneVoceFissaDao managerVociFisse;
	private final ListinoSimulazioneVocePercentualeDao managerVociPercentuali;
	private final ListinoSimulazioneVoceProporzionaleDao managerVociProporzionali;
	private final ListinoSimulazioneVoceScaglioniDao managerVociScaglioni;
	private final ListinoSimulazioneVoceScaglioniRipetutiDao managerVociScaglioniRipetuti;
	
	private final HashMap<Integer, ListinoSimulazione> listini;
	private final HashMap<Integer, List<ListinoSimulazioneVoce>> mappaListinoEVoci;
	private final HashMap<Integer, ListinoSimulazioneVoce> vociDiListino;
	private final HashMap<Integer, ListinoSimulazioneVoceFissa> vociFisse;
	private final HashMap<Integer, ListinoSimulazioneVocePercentuale> vociPercentuali;
	private final HashMap<Integer, ListinoSimulazioneVoceProporzionale> vociProporzionali;
	private final HashMap<Integer, ListinoSimulazioneVoceScaglioniRipetuti> vociScaglioniRipetuti;
	private final HashMap<Integer, List<ListinoSimulazioneVoceScaglioni>> vociAScaglioni;

	private ListiniSimulazioneController() {
		
		managerListini = new ListinoSimulazioneDao();
		managerVociDiListino = new ListinoSimulazioneVoceDao();
		managerVociFisse = new ListinoSimulazioneVoceFissaDao();
		managerVociPercentuali = new ListinoSimulazioneVocePercentualeDao();
		managerVociProporzionali = new ListinoSimulazioneVoceProporzionaleDao();
		managerVociScaglioni = new ListinoSimulazioneVoceScaglioniDao();
		managerVociScaglioniRipetuti = new ListinoSimulazioneVoceScaglioniRipetutiDao();
		
		listini = new HashMap<Integer, ListinoSimulazione>();
		vociDiListino = new HashMap<Integer, ListinoSimulazioneVoce>();
		mappaListinoEVoci = new HashMap<Integer, List<ListinoSimulazioneVoce>>();
		vociFisse = new HashMap<Integer, ListinoSimulazioneVoceFissa>();
		vociPercentuali = new HashMap<Integer, ListinoSimulazioneVocePercentuale>();
		vociProporzionali = new HashMap<Integer, ListinoSimulazioneVoceProporzionale>();
		vociScaglioniRipetuti = new HashMap<Integer, ListinoSimulazioneVoceScaglioniRipetuti>();
		vociAScaglioni = new HashMap<Integer, List<ListinoSimulazioneVoceScaglioni>>();
		caricaListini();
	}
	
	public static ListiniSimulazioneController getInstance() {
		if (instance == null) {
			instance = new ListiniSimulazioneController();
		}
		return instance;
	}
	
	private void caricaListini() {
		ProcessoCaricamentoListini processo = new ProcessoCaricamentoListini();
		DialogProgresso dialog = new DialogProgresso("Caricamento Listini", false);
		dialog.esegui(processo);
	}

	public boolean eliminaListino(ListinoSimulazione listino) {
		boolean eliminato = managerListini.elimina(listino);
		if (eliminato) {
			listini.remove(listino.getId());
			List<ListinoSimulazioneVoce> voci = mappaListinoEVoci.remove(listino.getId());
			for (ListinoSimulazioneVoce voce : voci) {
				vociDiListino.remove(voce.getId());
				vociFisse.remove(voce.getId());
				vociProporzionali.remove(voce.getId());
				vociPercentuali.remove(voce.getId());
				vociAScaglioni.remove(voce.getId());
				vociScaglioniRipetuti.remove(voce.getId());
			}
		}
		return eliminato;
	}

	public boolean eliminaVoce(ListinoSimulazioneVoce voce) {
		boolean eliminato = managerVociDiListino.elimina(voce);
		if (eliminato) {
			mappaListinoEVoci.get(voce.getIdListino()).remove(voce);
			vociDiListino.remove(voce.getId());
			vociFisse.remove(voce.getId());
			vociProporzionali.remove(voce.getId());
			vociPercentuali.remove(voce.getId());
			vociAScaglioni.remove(voce.getId());
			vociScaglioniRipetuti.remove(voce.getId());
		}
		return eliminato;
	}

	public List<ListinoSimulazione> getTuttiIListiniClienti() {
		List<ListinoSimulazione> lista = new LinkedList<ListinoSimulazione>();
		for (Integer id : listini.keySet()) {
			lista.add(listini.get(id));
		}
		return lista;
	}
	
	public List<ListinoSimulazione> getListiniDiSimulazione() {
		List<ListinoSimulazione> lista = new LinkedList<ListinoSimulazione>();
		for (Integer id : listini.keySet()) {
			ListinoSimulazione listino = listini.get(id);
			int tipo = listino.getTipo();
			if (tipo == AmbitoFattura.ID_SIMULAZIONI_TRASPORTI)
				lista.add(listino);
		}
		return lista;
	}

	public List<ListinoSimulazioneVoce> getVociDiListino(int idListino) {
		return mappaListinoEVoci.get(idListino);
	}

	public boolean inserisciListino(ListinoSimulazione listino) {
		ListinoSimulazione inserito = managerListini.inserisci(listino);
		boolean inserimento = inserito != null; 
		if (inserimento) {
			listini.put(inserito.getId(), inserito);
			LinkedList<ListinoSimulazioneVoce> listaNuoveVociCopiate =  new LinkedList<ListinoSimulazioneVoce>();
			mappaListinoEVoci.put(inserito.getId(), listaNuoveVociCopiate);
		}
		return inserimento;
	}

	public boolean aggiornaListinoCliente(ListinoSimulazione listino) {
		boolean update = managerListini.aggiorna(listino);
		return update;
	}
	
	public boolean inserisciVoce(ListinoSimulazioneVoce voce) {
		boolean insert;
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipo());
		switch (tipo) {
			case FISSO : insert = inserisciVoceFissa(voce); break;
			case PROPORZIONALE : insert = inserisciVoceProporzionale(voce); break;
			case SCAGLIONI : insert = insertVoceScaglione(voce); break;
			case SCAGLIONI_RIPETUTI : insert = insertVoceScaglioniRipetuti(voce); break;
			case PERCENTUALE : insert = inserisciVocePercentuale(voce); break;
			default : insert = false; break;
		}
		if (insert)
			mappaListinoEVoci.get(voce.getIdListino()).add(voce);
		return insert;
	}
	
	public boolean inserisciVoceFissa(ListinoSimulazioneVoce voce) {
		boolean insert;
		ListinoSimulazioneVoce entity = managerVociDiListino.inserisci(voce);
		if (entity != null) {
			vociDiListino.put(entity.getId(), entity);
			ListinoSimulazioneVoceFissa fissa = voce.getFissa();
			fissa.setId(entity.getId());
			insert = managerVociFisse.inserisci(fissa);
			if (insert)
				vociFisse.put(voce.getId(), fissa);
		} else {
			insert = false;
		}
		return insert;
	}
	
	public boolean inserisciVocePercentuale(ListinoSimulazioneVoce voce) {
		boolean insert;
		ListinoSimulazioneVoce entity = managerVociDiListino.inserisci(voce);
		if (entity != null) {
			vociDiListino.put(entity.getId(), entity);
			ListinoSimulazioneVocePercentuale percentuale = voce.getPercentuale();
			percentuale.setId(entity.getId());
			insert = managerVociPercentuali.inserisci(percentuale);
			if (insert)
				vociPercentuali.put(voce.getId(), percentuale);
		} else {
			insert = false;
		}
		return insert;
	}
	
	public boolean inserisciVoceProporzionale(ListinoSimulazioneVoce voce) {
		boolean insert;
		ListinoSimulazioneVoce entity = managerVociDiListino.inserisci(voce);
		if (entity != null) {
			vociDiListino.put(entity.getId(), entity);
			ListinoSimulazioneVoceProporzionale proporzionale = voce.getProporzionale();
			proporzionale.setId(entity.getId());
			insert = managerVociProporzionali.inserisci(proporzionale);
			if (insert)
				vociProporzionali.put(voce.getId(), proporzionale);
		} else {
			insert = false;
		}
		return insert;
	}
	
	public boolean insertVoceScaglione(ListinoSimulazioneVoce voce) {
		boolean insert;
		ListinoSimulazioneVoce entity = managerVociDiListino.inserisci(voce);
		if (entity != null) {
			vociDiListino.put(entity.getId(), entity);
			int scaglioniInseriti = 0;
			List<ListinoSimulazioneVoceScaglioni> scaglioni = voce.getScaglioni();
			for (ListinoSimulazioneVoceScaglioni scaglione : scaglioni) {
				scaglione.setId(entity.getId());
				boolean insertScaglione = managerVociScaglioni.inserisci(scaglione);
				if (insertScaglione)
					scaglioniInseriti += 1;
			}
			insert = scaglioniInseriti == scaglioni.size();
			if (insert)
				vociAScaglioni.put(voce.getId(), scaglioni);
		} else {
			insert = false;
		}
		return insert;
	}
	
	public boolean insertVoceScaglioniRipetuti(ListinoSimulazioneVoce voce) {
		boolean insert;
		ListinoSimulazioneVoce entity = managerVociDiListino.inserisci(voce);
		if (entity != null) {
			vociDiListino.put(entity.getId(), entity);
			ListinoSimulazioneVoceScaglioniRipetuti ripetuti = voce.getRipetuti();
			ripetuti.setId(entity.getId());
			insert = managerVociScaglioniRipetuti.inserisci(ripetuti);
			if (insert)
				vociScaglioniRipetuti.put(voce.getId(), ripetuti);
		} else {
			insert = false;
		}
		return insert;
	}

	public boolean aggiornaListinoSimulazioneVoce(ListinoSimulazioneVoce voceDiListino) {
		boolean update = managerVociDiListino.aggiorna(voceDiListino);
		return update;
	}

	public boolean aggiornaListinoSimulazioneVoceFissa(ListinoSimulazioneVoceFissa voceDiListinoFissa) {
		boolean update = managerVociFisse.aggiorna(voceDiListinoFissa);
		return update;
	}

	public boolean aggiornaListinoSimulazioneVocePercentuale(ListinoSimulazioneVocePercentuale voceDiListinoPercentuale) {
		boolean update = managerVociPercentuali.aggiorna(voceDiListinoPercentuale);
		return update;
	}

	public boolean aggiornaListinoSimulazioneVoceProporzionale(ListinoSimulazioneVoceProporzionale voceDiListinoProporzionale) {
		boolean update = managerVociProporzionali.aggiorna(voceDiListinoProporzionale);
		return update;
	}

	public boolean aggiornaListinoSimulazioneVoceScaglioniRipetuti(ListinoSimulazioneVoceScaglioniRipetuti voceDiListinoScaglioniRipetuti) {
		boolean update = managerVociScaglioniRipetuti.aggiorna(voceDiListinoScaglioniRipetuti);
		return update;
	}

	public boolean aggiornaListinoSimulazioneVoceScaglioni(List<ListinoSimulazioneVoceScaglioni> listaVociScaglioni, int id) {
		boolean insert = true;
		managerVociScaglioni.eliminaDaID(id);
		for (ListinoSimulazioneVoceScaglioni scaglione : listaVociScaglioni) {
			boolean partialInsert = managerVociScaglioni.inserisci(scaglione);
			if (!partialInsert)
				insert = false;
		}
		if (insert)
			vociAScaglioni.put(id, listaVociScaglioni);
		return insert;
	}

	public ListinoSimulazione getListinoCliente(Integer idListinoSimulazione) {
		return listini.get(idListinoSimulazione);
	}
	
	public List<ListinoSimulazione> getListiniPerTipo(Integer tipo) {
		List<ListinoSimulazione> listiniPerTipo = new LinkedList<ListinoSimulazione>();
		for (Integer idListino : listini.keySet()) {
			ListinoSimulazione listino = listini.get(idListino);
			if (listino.getTipo() == tipo)
				listiniPerTipo.add(listino);
		}
		return listiniPerTipo;
	}
	
	public boolean copiaListino(ListinoSimulazione listino) {
		//Info sul listino
		ListinoSimulazione copia = new ListinoSimulazione();
		copia.setNome("Copia di " + listino.getNome());
		copia.setDescrizione(listino.getDescrizione());
		copia.setTipo(listino.getTipo());
		//Info sulle voci
		LinkedList<ListinoSimulazioneVoce> listaNuoveVociCopiate =  new LinkedList<ListinoSimulazioneVoce>();
		for (ListinoSimulazioneVoce voce : mappaListinoEVoci.get(listino.getId())) {
			ListinoSimulazioneVoce nuovaVoce = new ListinoSimulazioneVoce();
			nuovaVoce.setNome(voce.getNome());
			nuovaVoce.setDescrizione(voce.getDescrizione());
			nuovaVoce.setTipo(voce.getTipo());
			nuovaVoce.setIdsottoAmbito(voce.getIdsottoAmbito());
			nuovaVoce.setValoreSottoAmbito(voce.getValoreSottoAmbito());
			nuovaVoce.setStrategia(voce.getStrategia());
			listaNuoveVociCopiate.add(nuovaVoce);
			TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipo());
			switch (tipo) {
				case FISSO : { nuovaVoce.setFissa(copiaVoceFissa(voce)); break; }
				case PROPORZIONALE : { nuovaVoce.setProporzionale(copiaVoceProporzionale(voce)); break; }
				case SCAGLIONI : { nuovaVoce.setScaglioni(copiaVociScaglioni(voce)); break; }
				case SCAGLIONI_RIPETUTI : { nuovaVoce.setRipetuti(copiaVociScaglioniRipetuti(voce)); break; }
				case PERCENTUALE : { nuovaVoce.setPercentuale(copiaVocePercentuale(voce)); break; }
				default : break;
			}
		}
		//Inserimento
		ProcessoSalvataggioListino processo = new ProcessoSalvataggioListino(copia, listaNuoveVociCopiate);
		DialogProgresso dialog = new DialogProgresso("Copia del listino");
		dialog.esegui(processo);
		return true;
	}
	
	private ListinoSimulazioneVocePercentuale copiaVocePercentuale(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVocePercentuale percentuale = vociPercentuali.get(voce.getId());
		ListinoSimulazioneVocePercentuale copia = new ListinoSimulazioneVocePercentuale();
		copia.setValore(percentuale.getValore());
		copia.setMassimo(percentuale.getMassimo());
		copia.setMinimo(percentuale.getMinimo());
		return copia;
	}

	private ListinoSimulazioneVoceScaglioniRipetuti copiaVociScaglioniRipetuti(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoceScaglioniRipetuti voceSR = vociScaglioniRipetuti.get(voce.getId());
		ListinoSimulazioneVoceScaglioniRipetuti copia = new ListinoSimulazioneVoceScaglioniRipetuti();
		copia.setValore(voceSR.getValore());
		copia.setIntervallo(voceSR.getIntervallo());
		copia.setMinimo(voceSR.getMinimo());
		copia.setMassimo(voceSR.getMassimo());
		return copia;
	}

	private List<ListinoSimulazioneVoceScaglioni> copiaVociScaglioni(ListinoSimulazioneVoce voce) {
		List<ListinoSimulazioneVoceScaglioni> voci = vociAScaglioni.get(voce.getId());
		List<ListinoSimulazioneVoceScaglioni> vociCopiate = new LinkedList<ListinoSimulazioneVoceScaglioni>(); 
		for (ListinoSimulazioneVoceScaglioni voceScaglioni : voci) {
			ListinoSimulazioneVoceScaglioni copia = new ListinoSimulazioneVoceScaglioni();
			copia.setValore(voceScaglioni.getValore());
			copia.setInizio(voceScaglioni.getInizio());
			copia.setFine(voceScaglioni.getFine());
			vociCopiate.add(copia);
		}
		return vociCopiate;
	}

	private ListinoSimulazioneVoceProporzionale copiaVoceProporzionale(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoceProporzionale proporzionale = vociProporzionali.get(voce.getId());
		ListinoSimulazioneVoceProporzionale copia = new ListinoSimulazioneVoceProporzionale();
		copia.setMassimo(proporzionale.getMassimo());
		copia.setMinimo(proporzionale.getMinimo());
		copia.setValore(proporzionale.getValore());
		return copia;
	}

	private ListinoSimulazioneVoceFissa copiaVoceFissa(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoceFissa fissa = vociFisse.get(voce.getId());
		ListinoSimulazioneVoceFissa copia = new ListinoSimulazioneVoceFissa();
		copia.setValore(fissa.getValore());
		return copia;
	}
	
	private String getNomeFile(ListinoSimulazione listino, String path) {
		String nomeListino = "listino";
		String estensione = ".xls";
		String nomeFile = path + "\\"  + nomeListino + estensione;
		return nomeFile;
	}

	public void esportaListino(ListinoSimulazione listino, String path) {
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
			for (ListinoSimulazioneVoce voce : getVociDiListino(listino.getId())) {
				workbook.aggiungiTesto(listino.getNome(), 0, riga, voce.getNome());
				workbook.aggiungiTesto(listino.getNome(), 1, riga, getDescrizioneAmbito(voce));
				workbook.aggiungiTesto(listino.getNome(), 2, riga, voce.getTipo());
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
	
	private String getDescrizioneAmbito(ListinoSimulazioneVoce voce) {
		SottoAmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(voce.getIdsottoAmbito());
		String nomeAmbito = ambito.getNome();
		return nomeAmbito;
	}
	
	private List<String> getDescrizioneValori(ListinoSimulazioneVoce voce) {
		List<String> valori = new LinkedList<String>();
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipo());
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
	
	private String stampaVoceFissa(ListinoSimulazioneVoce voce) {
		ListinoSimulazioneVoceFissa fissa = vociFisse.get(voce.getId());
		String stampa = Decorator.getEuroValue(fissa.getValore());
		return stampa;
	}
	
	private List<String> stampaVoceProporzionale(ListinoSimulazioneVoce voce) {
		List<String> valori = new LinkedList<String>();
		ListinoSimulazioneVoceProporzionale proporzionale = vociProporzionali.get(voce.getId());
		String tipo = "a: " + voce.getStrategia();
		valori.add(tipo);
		String stampa = " " + Decorator.getEuroValue(proporzionale.getValore());
		if (proporzionale.getMinimo() != null)
			stampa += " minimo: " + proporzionale.getMinimo();
		if (proporzionale.getMassimo() != null)
			stampa += " massimo: " + proporzionale.getMassimo();
		valori.add(stampa);
		return valori;
	}
	
	private List<String> stampaVocePercentuale(ListinoSimulazioneVoce voce) {
		List<String> valori = new LinkedList<String>();
		ListinoSimulazioneVocePercentuale percentuale = vociPercentuali.get(voce.getId());
		String tipo = "Calcolato su: " + voce.getStrategia();
		valori.add(tipo);
		String stampa = percentuale.getValore() + " %";
		if (percentuale.getMinimo() != null)
			stampa += " minimo: " + Decorator.getEuroValue(percentuale.getMinimo());
		if (percentuale.getMassimo() != null)
			stampa += " massimo: " + Decorator.getEuroValue(percentuale.getMassimo());
		valori.add(stampa);
		return valori;
	}
	
	private List<String> stampaVoceScaglioniRipetuti(ListinoSimulazioneVoce voce) {
		List<String> valori = new LinkedList<String>();
		ListinoSimulazioneVoceScaglioniRipetuti sr = vociScaglioniRipetuti.get(voce.getId());
		String tipo = voce.getStrategia();
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
	
	private List<String> stampaVociScaglioni(ListinoSimulazioneVoce voce) {
		List<String> valori = new LinkedList<String>();
		List<ListinoSimulazioneVoceScaglioni> voci = vociAScaglioni.get(voce.getId());
		String tipo = voce.getStrategia();
		String um = (tipo.equals("COLLI")) ? " Colli" : " Kg";
		valori.add("Calcolato su: " + tipo);
		for (ListinoSimulazioneVoceScaglioni v : voci) {
			String stampa = " da: " + v.getInizio() + um;
			stampa += " a: " + v.getFine() + um;
			stampa += " = " + Decorator.getEuroValue(v.getValore());
			valori.add(stampa);
		}
		return valori;
	}
	
	public String creaNuovoListinoDaCliente(ListinoSimulazione listino, ListinoCommessa listinoCommessa, Double[] rincari) {
		String risultato = "";
		LinkedList<ListinoSimulazioneVoce> listaNuoveVociCopiate =  new LinkedList<ListinoSimulazioneVoce>();
		List<VoceDiListino> vociDiListino = ControllerListiniClienti.getInstance().getVociDiListino(listinoCommessa.getId());
		for (VoceDiListino voce : vociDiListino) {
			//Recupero le informazioni base
			String nome = voce.getNome();
			String descrizione = voce.getDescrizione();
			TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
			Integer idSottoAmbito = voce.getIdSottoAmbito();
			Integer idAmbitoCorrispondente = getAmbitoCorrispondentePerCliente(idSottoAmbito);
			String valoreSottoAmbito = voce.getValoreSottoAmbito();
			SottoAmbitoFattura sottoAmbito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(idSottoAmbito);
			if (idAmbitoCorrispondente == null) {
				//Notifico l'utente e passo alla prossima voce.
				risultato += "Impossibile copiare la voce: '" + voce.getNome() + "', l'ambito: '" + sottoAmbito.getNome() + "' \u00E8 riservato solamente ai listini cliente effettivi.\r\n";
				continue;
			}
			//Inserisco la nuova voce
			ListinoSimulazioneVoce nuovaVoce = new ListinoSimulazioneVoce();
			nuovaVoce.setNome(nome);
			nuovaVoce.setDescrizione(descrizione);
			nuovaVoce.setIdsottoAmbito(idAmbitoCorrispondente);
			nuovaVoce.setValoreSottoAmbito(valoreSottoAmbito);
			nuovaVoce.setTipo(tipo.getCodifica());
			nuovaVoce.setStrategia(voce.getStrategiaCalcolo());
			//Inserisco i dettagli specifici della voce
			double rincaro = getRincaro(sottoAmbito.getCategoriaAmbito(), rincari);
			switch (tipo) {
				case FISSO : { nuovaVoce.setFissa(rincaraVoceFissa(voce, rincaro)); break; }
				case PROPORZIONALE : { nuovaVoce.setProporzionale(rincaraVoceProporzionale(voce, rincaro)); break; }
				case SCAGLIONI : { nuovaVoce.setScaglioni(rincaraVociScaglioni(voce, rincaro)); break; }
				case SCAGLIONI_RIPETUTI : { nuovaVoce.setRipetuti(rincaraVociScaglioniRipetuti(voce, rincaro)); break; }
				case PERCENTUALE : { nuovaVoce.setPercentuale(rincaraVocePercentuale(voce, rincaro)); break; }
				default : break;
			}
			listaNuoveVociCopiate.add(nuovaVoce);	
		}
		
		ProcessoSalvataggioListino processo = new ProcessoSalvataggioListino(listino, listaNuoveVociCopiate);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		
		return risultato;
	}
	
	public String creaNuovoListinoDaCorriere(ListinoSimulazione listino, ListinoCorriere listinoCorriere, Double[] rincari) {
		String risultato = "";
		LinkedList<ListinoSimulazioneVoce> listaNuoveVociCopiate =  new LinkedList<ListinoSimulazioneVoce>();
		List<VoceDiListinoCorriere> vociDiListino = ControllerListiniCorrieri.getInstance().getVociDiListino(listinoCorriere.getId());
		for (VoceDiListinoCorriere voce : vociDiListino) {
			//Recupero le informazioni base
			String nome = voce.getNome();
			String descrizione = voce.getDescrizione();
			TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
			Integer idSottoAmbito = voce.getIdSottoAmbito();
			Integer idAmbitoCorrispondente = getAmbitoCorrispondentePerCorriere(idSottoAmbito);
			String valoreSottoAmbito = voce.getValoreSottoAmbito();
			SottoAmbitoFattura sottoAmbito = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(idSottoAmbito);
			if (idAmbitoCorrispondente == null) {
				//Notifico l'utente e passo alla prossima voce.
				risultato += "Impossibile copiare la voce: '" + voce.getNome() + "', l'ambito: '" + sottoAmbito.getNome() + "' \u00E8 riservato solamente ai corrieri.\r\n";
				continue;
			}
			//Inserisco la nuova voce
			ListinoSimulazioneVoce nuovaVoce = new ListinoSimulazioneVoce();
			nuovaVoce.setNome(nome);
			nuovaVoce.setDescrizione(descrizione);
			nuovaVoce.setIdsottoAmbito(idAmbitoCorrispondente);
			nuovaVoce.setValoreSottoAmbito(valoreSottoAmbito);
			nuovaVoce.setTipo(tipo.getCodifica());
			nuovaVoce.setStrategia(voce.getStrategiaCalcolo());
			//Inserisco i dettagli specifici della voce
			double rincaro = getRincaro(sottoAmbito.getCategoriaAmbito(), rincari);
			switch (tipo) {
				case FISSO : { nuovaVoce.setFissa(rincaraVoceFissa(voce, rincaro)); break; }
				case PROPORZIONALE : { nuovaVoce.setProporzionale(rincaraVoceProporzionale(voce, rincaro)); break; }
				case SCAGLIONI : { nuovaVoce.setScaglioni(rincaraVociScaglioni(voce, rincaro)); break; }
				case SCAGLIONI_RIPETUTI : { nuovaVoce.setRipetuti(rincaraVociScaglioniRipetuti(voce, rincaro)); break; }
				case PERCENTUALE : { nuovaVoce.setPercentuale(rincaraVocePercentuale(voce, rincaro)); break; }
				default : break;
			}
			listaNuoveVociCopiate.add(nuovaVoce);	
		}
		
		ProcessoSalvataggioListino processo = new ProcessoSalvataggioListino(listino, listaNuoveVociCopiate);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		
		return risultato;
	}
	
	private Integer getAmbitoCorrispondentePerCliente(Integer idSottoAmbito) {
		Integer idCorrispondente;
		switch (idSottoAmbito) {
			case 19 : idCorrispondente = 82; break; //Spedizione con molti colli
			case 12 : idCorrispondente = 81; break; //Fuel
			case 18 : idCorrispondente = 80; break; //istat
			case 8 : idCorrispondente = 79; break; //contrassegno
			case 11 : idCorrispondente = 112; break; //Contrassegno superiore a
			case 10 : idCorrispondente = 113; break; //Contrassegno inferiore a
			case 24 : idCorrispondente = 78; break; //nolo estero
			case 20 : idCorrispondente = 76; break; //nolo
			case 31: case 6 : case 5 : idCorrispondente = 91; break; //priority
			case 22 : idCorrispondente = 92; break; //zone disagiate
			case 25 : case 26 : idCorrispondente = 93; break; //isole
			case 30 : idCorrispondente = 94; break; //regione specifica
			//case 97 : idCorrispondente = 98; break; //Nolo Light - Non sembra che esista.
			case 133 : idCorrispondente = 132; break; //nazione specifica
			case 134 : idCorrispondente = 135; break; //volume superiore a
			default : idCorrispondente = null;
		}
		return idCorrispondente;
	}
	
	private Integer getAmbitoCorrispondentePerCorriere(Integer idSottoAmbito) {
		Integer idCorrispondente;
		switch (idSottoAmbito) {
			case 54 : idCorrispondente = 82; break; //Spedizione con molti colli
			case 59 : idCorrispondente = 81; break; //Fuel
			case 62 : idCorrispondente = 80; break; //istat
			case 34 : idCorrispondente = 79; break; //contrassegno
			case 63 : idCorrispondente = 112; break; //Contrassegno superiore a
			case 64 : idCorrispondente = 113; break; //Contrassegno inferiore a
			case 50 : idCorrispondente = 78; break; //nolo estero
			case 33 : idCorrispondente = 76; break; //nolo
			case 57: case 58 : case 87 : case 88 : case 28 : case 29 : idCorrispondente = 91; break; //priority
			case 56 : idCorrispondente = 92; break; //zone disagiate
			case 51 : idCorrispondente = 93; break; //isole
			case 95 : idCorrispondente = 94; break; //regione specifica
			case 97 : idCorrispondente = 98; break; //Nolo Light
			case 111 : idCorrispondente = 132; break; //nazione specifica
			case 136 : idCorrispondente = 135; break; //volume superiore a
			default : idCorrispondente = null;
		}
		return idCorrispondente;
	}

	private double getRincaro(String categoriaAmbito, Double[] rincari) {
		double valore;
		switch (categoriaAmbito) {
			case "NOLO_BASE" : valore = rincari[0]; break;
			case "NOLO_EXTRA" : valore = rincari[1]; break;
			case "EXTRA" : valore = rincari[2]; break;
			case "CONTRASSEGNO" : valore = rincari[3]; break;
			case "ISTAT" : valore = rincari[4]; break;
			case "FUEL" : valore = rincari[5]; break;
			default : valore = 0;
		}
		valore = (valore / 100.0) + 1;
		return valore;
	}
	
	private ListinoSimulazioneVocePercentuale rincaraVocePercentuale(VoceDiListinoCorriere voce, double rincaro) {
		VoceDiListinoCorrierePercentuale percentuale = voce.getPercentuale();
		ListinoSimulazioneVocePercentuale copia = new ListinoSimulazioneVocePercentuale();
		copia.setValore(rincaraEArrotonda(percentuale.getValore(), rincaro));
		copia.setMassimo(percentuale.getValoreMassimo());
		copia.setMinimo(percentuale.getValoreMinimo());
		return copia;
	}

	private ListinoSimulazioneVoceScaglioniRipetuti rincaraVociScaglioniRipetuti(VoceDiListinoCorriere voce, double rincaro) {
		VoceDiListinoCorriereScaglioniRipetuti voceSR = voce.getRipetuti();
		ListinoSimulazioneVoceScaglioniRipetuti copia = new ListinoSimulazioneVoceScaglioniRipetuti();
		copia.setValore(rincaraEArrotonda(voceSR.getValore(), rincaro));
		copia.setIntervallo(voceSR.getIntervallo());
		copia.setMinimo(voceSR.getMinimo());
		copia.setMassimo(voceSR.getMassimo());
		return copia;
	}

	private List<ListinoSimulazioneVoceScaglioni> rincaraVociScaglioni(VoceDiListinoCorriere voce, double rincaro) {
		List<VoceDiListinoCorriereScaglioni> voci = voce.getScaglioni();
		List<ListinoSimulazioneVoceScaglioni> vociCopiate = new LinkedList<ListinoSimulazioneVoceScaglioni>(); 
		for (VoceDiListinoCorriereScaglioni voceScaglioni : voci) {
			ListinoSimulazioneVoceScaglioni copia = new ListinoSimulazioneVoceScaglioni();
			copia.setValore(rincaraEArrotonda(voceScaglioni.getValore(), rincaro));
			copia.setInizio(voceScaglioni.getInizio());
			copia.setFine(voceScaglioni.getFine());
			vociCopiate.add(copia);
		}
		return vociCopiate;
	}

	private ListinoSimulazioneVoceProporzionale rincaraVoceProporzionale(VoceDiListinoCorriere voce, double rincaro) {
		VoceDiListinoCorriereProporzionale proporzionale = voce.getProporzionale();
		ListinoSimulazioneVoceProporzionale copia = new ListinoSimulazioneVoceProporzionale();
		copia.setMassimo(proporzionale.getMassimo());
		copia.setMinimo(proporzionale.getMinimo());
		copia.setValore(rincaraEArrotonda(proporzionale.getValore(), rincaro));
		return copia;
	}

	private ListinoSimulazioneVoceFissa rincaraVoceFissa(VoceDiListinoCorriere voce, double rincaro) {
		VoceDiListinoCorriereFissa fissa = voce.getFissa();
		ListinoSimulazioneVoceFissa copia = new ListinoSimulazioneVoceFissa();
		copia.setValore(rincaraEArrotonda(fissa.getValore(), rincaro));
		return copia;
	}
	
	private ListinoSimulazioneVocePercentuale rincaraVocePercentuale(VoceDiListino voce, double rincaro) {
		VoceDiListinoPercentuale percentuale = voce.getPercentuale();
		ListinoSimulazioneVocePercentuale copia = new ListinoSimulazioneVocePercentuale();
		copia.setValore(rincaraEArrotonda(percentuale.getValore(), rincaro));
		copia.setMassimo(percentuale.getValoreMassimo());
		copia.setMinimo(percentuale.getValoreMinimo());
		return copia;
	}

	private ListinoSimulazioneVoceScaglioniRipetuti rincaraVociScaglioniRipetuti(VoceDiListino voce, double rincaro) {
		VoceDiListinoScaglioniRipetuti voceSR = voce.getRipetuti();
		ListinoSimulazioneVoceScaglioniRipetuti copia = new ListinoSimulazioneVoceScaglioniRipetuti();
		copia.setValore(rincaraEArrotonda(voceSR.getValore(), rincaro));
		copia.setIntervallo(voceSR.getIntervallo());
		copia.setMinimo(voceSR.getMinimo());
		copia.setMassimo(voceSR.getMassimo());
		return copia;
	}

	private List<ListinoSimulazioneVoceScaglioni> rincaraVociScaglioni(VoceDiListino voce, double rincaro) {
		List<VoceDiListinoScaglioni> voci = voce.getScaglioni();
		List<ListinoSimulazioneVoceScaglioni> vociCopiate = new LinkedList<ListinoSimulazioneVoceScaglioni>(); 
		for (VoceDiListinoScaglioni voceScaglioni : voci) {
			ListinoSimulazioneVoceScaglioni copia = new ListinoSimulazioneVoceScaglioni();
			copia.setValore(rincaraEArrotonda(voceScaglioni.getValore(), rincaro));
			copia.setInizio(voceScaglioni.getInizio());
			copia.setFine(voceScaglioni.getFine());
			vociCopiate.add(copia);
		}
		return vociCopiate;
	}

	private ListinoSimulazioneVoceProporzionale rincaraVoceProporzionale(VoceDiListino voce, double rincaro) {
		VoceDiListinoProporzionale proporzionale = voce.getProporzionale();
		ListinoSimulazioneVoceProporzionale copia = new ListinoSimulazioneVoceProporzionale();
		copia.setMassimo(proporzionale.getMassimo());
		copia.setMinimo(proporzionale.getMinimo());
		copia.setValore(rincaraEArrotonda(proporzionale.getValore(), rincaro));
		return copia;
	}

	private ListinoSimulazioneVoceFissa rincaraVoceFissa(VoceDiListino voce, double rincaro) {
		VoceDiListinoFissa fissa = voce.getFissa();
		ListinoSimulazioneVoceFissa copia = new ListinoSimulazioneVoceFissa();
		copia.setValore(rincaraEArrotonda(fissa.getValore(), rincaro));
		return copia;
	}
	
	/**
	 * Rincara il valore della percentuale indicata e arrotonda il risultato a 3 cifre decimali.
	 * @param valore il valore da rincarare.
	 * @param rincaro il rincaro da effettuare
	 * @return il valore rincarato.
	 */
	private double rincaraEArrotonda(double valore, double rincaro) {
		double nuovoValore = Math.round(valore * rincaro * 1000.0) / 1000.0;
		return nuovoValore;
	}
	
	/**
	 * Inner Class dedita al caricamento dei listini.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	private class ProcessoCaricamentoListini extends Processo {
		
		private static final String title = "Caricamento Listini";
		
		public ProcessoCaricamentoListini() {
			super(title, -1);
		}

		@Override
		public void eseguiOperazioni() {
			for (ListinoSimulazione listino : getListini()) {
				listini.put(listino.getId(), listino);
				List<ListinoSimulazioneVoce> voci = getVociDiListino(listino);
				mappaListinoEVoci.put(listino.getId(), voci);
				for (ListinoSimulazioneVoce voce : voci) {
					vociDiListino.put(voce.getId(), voce);
					TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipo());
					switch (tipo) {
						case FISSO : caricaVoceFissa(voce); break;
						case PERCENTUALE : caricaVocePercentuale(voce); break;
						case PROPORZIONALE : caricaVoceProporzionale(voce); break;
						case SCAGLIONI : caricaVociScaglioni(voce); break;
						case SCAGLIONI_RIPETUTI : caricaVoceScaglioniRipetuti(voce); break;
						default : break;
					}
				}
			}
		}
		
		private List<ListinoSimulazione> getListini() {
			List<ListinoSimulazione> listini = managerListini.trovaTutti();
			return listini;
		}
		
		private List<ListinoSimulazioneVoce> getVociDiListino(ListinoSimulazione listino) {
			List<ListinoSimulazioneVoce> voci = managerVociDiListino.trovaVociDaListino(listino);
			return voci;
		}
		
		private void caricaVoceFissa(ListinoSimulazioneVoce voceDiListino) {
			ListinoSimulazioneVoceFissa voceCaricata = managerVociFisse.trovaDaVoce(voceDiListino);
			vociFisse.put(voceDiListino.getId(), voceCaricata);
		}
		
		private void caricaVocePercentuale(ListinoSimulazioneVoce voceDiListino) {
			ListinoSimulazioneVocePercentuale voceCaricata = managerVociPercentuali.trovaDaVoce(voceDiListino);
			vociPercentuali.put(voceDiListino.getId(), voceCaricata);
		}
		
		private void caricaVoceProporzionale(ListinoSimulazioneVoce voceDiListino) {
			ListinoSimulazioneVoceProporzionale voceCaricata = managerVociProporzionali.trovaDaVoce(voceDiListino);
			vociProporzionali.put(voceDiListino.getId(), voceCaricata);
		}
		
		private void caricaVoceScaglioniRipetuti(ListinoSimulazioneVoce voceDiListino) {
			ListinoSimulazioneVoceScaglioniRipetuti voceCaricata = managerVociScaglioniRipetuti.trovaDaVoce(voceDiListino);
			vociScaglioniRipetuti.put(voceDiListino.getId(), voceCaricata);
		}
		
		private void caricaVociScaglioni(ListinoSimulazioneVoce voceDiListino) {
			List<ListinoSimulazioneVoceScaglioni> vociCaricate = managerVociScaglioni.trovaDaVoce(voceDiListino);
			vociAScaglioni.put(voceDiListino.getId(), vociCaricate);
		}

	}

	public ListinoSimulazioneVoceFissa getVoceFissa(ListinoSimulazioneVoce voce) {
		return vociFisse.get(voce.getId());
	}

	public ListinoSimulazioneVocePercentuale getVocePercentuale(ListinoSimulazioneVoce voce) {
		return vociPercentuali.get(voce.getId());
	}

	public ListinoSimulazioneVoceProporzionale getVoceProporzionale(ListinoSimulazioneVoce voce) {
		return vociProporzionali.get(voce.getId());
	}

	public List<ListinoSimulazioneVoceScaglioni> getVociScaglioni(ListinoSimulazioneVoce voce) {
		return vociAScaglioni.get(voce.getId());
	}

	public ListinoSimulazioneVoceScaglioniRipetuti getVoceScaglioniRipetuti(ListinoSimulazioneVoce voce) {
		return vociScaglioniRipetuti.get(voce.getId());
	}

	public MDettaglioVoce getDettagliVoce(ListinoSimulazioneVoce voce) {
		MDettaglioVoce dettagli;
		TipoAlgoritmo tipo = TipoAlgoritmo.getTipo(voce.getTipo());
		switch (tipo) {
			case FISSO : {ListinoSimulazioneVoceFissa fissa = vociFisse.get(voce.getId()); dettagli = new MVoceFissa(fissa.getValore());} break;
			case PERCENTUALE : {ListinoSimulazioneVocePercentuale percentuale = vociPercentuali.get(voce.getId()); dettagli = new MVocePercentuale(percentuale.getValore(), percentuale.getMinimo(), percentuale.getMassimo()); } break;
			case PROPORZIONALE : {ListinoSimulazioneVoceProporzionale proporzionale = vociProporzionali.get(voce.getId()); dettagli = new MVoceProporzionale(proporzionale.getValore(), proporzionale.getMinimo(), proporzionale.getMassimo()); } break;
			case SCAGLIONI : {List<ListinoSimulazioneVoceScaglioni> lista = vociAScaglioni.get(voce.getId()); MVoceScaglioni vs = new MVoceScaglioni(); for (ListinoSimulazioneVoceScaglioni v : lista) {Scaglione s = new Scaglione(); s.setValore(v.getValore()); s.setInizio(v.getInizio()); s.setFine(v.getFine()); vs.addScaglione(s);} dettagli = vs;} break;
			case SCAGLIONI_RIPETUTI : {ListinoSimulazioneVoceScaglioniRipetuti sr = vociScaglioniRipetuti.get(voce.getId()); dettagli = new MVoceScaglioniRipetuti(sr.getValore(), sr.getIntervallo(), sr.getMinimo(), sr.getMassimo()); } break;
			default : dettagli = null;
		}
		return dettagli;
	}

	/**
	 * Restituisce lo scopo di calcolo del listino di simulazione: costo o ricavo.
	 * Viene restituito nessuno in caso di anomalie.
	 * @param idListino l'ID del listino di simulazione.
	 * @return un valore della enum Scopo.
	 */
	public Scopo getScopo(Integer idListino) {
		Scopo scopo;
		ListinoSimulazione listino = listini.get(idListino);
		int tipo = listino != null ? listino.getTipo() : -1;
		switch (tipo) {
			case AmbitoFattura.ID_SIMULAZIONI_TRASPORTI : scopo = Scopo.RICAVO; break;
			default : scopo = Scopo.NESSUNO; break;
		}
		return scopo;
	}
	
	/**
	 * Classe interna che si occupa del salvataggio del listino.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	private class ProcessoSalvataggioListino extends Processo {
		
		private final static String title = "Inserimento del nuovo listino";
		
		private final ListinoSimulazione listino;
		private final List<ListinoSimulazioneVoce> voci;

		public ProcessoSalvataggioListino(ListinoSimulazione listino, List<ListinoSimulazioneVoce> voci) {
			super(title, voci.size() + 1);
			
			this.listino = listino;
			this.voci = voci;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			boolean inserimentoListino = inserisciListino(listino);
			if (!inserimentoListino)
				throw new RuntimeException("Errore durante il salvataggio del listino.");
			aumentaProgresso(1);
			for (ListinoSimulazioneVoce voce : voci) {
				voce.setIdListino(listino.getId());
				boolean inserimentoVoce = inserisciVoce(voce);
				if (!inserimentoVoce)
					throw new RuntimeException("Errore durante il salvataggio di una voce di listino.");
				aumentaProgresso(1);
			}
		}

	}

}

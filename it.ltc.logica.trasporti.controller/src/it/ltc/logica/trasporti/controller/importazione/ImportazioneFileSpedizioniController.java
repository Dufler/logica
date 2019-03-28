package it.ltc.logica.trasporti.controller.importazione;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegniSimulazione;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerDocumentiSpedizioniSimulazione;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.common.controller.trasporti.ControllerServizioSpedizione;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioniSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.ContrassegnoSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.ServizioSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.utility.csv.FileCSV;

public class ImportazioneFileSpedizioniController {
	
	private static ImportazioneFileSpedizioniController instance;
	
	public static final String LDV = "lettera di vettura";
	public static final String CORRIERE = "corriere";
	public static final String RIFERIMENTO = "riferimento";
	public static final String DATA_PARTENZA = "data partenza";
	public static final String RAGIONE_SOCIALE_DESTINATARIO = "ragione sociale destinatario";
	public static final String INDIRIZZO_DESTINATARIO = "indirizzo destinatario";
	public static final String CAP_DESTINATARIO = "cap destinatario";
	public static final String RAGIONE_SOCIALE_MITTENTE = "ragione sociale mittente";
	public static final String INDIRIZZO_MITTENTE = "indirizzo mittente";
	public static final String CAP_MITTENTE = "cap mittente";
	public static final String COLLI = "colli";
	public static final String PESO = "peso";
	public static final String VOLUME = "volume";
	public static final String COSTO = "costo";
	public static final String CONTRASSEGNO = "contrassegno";
	public static final String TIPO_CONTRASSEGNO = "tipo contrassegno";
	public static final String CODICE_CLIENTE = "codice cliente";
	public static final String PEZZI = "pezzi";
	public static final String SERVIZIO = "servizio";
	
	private final ControllerSpedizioniSimulazione controllerSpedizioni;
	private final ControllerContrassegniSimulazione controllerContrassegni;
	
	private ImportazioneFileSpedizioniController() {
		controllerSpedizioni = ControllerSpedizioniSimulazione.getInstance();
		controllerContrassegni = ControllerContrassegniSimulazione.getInstance();
	}

	public static synchronized ImportazioneFileSpedizioniController getInstance() {
		if (null == instance) {
			instance = new ImportazioneFileSpedizioniController();
		}
		return instance;
	}
	
	public boolean controllaEsistenzaDocumento(FileSpedizioni file) {
		return false;
	}
	
	public List<SpedizioneSimulazione> estraiSpedizioniDaFile(String pathFile, TipoFileImportazioneSpedizioni tipo) {
		//List<SpedizioneSimulazione> spedizioni = estraiDaCSV(pathFile);
		ProcessoEstrazioneCSV processo = new ProcessoEstrazioneCSV(pathFile);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		//FIXME: Vanno fatte procedura diverse d'importazione in base al tipo di file che viene passato.
		return processo.getSpedizioni();
	}
	
	/**
	 * Processo che estrae i dati delle spedizioni dal csv.
	 * @author Damiano
	 *
	 */
	private class ProcessoEstrazioneCSV extends Processo {

		private static final String title = "Estrazione dati da csv";
		
		private final String pathFile;
		private final List<SpedizioneSimulazione> spedizioni;
		
		public ProcessoEstrazioneCSV(String pathFile) {
			super(title, -1);
			this.pathFile = pathFile;
			this.spedizioni = new LinkedList<>();
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			spedizioni.addAll(estraiDaCSV(pathFile));			
		}
		
		public List<SpedizioneSimulazione> getSpedizioni() {
			return spedizioni;
		}
		
	}
	
	private void controllaColonne(FileCSV csv) {
		List<String> colonneNecessarie = new LinkedList<>();
		List<String> colonneOpzionali = new LinkedList<>();
		//Controllo la presenza delle colonne necessarie: Info spedizione
		colonneNecessarie.addAll(csv.checkColonne(LDV, CORRIERE, RIFERIMENTO, DATA_PARTENZA, CODICE_CLIENTE, COLLI, PESO, VOLUME));
		//Controllo la presenza delle colonne necessarie: Destinatario
		colonneNecessarie.addAll(csv.checkColonne(RAGIONE_SOCIALE_DESTINATARIO, INDIRIZZO_DESTINATARIO, CAP_DESTINATARIO));
		//Controllo la presenza delle colonne opzionali: Mittente
		colonneOpzionali.addAll(csv.checkColonne(RAGIONE_SOCIALE_DESTINATARIO, INDIRIZZO_DESTINATARIO, CAP_DESTINATARIO));
		//Controllo la presenza delle colonne opzionali: Contrassegno
		colonneOpzionali.addAll(csv.checkColonne(CONTRASSEGNO, TIPO_CONTRASSEGNO));
		//Controllo la presenza delle colonne opzionali: varie
		colonneOpzionali.addAll(csv.checkColonne(COSTO, PEZZI, SERVIZIO));
		//Mostro i messaggi d'errore e/o warning
		if (!colonneNecessarie.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder("Non sono state trovate le seguenti colonne necessarie alla simulazione:\r\n");
			for (String colonna : colonneNecessarie) {
				errorMessage.append(colonna);
				errorMessage.append("\r\n");
			}
			throw new RuntimeException(errorMessage.toString());
		}
		if (!colonneOpzionali.isEmpty()) {
			String title = "Colonne opzionali mancanti!";
			StringBuilder warningMessage = new StringBuilder("Non sono state trovate le seguenti colonne opzionali per la simulazione:\r\n");
			for (String colonna : colonneOpzionali) {
				warningMessage.append(colonna);
				warningMessage.append("\r\n");
			}
			warningMessage.append("Verranno usati i valori di default dove necessario.");
			DialogMessaggio.openWarning(title, warningMessage.toString());
		}
	}
	
	private IndirizzoSimulazione inserisciDestinazione(FileCSV csv) {
		//Recupero e controllo le info necessarie
		String cap = csv.getStringa(CAP_DESTINATARIO);
		if (cap == null || cap.isEmpty()) throw new RuntimeException("Cap destinatario mancante per la riga " + (csv.getRigaAttuale() + 2));
		Cap c = ControllerCap.getInstance().getInfoGeneraliCap(cap);
		if (c == null) throw new RuntimeException("il Cap  " + cap + " non \u00E8 stato trovato, inserirlo manualmente dalla sezione indirizzi>cap oppure contattare il ced prima di procedere con l'importazione dati.");
		IndirizzoSimulazione destinazione = new IndirizzoSimulazione();
		destinazione.setRagioneSociale(csv.getStringa(RAGIONE_SOCIALE_DESTINATARIO));
		destinazione.setIndirizzo(csv.getStringa(INDIRIZZO_DESTINATARIO));
		destinazione.setCap(cap);
		destinazione.setProvincia(c.getProvincia());
		destinazione.setLocalita(c.getLocalita());
		destinazione.setConsegnaAlPiano(false);
		destinazione.setConsegnaAppuntamento(false);
		destinazione.setConsegnaGdo(false);
		destinazione.setConsegnaPrivato(false);
		destinazione.setNazione("ITA");
		destinazione.setInserimentoManuale(false);
		//Vado in inserimento/aggiornamento
		boolean insert = ControllerIndirizziSimulazione.getInstance().salva(destinazione);
		if (!insert) throw new RuntimeException("Impossibile inserire l'indirizzo di destinazione. (riga " + (csv.getRigaAttuale() + 2) + ")");
		return destinazione;
	}
	
	private IndirizzoSimulazione inserisciPartenza(FileCSV csv) {
		//Recupero e controllo le info necessarie
		String cap = csv.getStringa(CAP_MITTENTE);
		if (cap == null || cap.isEmpty()) cap = "06073";
		Cap c = ControllerCap.getInstance().getInfoGeneraliCap(cap);
		if (c == null) throw new RuntimeException("il Cap  " + cap + " non \u00E8 stato trovato, inserirlo manualmente dalla sezione indirizzi>cap oppure contattare il ced prima di procedere con l'importazione dati.");
		IndirizzoSimulazione partenza = new IndirizzoSimulazione();
		String ragioneSocialeMittente = csv.getStringa(RAGIONE_SOCIALE_MITTENTE);
		if (ragioneSocialeMittente == null || ragioneSocialeMittente.isEmpty()) ragioneSocialeMittente = "LTC";
		partenza.setRagioneSociale(ragioneSocialeMittente);
		String indirizzo = csv.getStringa(INDIRIZZO_MITTENTE);
		if (indirizzo == null || indirizzo.isEmpty()) indirizzo = "Via Firenze, 41";
		partenza.setIndirizzo(indirizzo);
		partenza.setCap(cap);
		partenza.setProvincia(c.getProvincia());
		partenza.setLocalita(c.getLocalita());
		partenza.setConsegnaAlPiano(false);
		partenza.setConsegnaAppuntamento(false);
		partenza.setConsegnaGdo(false);
		partenza.setConsegnaPrivato(false);
		partenza.setNazione("ITA");
		partenza.setInserimentoManuale(false);
		//Vado in inserimento/aggiornamento
		boolean insert = ControllerIndirizziSimulazione.getInstance().salva(partenza);
		if (!insert) throw new RuntimeException("Impossibile inserire l'indirizzo del mittente. (riga " + (csv.getRigaAttuale() + 2) + ")");
		return partenza;
	}
	
	public void controllaCap(FileCSV csv) {
		List<String> capNonEsistenti = new LinkedList<>();
		List<Integer> destinatariSenzaCap = new LinkedList<>(); 
		while (csv.prossimaRiga()) {
			//Mittente
			String capMittente = csv.getStringa(CAP_MITTENTE);
			if (capMittente != null && !capMittente.isEmpty()) {
				Cap c = ControllerCap.getInstance().getInfoGeneraliCap(capMittente);
				if (c == null)
					capNonEsistenti.add(capMittente);
			}
			//Destinatario
			String capDestinatario = csv.getStringa(CAP_DESTINATARIO);
			if (capDestinatario == null || capDestinatario.isEmpty()) {
				destinatariSenzaCap.add(csv.getRigaAttuale() + 2);
			} else {
				Cap c = ControllerCap.getInstance().getInfoGeneraliCap(capDestinatario);
				if (c == null)
					capNonEsistenti.add(capDestinatario);
			}
		}
		if (!capNonEsistenti.isEmpty() || !destinatariSenzaCap.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder("Il controllo sui cap \u00E8 fallito!\r\n");
			if (!capNonEsistenti.isEmpty()) {
				errorMessage.append("vanno inseriti i seguenti cap a sistema:\r\n");
				for (String cap : capNonEsistenti) {
					errorMessage.append(cap);
					errorMessage.append("\r\n");
				}
			}
			if (!destinatariSenzaCap.isEmpty()) {
				errorMessage.append("Le seguenti righe non hanno il cap:\r\n");
				for (Integer riga : destinatariSenzaCap) {
					errorMessage.append(riga);
					errorMessage.append("\r\n");
				}
			}
			throw new RuntimeException(errorMessage.toString());
		}
		//Riporto il csv all'inizio.
		csv.setRigaAttuale(0);
	}
	
	public List<SpedizioneSimulazione> estraiDaCSV(String pathFile) {
		//Carico il file
		FileCSV csv;
		try {
			csv = FileCSV.leggiFile(new File(pathFile));
		} catch (Exception e) { throw new RuntimeException("Impossibile leggere il file specificato. (" + e.getLocalizedMessage() + ")"); }
		//Eseguo dei controlli sulla presenza delle colonne
		controllaColonne(csv);
		//Eseguo i controlli sui cap
		controllaCap(csv);
		//Preparo degli warning se si presenta l'occasione
		boolean warningVolume = false;
		//Parso le spedizioni
		List<SpedizioneSimulazione> spedizioni = new LinkedList<>();
		while (csv.prossimaRiga()) {
			int riga = csv.getRigaAttuale() + 2;
			//Ottengo l'indirizzo di destinazione
			IndirizzoSimulazione destinazione = inserisciDestinazione(csv);
			if (destinazione == null) throw new RuntimeException("L'indirizzo di destinazione per la riga " + riga + " non \u00E8 corretto.");
			IndirizzoSimulazione partenza = inserisciPartenza(csv);
			if (partenza == null) throw new RuntimeException("L'indirizzo di partenza per la riga " + riga + " non \u00E8 corretto.");
			SpedizioneSimulazione spedizione = new SpedizioneSimulazione();
			//Imposto i valori fissi
			spedizione.setAssicurazione(false);
			spedizione.setDatiCompleti(true);
			spedizione.setGiacenza(false);
			spedizione.setInRitardo(false);
			spedizione.setParticolarita(false);
			spedizione.setTipo(TipoSpedizione.ITALIA); //Fisso, per ora.
			spedizione.setStato("IMP"); //Importazione manuale
			spedizione.setIdCommessa(30); //Commessa: LTC Generica
			//Imposto i valori necessari
			Integer colli = csv.getIntero(COLLI);
			if (colli == null || colli < 1) throw new RuntimeException("Il numero dei colli per la riga " + riga + " non \u00E8 corretto.");
			spedizione.setColli(colli);
			Double peso = csv.getNumerico(PESO);
			if (peso == null || peso <= 0) throw new RuntimeException("Il peso indicato per la riga " + riga + " non \u00E8 corretto.");
			spedizione.setPeso(peso);
			Double volume = csv.getNumerico(VOLUME);
			if (volume == null || volume <= 0) {
				warningVolume = true;
				volume = colli * 0.02;
			}
			spedizione.setVolume(volume);
			spedizione.setIndirizzoDestinazione(destinazione.getId());
			spedizione.setIndirizzoPartenza(partenza.getId());
			//Imposto i valori necessari che possono essere impostati a default se non presenti
			String codiceCliente = csv.getStringa(CODICE_CLIENTE);
			if (codiceCliente == null) codiceCliente = "NESSUNO";
			spedizione.setCodiceCliente(codiceCliente);
			Date dataPartenza = csv.getData(DATA_PARTENZA);
			if (dataPartenza == null) dataPartenza = new Date();
			spedizione.setDataPartenza(dataPartenza);
			String codiceCorriere = csv.getStringa(CORRIERE);
			Corriere corriere = codiceCorriere != null ? ControllerCorrieri.getInstance().getCorriere(codiceCorriere) : null;
			int idCorriere = corriere != null ? corriere.getId() : 10;
			spedizione.setIdCorriere(idCorriere);
			String ldv = csv.getStringa(LDV);
			spedizione.setLetteraDiVettura(ldv);
			Integer pezzi = csv.getIntero(PEZZI);
			if (pezzi == null || pezzi < 0)
				pezzi = 0;
			spedizione.setPezzi(pezzi);
			//Imposto i valori opzionali
			spedizione.setCosto(csv.getNumerico(COSTO));
			spedizione.setRagioneSocialeDestinatario(csv.getStringa(RAGIONE_SOCIALE_DESTINATARIO));
			String riferimento = csv.getStringa(RIFERIMENTO);
			if (riferimento == null || riferimento.isEmpty())
				riferimento = "Riga " + (csv.getRigaAttuale() + 1);
			spedizione.setRiferimentoCliente(riferimento);
			spedizione.setRiferimentoMittente(riferimento);
			String codiceServizio = csv.getStringa(SERVIZIO);
			ServizioSpedizione servizio = ControllerServizioSpedizione.getInstance().getServizio(codiceServizio);
			spedizione.setServizio(servizio != null ? servizio.getCodice() : "DEF");
			//Contrassegno
			Double valoreContrassegno = csv.getNumerico(CONTRASSEGNO);
			boolean contrassegno = valoreContrassegno != null && valoreContrassegno > 0;
			spedizione.setContrassegno(contrassegno);
			if (contrassegno) {
				ContrassegnoSimulazione cs = new ContrassegnoSimulazione();
				cs.setAnnullato(false);
				cs.setTipo(csv.getStringa(TIPO_CONTRASSEGNO));
				cs.setValore(valoreContrassegno);
				spedizione.setContrassegnoValori(cs);
			}
			spedizioni.add(spedizione);
		}
		if (warningVolume) {
			DialogMessaggio.openWarning("Volume mancante", "Il volume risulta mancante per una o pi\u00F9 spedizioni, \u00E8 stato usato un valore di default dove necessario.");
		}
		return spedizioni;
	}
	
	/**
	 * Processo adibito al salvataggio delle spedizioni da simulare.
	 * @author Damiano
	 *
	 */
	private class ProcessoSalvataggioSpedizioni extends Processo {
		
		private static final String title = "Salvataggio dei dati sulle spedizioni da simulare";
		
		private final List<SpedizioneSimulazione> spedizioni;
		private final FileSpedizioni file;
		
		private boolean esito;

		public ProcessoSalvataggioSpedizioni(List<SpedizioneSimulazione> spedizioni, FileSpedizioni file) {
			super(title, spedizioni.size());
			this.spedizioni = spedizioni;
			this.file = file;
			this.esito = false;
		}
		
		public boolean getEsisto() {
			return esito;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			//crea il documento
			DocumentoSpedizioniSimulazione documento = new DocumentoSpedizioniSimulazione();
			documento.setDataImportazione(new Date());
			documento.setDescrizione(file.getNote());
			documento.setNomeFile(file.getNomeDocumento());
			documento.setTipo(file.getTipo().toString());
			boolean inserimentoDocumento = ControllerDocumentiSpedizioniSimulazione.getInstance().inserisci(documento);
			if (!inserimentoDocumento)
				throw new RuntimeException("Impossibile salvare il documento.");
			//salva le spedizioni
			int counter = 1;
			for (SpedizioneSimulazione spedizione : spedizioni) {
				spedizione.setIdDocumento(documento.getId());
				if (spedizione.getLetteraDiVettura() == null || spedizione.getLetteraDiVettura().isEmpty()) {
					spedizione.setLetteraDiVettura(documento.getId() + "_" + counter);
				}
				boolean inserimentoSpedizione = controllerSpedizioni.inserisci(spedizione);
				if (!inserimentoSpedizione)
					throw new RuntimeException("Impossibile salvare la spedizione. (riga " + counter + ")");
				else if (spedizione.getContrassegno()) {
					ContrassegnoSimulazione cs = spedizione.getContrassegnoValori();
					boolean inserimentoContrassegno = controllerContrassegni.inserisci(cs);
					if (!inserimentoContrassegno)
						System.out.println("Impossibile inserire il contrassegno. (riga " + counter + ")");
				}
				counter++;
				aumentaProgresso(1);
			}
			esito = true;
		}
		
	}
	
	public boolean importaSpedizioni(List<SpedizioneSimulazione> spedizioni, FileSpedizioni file) {
		ProcessoSalvataggioSpedizioni processo = new ProcessoSalvataggioSpedizioni(spedizioni, file);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		//restituisci l'esito delle operazioni
		return processo.getEsisto();
	}

}

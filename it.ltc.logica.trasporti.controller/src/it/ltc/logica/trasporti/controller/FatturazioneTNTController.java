package it.ltc.logica.trasporti.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.utility.csv.FileCSV;
import it.ltc.utility.microsoft.converter.XLStoCSV;

/**
 * Controller singleton che si occupa di importare un file .csv con i dati di fatturazione di TNT.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class FatturazioneTNTController {
	
	private static FatturazioneTNTController instance;
	
	private final ControllerCap controllerCap;
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerGiacenze controllerGiacenze;
	
	private final SimpleDateFormat sdf;
	
//	private int indiceCodiceCliente;
//	private int indiceLetteraDiVettura;
//	private int indiceRagioneSocialeMittente;
//	private int indiceIndirizzoMittente;
//	private int indiceLocalitaMittente;
//	private int indiceProvinciaMittente;
//	private int indiceRagioneSocialeDestinatario;
//	private int indiceIndirizzoDestinatario;
//	private int indiceLocalitaDestinatario;
//	private int indiceProvinciaDestinatario;
//	private int indiceDataPartenza;
//	private int indiceNumeroColli;
//	private int indicePeso;
//	private int indicePesoRiscontrato;
//	private int indiceVolume;
//	private int indiceImponibile;
//	private int indiceConsegnaPrivati;
//	private int indiceConsegnaPiano;
//	private int indiceContrassegno;
	//private int indiceGiorniGiacenza;
	
	private int[] esitoImportazione;
	
	private FatturazioneTNTController() {
		controllerCap = ControllerCap.getInstance();
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerGiacenze = ControllerGiacenze.getInstance();
		sdf = new SimpleDateFormat("yyyyMMdd");
	}
	
	public static FatturazioneTNTController getInstance() {
		if (instance == null) {
			instance = new FatturazioneTNTController();
		}
		return instance;
	}
	
	private class ProcessoImportazione extends Processo {
		
		private final static String title = "Importazione dei dati di fatturazione";
		
		private final FileCSV csv;

		public ProcessoImportazione(FileCSV csv) {
			super(title, csv.getRighe().size());
			this.csv = csv;
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			while (csv.prossimaRiga()) {
				boolean giacenza = isGiacenza(csv);
				if (giacenza) {
					aggiornaCostoGiacenza(csv);
				} else {
					Spedizione spedizione = recuperaSpedizione(csv);
					// Aggiorno il costo
					if (spedizione != null) {
						aggiornaCostoSpedizione(spedizione, csv);
						aggiornaIndirizzoPerConsegnaAlPiano(spedizione, csv);
						aggiornaIndirizzoPerConsegnaAPrivato(spedizione, csv);
					} else { // Inserisco la spedizione, viene anche controllato se è una giacenza
						inserisciNuovaSpedizione(csv);
					}
				}
				aumentaProgresso(1);
			}
		}
		
	}
	
	/**
	 * Importa le informazioni di fatturazione contenute nel .xls passato come argomento.
	 * L'array restituito contiene:
	 * 0 - il totale delle spedizioni e giacenze contenute nel file .xls
	 * 1 - il totale delle spedizioni di cui si è aggiornato il costo
	 * 2 - il totale delle giacenze di cui si è aggiornato il costo
	 * 3 - il totale degli indirizzi aggiornati
	 * 4 - il totale degli aggiornamenti/inserimenti falliti.
	 * 5 - il totale delle nuove spedizioni inserite
	 * @param fileCSV 
	 * @return un array contenente le informazioni sul risultato dell'importazione.
	 */
	public int[] importaXLSFatturazione(String pathFileXLS) {
		esitoImportazione = new int[6];
		File fileXLS = new File(pathFileXLS);
		FileCSV csv = XLStoCSV.getCSV(fileXLS);
		esitoImportazione[0] = csv.getRighe().size();
//		recuperaIndici(csv);
		
		ProcessoImportazione processo = new ProcessoImportazione(csv);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);	
		
		return esitoImportazione;
	}
	
//	/**
//	 * Permette di abbinare i giusti indici di colonna ai nomi/informazioni necessarie.
//	 * @param csv il file .csv
//	 */
//	private void recuperaIndici(FileCSV csv) {
//		indiceCodiceCliente = csv.getIndiceColonna("Codice Cliente");
//		indiceLetteraDiVettura = csv.getIndiceColonna("Lettera di Vettura");
//		indiceImponibile = csv.getIndiceColonna("Imponibile");
//		indiceConsegnaPrivati = csv.getIndiceColonna("Imp.addebito PRDEL");
//		indiceConsegnaPiano = csv.getIndiceColonna("Imp.addebito FLDEL");
//		//indiceGiorniGiacenza = csv.getIndiceColonna("Giorni giacenza");
//		indiceRagioneSocialeMittente = csv.getIndiceColonna("Ragione sociale mittente");
//		indiceIndirizzoMittente = csv.getIndiceColonna("Indirizzo mittente");
//		indiceLocalitaMittente = csv.getIndiceColonna("Localita' mittente");
//		indiceProvinciaMittente = csv.getIndiceColonna("Provincia mittente");
//		indiceRagioneSocialeDestinatario = csv.getIndiceColonna("Ragione sociale destinatario");
//		indiceIndirizzoDestinatario = csv.getIndiceColonna("Indirizzo destinatario");
//		indiceLocalitaDestinatario = csv.getIndiceColonna("Localita' destinatario");
//		indiceProvinciaDestinatario = csv.getIndiceColonna("Provincia destinatario");
//		indiceDataPartenza = csv.getIndiceColonna("Data partenza");
//		indiceNumeroColli = csv.getIndiceColonna("Numero Colli");
//		indicePeso = csv.getIndiceColonna("Peso da bolla");
//		indicePesoRiscontrato = csv.getIndiceColonna("Peso riscontrato");
//		indiceVolume = csv.getIndiceColonna("Volume");
//		indiceContrassegno = csv.getIndiceColonna("Importo c/assegno");
//	}
	
	private boolean isGiacenza(FileCSV csv) {
//		CampoCSV campoGiorniGiacenza = riga.getCampo(indiceGiorniGiacenza);
//		double giorniGiacenza = campoGiorniGiacenza.getValore();
//		return giorniGiacenza > 0;
		return false;
	}
	
	private Indirizzo inserisciDestinazione(FileCSV csv) {
		String localita = csv.getStringa("Localita' destinatario");
		Cap c = controllerCap.getCapDaLocalita(localita);
		String cap = c != null ? c.getCap() : "12345";
		Indirizzo destinazione = new Indirizzo();
		destinazione.setCap(cap);
		destinazione.setIndirizzo(csv.getStringa("Indirizzo destinatario"));
		destinazione.setLocalita(localita);
		destinazione.setNazione("ITA");
		destinazione.setProvincia(csv.getStringa("Provincia destinatario"));
		destinazione.setRagioneSociale(csv.getStringa("Ragione sociale destinatario"));
		boolean inserimentoDestinazione = controllerIndirizzi.inserisciSenzaProgressDialog(destinazione);
		if (!inserimentoDestinazione) {
			System.out.println("(DEBUG) Indirizzo di destinazione non inserito!");
			throw new RuntimeException("Impossibile inserire l'indirizzo di destinazione.");
		}
		return destinazione;
	}
	
	private Indirizzo inserisciPartenza(FileCSV csv) {
		String localita = csv.getStringa("Localita' mittente");
		Cap c = controllerCap.getCapDaLocalita(localita);
		String cap = c != null ? c.getCap() : "12345";
		Indirizzo mittente = new Indirizzo();
		mittente.setCap(cap);
		mittente.setIndirizzo(csv.getStringa("Indirizzo mittente"));
		mittente.setLocalita(localita);
		mittente.setNazione("ITA");
		mittente.setProvincia(csv.getStringa("Provincia mittente"));
		mittente.setRagioneSociale(csv.getStringa("Ragione sociale mittente"));
		boolean inserimentoDestinazione = controllerIndirizzi.inserisciSenzaProgressDialog(mittente);
		if (!inserimentoDestinazione) {
			System.out.println("(DEBUG) Indirizzo di partenza non inserito!");
			throw new RuntimeException("Impossibile inserire l'indirizzo di partenza.");
		}
		return mittente;
	}
	
	/**
	 * Controlla se la spedizione riportata nella riga sia una giacenza:
	 * Se è una giacenza viene aggiornato il costo della giacenza indicata.
	 * Se invece è una spedizione non presente a sistema viene inserita e poi si notifica all'utente.
	 * @param riga la riga csv che contiene le info sulla spedizione
	 * @return la spedizione appena inserita
	 */
	private Spedizione inserisciNuovaSpedizione(FileCSV csv) {
		Spedizione nuovaSpedizione;
		//Ricava o inserisci gli indirizzi di partenza e destinazione
		//Recupero il cap
		try {
			//Destinazione
			Indirizzo destinazione = inserisciDestinazione(csv);
			//Partenza
			Indirizzo partenza = inserisciPartenza(csv);
			//Spedizione
			nuovaSpedizione = new Spedizione();
			nuovaSpedizione.setCodiceCliente(csv.getStringa("Codice Cliente"));
			int colli = csv.getIntero("Numero Colli");
			nuovaSpedizione.setColli(colli);
			Date dataPartenza = sdf.parse(csv.getStringa("Data partenza"));
			nuovaSpedizione.setDataPartenza(dataPartenza);
			nuovaSpedizione.setIdCommessa(32);
			nuovaSpedizione.setFatturazione(Fatturazione.NON_FATTURABILE);
			nuovaSpedizione.setAssicurazione(false);
			nuovaSpedizione.setContrassegno(false);
			double costo = csv.getNumerico("Imponibile");
			nuovaSpedizione.setCosto(costo);
			nuovaSpedizione.setDatiCompleti(false);
			nuovaSpedizione.setGiacenza(false);
			nuovaSpedizione.setIdCorriere(2);
			nuovaSpedizione.setIdDocumento(2); //Non è rilevante.
			nuovaSpedizione.setIndirizzoDestinazione(destinazione.getId());
			nuovaSpedizione.setIndirizzoPartenza(partenza.getId());
			nuovaSpedizione.setInRitardo(false);
			String ldv = csv.getStringa("Lettera di Vettura");
			nuovaSpedizione.setLetteraDiVettura(ldv);
			nuovaSpedizione.setParticolarita(false);
			double peso = csv.getNumerico("Peso da bolla");
			double pesoRiscontrato = csv.getNumerico("Peso riscontrato");
			nuovaSpedizione.setPeso(peso > pesoRiscontrato ? peso : pesoRiscontrato);
			Double volume = csv.getNumerico("Volume");
			nuovaSpedizione.setVolume(volume > 0 ? volume : 0.001);
			nuovaSpedizione.setPezzi(0);
			nuovaSpedizione.setRagioneSocialeDestinatario(csv.getStringa("Ragione sociale destinatario"));
			nuovaSpedizione.setRiferimentoCliente(ldv);
			nuovaSpedizione.setRiferimentoMittente(ldv);
			nuovaSpedizione.setServizio("DEF"); //Non è sempre possibile desumerlo, le codifiche sono molto diverse fra loro.
			nuovaSpedizione.setStato("IMP"); //Importato
			nuovaSpedizione.setTipo(TipoSpedizione.ITALIA); //Fisso
			Double contrassegno = csv.getNumerico("Importo c/assegno");
			nuovaSpedizione.setContrassegno(contrassegno > 0);
			boolean inserimentoSpedizione = controllerSpedizioni.inserisciSenzaProgressDialog(nuovaSpedizione);
			//Contrassegno
			if (inserimentoSpedizione && contrassegno != null && contrassegno > 0) {
				Contrassegno nuovoContrassegno = new Contrassegno();
				nuovoContrassegno.setIdSpedizione(nuovaSpedizione.getId());
				nuovoContrassegno.setTipo("NA"); //Non specificato, non è riportato da TNT nel foglio di fatturazione.
				nuovoContrassegno.setValore(contrassegno);
				nuovoContrassegno.setValuta("EUR");
			}
			if (inserimentoSpedizione)
				esitoImportazione[5] += 1;
			else
				esitoImportazione[4] += 1;
		} catch (Exception e) {
			nuovaSpedizione = null;
			esitoImportazione[4] += 1;
		}
		return nuovaSpedizione;
	}
	
	private boolean aggiornaCostoSpedizione(Spedizione spedizione, FileCSV csv) {
		double imponibile = getImponibile(csv);
		spedizione.setCosto(imponibile);
		boolean update = controllerSpedizioni.aggiornaSenzaProgressDialog(spedizione);
		if (update)
			esitoImportazione[1] += 1;
		else
			esitoImportazione[4] += 1;
		return update;
	}
	
	private boolean aggiornaCostoGiacenza(FileCSV csv) {
		boolean update = false;
		String letteraDiVettura = csv.getStringa("Lettera di Vettura");
		double imponibile = getImponibile(csv);
		for (Giacenza g : controllerGiacenze.getGiacenze()) {
			String ldv = g.getLetteraDiVettura();
			if (ldv != null && ldv.equalsIgnoreCase(letteraDiVettura)) {
				g.setCosto(imponibile);
				update = controllerGiacenze.aggiornaSenzaProgressDialog(g);
				break;
			}
		}
		if (update)
			esitoImportazione[2] += 1;
		else
			esitoImportazione[4] += 1;
		return update;
	}
	
	private boolean aggiornaIndirizzoPerConsegnaAlPiano(Spedizione spedizione, FileCSV csv) {
		boolean update = false;
		Double costoConsegnaPiano = csv.getNumerico("Imp.addebito FLDEL");
		Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
		if (destinazione != null && costoConsegnaPiano != null && costoConsegnaPiano > 0) {
			destinazione.setConsegnaAlPiano(true);
			update = controllerIndirizzi.aggiornaSenzaProgressDialog(destinazione);
			if (update)
				esitoImportazione[3] += 1;
			else
				esitoImportazione[4] += 1;
		} else {
			esitoImportazione[4] += 1;
		}
		return update;
	}
	
	private boolean aggiornaIndirizzoPerConsegnaAPrivato(Spedizione spedizione, FileCSV csv) {
		boolean update = false;
		Double costoConsegnaPrivati = csv.getNumerico("Imp.addebito PRDEL");
		Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
		if (destinazione != null && costoConsegnaPrivati != null && costoConsegnaPrivati > 0) {
			destinazione.setConsegnaPrivato(true);
			update = controllerIndirizzi.aggiornaSenzaProgressDialog(destinazione);
			if (update)
				esitoImportazione[3] += 1;
			else
				esitoImportazione[4] += 1;
		} else {
			esitoImportazione[4] += 1;
		}
		return update;
	}

	private Spedizione recuperaSpedizione(FileCSV csv) {
		String codiceCliente = csv.getStringa("Codice Cliente");
		String letteraDiVettura = csv.getStringa("Lettera di Vettura");
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setLetteraDiVettura(letteraDiVettura);
		criteri.setCodiceCliente(codiceCliente);
		List<Spedizione> listaSpedizioni = controllerSpedizioni.selezionaSpedizioniSenzaProgressDialog(criteri);
		Spedizione spedizione = listaSpedizioni.size() == 1 ? listaSpedizioni.get(0) : null;
		return spedizione;
	}
	
	private double getImponibile(FileCSV csv) {
		Double valore = csv.getNumerico("Imponibile");
		double imponibile = valore != null ? valore : 0.0;
		return imponibile;
	}

}

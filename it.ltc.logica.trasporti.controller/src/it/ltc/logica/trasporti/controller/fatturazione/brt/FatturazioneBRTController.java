package it.ltc.logica.trasporti.controller.fatturazione.brt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;
import it.ltc.utility.miscellanea.string.StringUtility;

public class FatturazioneBRTController {
	
	private static FatturazioneBRTController instance;
	
	private static final int[] indiciVociDAT = {89, 103, 117, 131, 145, 159, 173, 187};
	
	private final StringUtility su;
	private final SimpleDateFormat sdf;
	private int[] esitoImportazione;
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerContrassegni controllerContrassegni;
	private final ControllerGiacenze controllerGiacenze;
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerCap controllerCap;
	
	private FatturazioneBRTController() {
		su = new StringUtility("0", "0", true, true);
		sdf = new SimpleDateFormat("yyyyMMdd");
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerGiacenze = ControllerGiacenze.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerCap = ControllerCap.getInstance();
	}
	
	public static FatturazioneBRTController getInstance() {
		if (instance == null) {
			instance = new FatturazioneBRTController();
		}
		return instance;
	}
	
	private class ProcessoImportazioneDocumento extends Processo {
		
		private static final String title = "Importazione dei dati di fatturazione";

		private final DocumentoFatturazione documento;
		
		public ProcessoImportazioneDocumento(DocumentoFatturazione documento) {
			super(title, documento.getRighe().size());
			this.documento = documento;
			esitoImportazione[0] = documento.getRighe().size();
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			for (RigaFatturazione riga : documento.getRighe()) {
				if (riga.isGiacenza()) {
					aggiornaCostoGiacenza(riga);
				} else {
					Spedizione spedizione = recuperaSpedizione(riga);
					if (spedizione != null) {
						aggiornaCostoSpedizione(spedizione, riga);
						aggiornaIndirizzo(spedizione, riga);
						aggiornaCAP(spedizione, riga);
					} else { //Inserisco la spedizione, viene anche controllato se è una giacenza
						inserisciNuovaSpedizione(riga);
					}
				}
				aumentaProgresso(1);
			}
		}
		
	}
	
	/**
	 * Importa il file passato come argomento seguendo procedure diverse in base alla tipologia.
	 * L'array restituito contiene:
	 * 0 - il totale delle spedizioni e giacenze contenute nel file .xls
	 * 1 - il totale delle spedizioni di cui si è aggiornato il costo
	 * 2 - il totale delle giacenze di cui si è aggiornato il costo
	 * 3 - il totale degli indirizzi aggiornati
	 * 4 - il totale degli aggiornamenti/inserimenti falliti.
	 * 5 - il totale delle nuove spedizioni inserite
	 * @param path il percorso del file sul computer.
	 * @return un array contenente le informazioni sul risultato dell'importazione.
	 */
	public int[] importaDocumentoFatturazione(String path) {
		esitoImportazione = new int[6];		
		int truncationIndex = path.lastIndexOf('.');
		if (truncationIndex != -1) {
			String estensione = path.substring(truncationIndex + 1).toUpperCase();
			switch (estensione) {
				case "DAT" : esitoImportazione = importaDATFatturazione(path); break;
				case "CSV" : esitoImportazione = importaCSVFatturazione(path); break;
			}
		}
		
		return esitoImportazione;
	}
	
	public int[] importaCSVFatturazione(String path) {
		List<String> righe = leggiDocumento(path);
		HashMap<String, Integer> mappaColonne = new HashMap<String, Integer>();
		//Crea la mappa dei nomi delle colonne
		String[] colonne = righe.remove(0).split(";");
		for (int index = 0; index < colonne.length; index++) {
			colonne[index] = colonne[index].replaceAll("\"", "");
			mappaColonne.put(colonne[index], index);
		}
		//Leggi le righe ed elabora il documento
		DocumentoFatturazione documento = new DocumentoFatturazione();
		for (String riga : righe) {
			String[] campi = riga.split(";");
			for (int index = 0; index < campi.length; index++)
				campi[index] = campi[index].replaceAll("\"", "");
			RigaFatturazione rf = leggiRigaCSV(mappaColonne, campi);
			documento.addRiga(rf);
		}
		
		ProcessoImportazioneDocumento processo = new ProcessoImportazioneDocumento(documento);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		
		return esitoImportazione;
	}
	
	public int[] importaDATFatturazione(String path) {
		//Leggi le righe ed elabora il documento
		DocumentoFatturazione documento = new DocumentoFatturazione();
		List<String> righe = leggiDocumento(path);
		for (String riga : righe) {
			RigaFatturazione rf = leggiRigaDAT(riga);
			documento.addRiga(rf);
		}
		
		ProcessoImportazioneDocumento processo = new ProcessoImportazioneDocumento(documento);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		
		return esitoImportazione;
	}
	
	private boolean aggiornaIndirizzo(Spedizione spedizione, RigaFatturazione riga) {
		boolean update = false;
		if (riga.isIndirizzoParticolare()) {
			Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
			if (destinazione != null) {
				destinazione.setConsegnaAlPiano(riga.isPiano());
				destinazione.setConsegnaGdo(riga.isGdo());
				update = controllerIndirizzi.aggiorna(destinazione);
				if (update)
					esitoImportazione[3] += 1;
				else
					esitoImportazione[4] += 1;
			} else {
				esitoImportazione[4] += 1;
			}
		}
		return update;
	}
	
	private boolean aggiornaCAP(Spedizione spedizione, RigaFatturazione riga) {
		boolean update = false;
		if (riga.isCAPParticolare()) {
			Indirizzo destinazione = controllerIndirizzi.getIndirizzo(spedizione.getIndirizzoDestinazione());
			if (destinazione != null) {
				String localita = destinazione.getLocalita();
				String cap = destinazione.getCap();
				Cap c = controllerCap.getInfoCap(cap, localita);
				if (c != null) {
					c.setBrtDisagiate(riga.isDisagiata());
					c.setBrtIsole(riga.isIsola());
					c.setBrtZtl(riga.isZtl());
					update = controllerCap.aggiorna(c);
					if (update)
						esitoImportazione[3] += 1;
					else
						esitoImportazione[4] += 1;
				} else {
					esitoImportazione[4] += 1;
				}
			} else {
				esitoImportazione[4] += 1;
			}	
		}
		return update;
	}
	
	/**
	 * Controlla se la spedizione riportata nella riga sia una giacenza:
	 * Se è una giacenza viene aggiornato il costo della giacenza indicata.
	 * Se invece è una spedizione non presente a sistema viene inserita e poi si notifica all'utente.
	 * @param riga la riga csv che contiene le info sulla spedizione
	 * @return la spedizione appena inserita
	 */
	private Spedizione inserisciNuovaSpedizione(RigaFatturazione riga) {
		//Ricava o inserisci gli indirizzi di partenza e destinazione
		Indirizzo destinazione = new Indirizzo();
		destinazione.setCap(riga.getCapDestinazione());
		destinazione.setConsegnaAlPiano(false);
		destinazione.setConsegnaAppuntamento(false);
		destinazione.setConsegnaGdo(false);
		destinazione.setConsegnaPrivato(false);
		destinazione.setIndirizzo(riga.getIndirizzoDestinazione());
		destinazione.setLocalita(riga.getLocalitaDestinazione());
		destinazione.setNazione(riga.getNazioneDestinazione());
		destinazione.setProvincia(riga.getProvinciaDestinazione());
		destinazione.setRagioneSociale(riga.getRagioneSociale());
		controllerIndirizzi.inserisciSenzaProgressDialog(destinazione);
		int idDestinazione = destinazione.getId();
//		if (idDestinazione < 1)
//			throw new RuntimeException("L'indirizzo non \u00E8 corretto, la spedizione non verr\u00E0 importata.");
		//Controllo sul codice cliente
		CodiceClienteCorriere codice = ControllerCodiciClienteCorriere.getInstance().getCodiceCliente(riga.getCodiceCliente());
//		if (codice == null)
//			throw new RuntimeException("Il codice cliente non esiste, la spedizione non verr\u00E0 importata. (" + riga.getCodiceCliente() + ")");
		Spedizione nuovaSpedizione;
		if (idDestinazione > 0 && codice != null) {
			//inserisci la spedizione
			nuovaSpedizione = new Spedizione();
			nuovaSpedizione.setCodiceCliente(riga.getCodiceCliente());
			nuovaSpedizione.setColli(riga.getColli());
			nuovaSpedizione.setPeso(riga.getPeso());
			nuovaSpedizione.setAssicurazione(false);
			boolean contrassegno = riga.getValoreContrassegno() > 0;
			nuovaSpedizione.setContrassegno(contrassegno);
			Date dataSpedizione;
			try {
				dataSpedizione = sdf.parse(riga.getDataSpedizione());
			} catch (Exception e) {
				dataSpedizione = new Date();
			}
			nuovaSpedizione.setDataPartenza(dataSpedizione);
			nuovaSpedizione.setDatiCompleti(true);
			nuovaSpedizione.setFatturazione(Spedizione.Fatturazione.FATTURABILE);
			nuovaSpedizione.setGiacenza(false); //TODO - nel file c'è, va inserita.
			nuovaSpedizione.setIdCommessa(codice.getCommessa());
			nuovaSpedizione.setIdCorriere(1); //FISSO a brt
			nuovaSpedizione.setIdDocumento(1); //non è rilevante, basta che esiste.
			nuovaSpedizione.setIndirizzoDestinazione(idDestinazione);
			nuovaSpedizione.setIndirizzoPartenza(1); //TODO -  recuperarlo dalla sede della commessa
			nuovaSpedizione.setInRitardo(false);
			nuovaSpedizione.setLetteraDiVettura(riga.getLetteraDiVettura()); //ricavarla da brt
			nuovaSpedizione.setParticolarita(false);
			nuovaSpedizione.setPezzi(0);
			nuovaSpedizione.setRiferimentoCliente(riga.getLetteraDiVettura());
			nuovaSpedizione.setRiferimentoMittente(riga.getLetteraDiVettura());
			nuovaSpedizione.setServizio("DEF"); //TODO - verificare se c'è
			nuovaSpedizione.setStato("IMP");
			nuovaSpedizione.setTipo(riga.getTipoSpedizione());
			nuovaSpedizione.setVolume(riga.getVolume());
			nuovaSpedizione.setCosto(riga.getImponibile());
			nuovaSpedizione.setRagioneSocialeDestinatario(riga.getRagioneSociale());
			//int idSpedizione = controllerSpedizioni.inserisciNuovaSpedizione(nuovaSpedizione);
			boolean inserimentoSpedizione = controllerSpedizioni.inserisciSenzaProgressDialog(nuovaSpedizione);
			if (inserimentoSpedizione)
				esitoImportazione[5] += 1;
			else
				esitoImportazione[4] += 1;		
			//inserisco il contrassegno, se presente.
			if (contrassegno) {
				Contrassegno c = new Contrassegno();
				c.setIdSpedizione(nuovaSpedizione.getId());
				c.setValore(riga.getValoreContrassegno());
				c.setTipo(riga.getTipoContrassegno());
				c.setValuta("EUR");
				controllerContrassegni.inserisciSenzaProgressDialog(c);
			}
		} else {
			nuovaSpedizione = null;
			esitoImportazione[4] += 1;	
		}		
		return nuovaSpedizione;
	}
	
	private Spedizione recuperaSpedizione(RigaFatturazione riga) {
		String letteraDiVettura = riga.getLetteraDiVettura();
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setLetteraDiVettura(letteraDiVettura);
		List<Spedizione> corrispondenza = controllerSpedizioni.selezionaSpedizioniSenzaProgressDialog(criteri);
		Spedizione spedizione = corrispondenza.size() == 1 ? corrispondenza.get(0) : null;
		return spedizione;
	}
	
	private boolean aggiornaCostoSpedizione(Spedizione spedizione, RigaFatturazione riga) {
		double imponibile = riga.getImponibile();
		spedizione.setCosto(imponibile);
		boolean update = controllerSpedizioni.aggiornaSenzaProgressDialog(spedizione);
		if (update)
			esitoImportazione[1] += 1;
		else
			esitoImportazione[4] += 1;
		return update;
	}
	
	private boolean aggiornaCostoGiacenza(RigaFatturazione riga) {
		boolean update = false;
		String letteraDiVettura = riga.getLetteraDiVettura();
		double imponibile = riga.getImponibile();
		Giacenza trovata = null;
		//Prima ricerca tra le giacenze
		for (Giacenza g : controllerGiacenze.getGiacenze()) {
			String ldv = g.getLetteraDiVettura();
			if (ldv != null && ldv.equalsIgnoreCase(letteraDiVettura)) {
				trovata = g;
				break;
			}
		}
		//Se non c'è cerca la spedizione originale (BRT crea una spedizione fittizia a volta per fatturare)
		if (trovata == null) {
			Spedizione spedizione = recuperaSpedizione(riga);
			if (spedizione != null && spedizione.getGiacenza()) {
				for (Giacenza g : controllerGiacenze.getGiacenze()) {
					String ldv = g.getLetteraDiVettura();
					if (ldv != null && ldv.equalsIgnoreCase(spedizione.getLetteraDiVettura())) {
						trovata = g;
						break;
					}
				}
			}
		}
		//Se dopo le ricerche la ho trovata allora l'aggiorno.
		if (trovata != null) {
			trovata.setCosto(imponibile);
			update = controllerGiacenze.aggiornaSenzaProgressDialog(trovata);
		}
		if (update)
			esitoImportazione[2] += 1;
		else
			esitoImportazione[4] += 1;
		return update;
	}
	
	private List<String> leggiDocumento(String path) {
		List<String> righe = new LinkedList<String>();
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				righe.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			//log error
		}
		return righe;
	}
	
	private RigaFatturazione leggiRigaDAT(String line) {
		String letteraDiVettura = getLetteraDiVetturaDAT(line);
		String codiceCliente = line.substring(28, 35);
		String riferimentoCliente = getRiferimentoCliente(line);
		String dataSpedizione = getDataSpedizioneDAT(line);
		double imponibile = getImponibileDAT(line);
		List<String> voci = getVociDAT(line);
		boolean piano = voci.contains("P");
		boolean gdo = voci.contains("S");
		boolean ztl = voci.contains("Q");
		boolean disagiata = voci.contains("K");
		boolean isola = voci.contains("J");
		boolean giacenza = voci.contains("I");
		RigaFatturazione riga = new RigaFatturazione(letteraDiVettura, codiceCliente, riferimentoCliente, dataSpedizione, imponibile, piano, gdo, ztl, disagiata, isola, giacenza);
		return riga;
	}
	
	private String getRiferimentoCliente(String line) {
		String riferimentoCliente = line.substring(262, 277);
		while (!riferimentoCliente.isEmpty() && riferimentoCliente.charAt(0) == '0')
			riferimentoCliente = riferimentoCliente.substring(1);
		return riferimentoCliente;
	}
	
	private String getLetteraDiVetturaDAT(String line) {
		String filialePartenza = line.substring(6, 9);
		String numeroSerie = line.substring(10, 12);
		String numeroSpedizione = line.substring(13, 20);
		String letteraDiVettura = filialePartenza + numeroSerie + numeroSpedizione;
		return letteraDiVettura;
	}
	
	private String getDataSpedizioneDAT(String line) {
		String annoSpedizione = line.substring(1, 5);
		String meseGiornoSpedizione = line.substring(23, 27);
		String dataSpedizione = annoSpedizione + meseGiornoSpedizione;
		return dataSpedizione;
	}
	
	private double getImponibileDAT(String line) {
		String sImponibile = line.substring(215, 229);
		sImponibile = sImponibile.replace(',', '.');
		double imponibile = Double.parseDouble(sImponibile);
		return imponibile;
	}
	
	private List<String> getVociDAT(String line) {
		List<String> voci = new LinkedList<String>();
		//int[] indiciVociDAT = {89, 103, 117, 131, 145, 159, 173, 187};
		for (int indice : indiciVociDAT) {
			String voce = line.substring(indice, indice + 1).trim();
			if (!voce.isEmpty())
				voci.add(voce.toUpperCase());
		}
		return voci;
	}
	
	private RigaFatturazione leggiRigaCSV(HashMap<String, Integer> mappaColonne, String[] fields) {
		String letteraDiVettura = getLetteraDiVetturaCSV(mappaColonne, fields);
		String codiceCliente = fields[mappaColonne.get("VAFKSC")];
		String riferimentoCliente = fields[mappaColonne.get("VAFRMN")];
		String dataSpedizione = getDataSpedizioneCSV(mappaColonne, fields);
		codiceCliente = su.getFormattedString(codiceCliente, 7);
		double imponibile = getImponibileCSV(mappaColonne, fields);
		List<String> voci = getVociCSV(mappaColonne, fields);
		boolean piano = voci.contains("P");
		boolean gdo = voci.contains("S");
		boolean ztl = voci.contains("Q");
		boolean disagiata = voci.contains("K");
		boolean isola = voci.contains("J");
		boolean giacenza = voci.contains("I");
		String numeroColli = fields[mappaColonne.get("VAFNCL")];
		if (numeroColli.isEmpty())
			numeroColli = "1";
		int colli = Integer.parseInt(numeroColli);
		
		String pesoSpedizione = fields[mappaColonne.get("VAFPKB")];
		if (pesoSpedizione.isEmpty())
			pesoSpedizione = "0.0";
		pesoSpedizione = pesoSpedizione.replace(',', '.');
		double peso = Double.parseDouble(pesoSpedizione);
		
		String pesoSpedizioneReale = fields[mappaColonne.get("VAFPKC")];
		if (pesoSpedizioneReale.isEmpty())
			pesoSpedizioneReale = "0.0";
		pesoSpedizioneReale = pesoSpedizioneReale.replace(',', '.');
		double pesoReale = Double.parseDouble(pesoSpedizioneReale);
		if (pesoReale > 0)
			peso = pesoReale;
		
		String volumeSpedizione = fields[mappaColonne.get("VAFVLF")];
		if (volumeSpedizione.isEmpty())
			volumeSpedizione = "0.0";
		volumeSpedizione = volumeSpedizione.replace(',', '.');
		double volume = Double.parseDouble(volumeSpedizione);
		String volumeSpedizioneReale = fields[mappaColonne.get("VAFVLC")];
		if (volumeSpedizioneReale.isEmpty())
			volumeSpedizioneReale = "0.0";
		volumeSpedizioneReale = volumeSpedizioneReale.replace(',', '.');
		double volumeReale = Double.parseDouble(volumeSpedizioneReale);
		if (volumeReale > 0)
			volume = volumeReale;
		
		//String valContrassegno = fields[mappaColonne.get("VAFCAS")]; //TODO
		double valoreContrassegno = 0;
		String tipoContrassegno = "SC"; 
		String cap = fields[mappaColonne.get("VAFCAD")];
		cap = su.getFormattedString(cap, 5); //A volte tolgono gli 0 all'inizio.
		String localita = fields[mappaColonne.get("VAFLOD")];
		String provincia = fields[mappaColonne.get("VAFPRD")];
		String indirizzo = fields[mappaColonne.get("VAFIND")];
		String ragioneSociale = fields[mappaColonne.get("VAFRSD")];
		String nazione = fields[mappaColonne.get("VAFNZD")];
		RigaFatturazione riga = new RigaFatturazione(letteraDiVettura, codiceCliente, riferimentoCliente, dataSpedizione, imponibile, piano, gdo, ztl, disagiata, isola, giacenza, colli, peso, volume, valoreContrassegno, tipoContrassegno, cap, localita, provincia, indirizzo, nazione, ragioneSociale);
		return riga;
	}
	
	private String getLetteraDiVetturaCSV(HashMap<String, Integer> mappaColonne, String[] fields) {
		String filialePartenza = fields[mappaColonne.get("VAFLNP")];
		filialePartenza = su.getFormattedString(filialePartenza, 3);
		String numeroSerie = fields[mappaColonne.get("VAFNRS")];
		numeroSerie = su.getFormattedString(numeroSerie, 2);
		String numeroSpedizione = fields[mappaColonne.get("VAFNSP")];
		numeroSpedizione = su.getFormattedString(numeroSpedizione, 7);
		String letteraDiVettura = filialePartenza + numeroSerie + numeroSpedizione;
		return letteraDiVettura;
	}
	
	private String getDataSpedizioneCSV(HashMap<String, Integer> mappaColonne, String[] fields) {
		String annoSpedizione = fields[mappaColonne.get("VAFAAS")];
		String meseGiornoSpedizione = fields[mappaColonne.get("VAFMGS")];
		meseGiornoSpedizione = su.getFormattedString(meseGiornoSpedizione, 4);
		String dataSpedizione = annoSpedizione + meseGiornoSpedizione;
		return dataSpedizione;
	}
	
	private double getImponibileCSV(HashMap<String, Integer> mappaColonne, String[] fields) {
		String sImponibile = fields[mappaColonne.get("VAFIMV")];
		sImponibile = sImponibile.replace(',', '.');
		double imponibile = Double.parseDouble(sImponibile);
		return imponibile;
	}
	
	private List<String> getVociCSV(HashMap<String, Integer> mappaColonne, String[] fields) {
		List<String> voci = new LinkedList<String>();
		for (int index = 1; index < 9; index++) {
			String key = "VAFSV" + index;
			String voce = fields[mappaColonne.get(key)].trim();
			if (!voce.isEmpty())
				voci.add(voce.toUpperCase());
		}
		return voci;
	}
	
	private class DocumentoFatturazione {
		
		private final List<RigaFatturazione> righe;
		
		DocumentoFatturazione() {
			righe = new LinkedList<RigaFatturazione>();
		}
		
		public void addRiga(RigaFatturazione riga) {
			righe.add(riga);
		}
		
		List<RigaFatturazione> getRighe() {
			return righe;
		}
	}

}

package it.ltc.logica.utilities.excel.model.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che mappa un file .csv
 * 
 * @author Damiano
 *
 */
public class FileCSV {
	
	public static final String DEFAULT_CSV_SEPARATOR = ";";
	public static final int MAX_FIELD_LIMIT = 100;
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	private final SimpleDateFormat sdf;
	
	private final HashMap<String, Integer> mappaColonne;
	private final String intestazione;
	private final ArrayList<String[]> righe;
	
	private int rigaAttuale;

	public FileCSV(HashMap<String, Integer> mappaColonne, String intestazione, ArrayList<String[]> righe) {
		this(mappaColonne, intestazione, righe, DEFAULT_DATE_FORMAT);
	}
	
	public FileCSV(HashMap<String, Integer> mappaColonne, String intestazione, ArrayList<String[]> righe, String dateFormat) {
		this.mappaColonne = mappaColonne;
		this.intestazione = intestazione;
		this.righe = righe;
		this.rigaAttuale = -1;
		this.sdf = new SimpleDateFormat(dateFormat);
	}

	public int getRigaAttuale() {
		return rigaAttuale;
	}

	public void setRigaAttuale(int rigaAttuale) {
		this.rigaAttuale = rigaAttuale;
	}
	
	/**
	 * Controlla se tutte le colonne indicate sono presenti nel file csv
	 * @param nomiColonne un array di stringhe che rappresentano le colonne necessarie.
	 * @return true se ci sono tutte, false altrimenti.
	 */
	public List<String> checkColonne(String... nomiColonne) {
		List<String> colonneMancanti = new LinkedList<>();
		for (String colonna : nomiColonne) {
			if (!mappaColonne.containsKey(colonna)) {
				colonneMancanti.add(colonna);
			}
		}
		return colonneMancanti;
	}

	/**
	 * Restituisce un HashMap che contiene i nomi delle colonne e l'indice 0 based collegato.
	 * @return la mappa nomi colonne / indici.
	 */
	public HashMap<String, Integer> getMappaColonne() {
		return mappaColonne;
	}

	/**
	 * Restituisce la riga di intestazione del file .csv, qui vi sono contenuti i nomi delle colonne usualmente.
	 * @return la prima riga del file .csv
	 */
	public String getIntestazione() {
		return intestazione;
	}

	/**
	 * Restituisce la lista delle righe contenute nel file dove sono presenti i valori da processare.
	 * @return una list contenente array di stringhe. Ogni array contiene i valori della riga.
	 */
	public List<String[]> getRighe() {
		return righe;
	}
	
	/**
	 * Restituisce l'indice della colonna specificata, <code>null</code> se non esiste.
	 * @param colonna
	 * @return
	 */
	public Integer getIndiceColonna(String colonna) {
		return mappaColonne.get(colonna);
	}
	
	public boolean prossimaRiga() {
		rigaAttuale++;
		return rigaAttuale < righe.size();
	}
	
	public String getStringa(String colonna) {
		Integer indexColonna = mappaColonne.get(colonna);
		return indexColonna != null ? getStringa(indexColonna, rigaAttuale) : null;
	}
	
	public String getStringa(int colonna, int numeroRiga) {
		return righe.get(numeroRiga)[colonna];
	}
	
	public Double getNumerico(String colonna) {
		Integer indexColonna = mappaColonne.get(colonna);
		return indexColonna != null ? getNumerico(indexColonna, rigaAttuale) : null;
	}
	
	public Double getNumerico(int colonna, int numeroRiga) {
		Double valore;
		try {
			StringBuilder value = new StringBuilder(righe.get(numeroRiga)[colonna]);
			//value = value.replace(',', '.');
			for (int index = 0; index < value.length(); index++) {
				char c = value.charAt(index);
				switch (c) {
					case '0' : case '1' : case '2' : case '3' : case '4' :case '5' :case '6' :case '7' :case '8' :case '9' : break;
					case ',' : value.setCharAt(index, '.'); break;
					default : value.deleteCharAt(index); index--; break; //Se elimino un carattere riporto l'indice indietro di 1.
				}
			}
			valore = Double.parseDouble(value.toString());
		} catch (Exception e) { valore = null; }
		return valore;
	}
	
	public Integer getIntero(String colonna) {
		Integer indexColonna = mappaColonne.get(colonna);
		return indexColonna != null ? getIntero(indexColonna, rigaAttuale) : null;
	}
	
	public Integer getIntero(int colonna, int numeroRiga) {
		Integer valore;
		try {
			valore = Integer.parseInt(righe.get(numeroRiga)[colonna].replace(',', '.'));
		} catch (Exception e) { valore = null; }
		return valore;
	}
	
	public Boolean getBooleano(String colonna) {
		Integer indexColonna = mappaColonne.get(colonna);
		return indexColonna != null ? getBooleano(indexColonna, rigaAttuale) : null;
	}
	
	public Boolean getBooleano(int colonna, int numeroRiga) {
		Boolean valore;
		try {
			valore = Boolean.parseBoolean(righe.get(numeroRiga)[colonna]);
		} catch (Exception e) { valore = null; }
		return valore;
	}
	
	public Date getData(String colonna) {
		Integer indexColonna = mappaColonne.get(colonna);
		return indexColonna != null ? getData(indexColonna, rigaAttuale) : null;
	}
	
	public Date getData(int colonna, int numeroRiga) {
		Date valore;
		try {
			valore = sdf.parse(righe.get(numeroRiga)[colonna]);
		} catch (Exception e) { valore = null; }
		return valore;
	}
	
	/**
	 * Legge il file .csv passato come argomento e restituisce l'oggetto che lo mappa.
	 * Potrebbero generarsi errori di I/O da gestire.
	 * Verrà eseguito automaticamente un trim di spazi sui nomi dei campi e i valori e verranno eliminate le virgolette.
	 * @param file Il file da leggere e mappare.
	 * @return un oggetto che mappa il file .csv.
	 * @throws Exception errori di I/O da gestire.
	 */
	public static FileCSV leggiFile(File file) throws Exception {
		return leggiFile(file, true, DEFAULT_CSV_SEPARATOR, DEFAULT_CSV_SEPARATOR);
	}

	/**
	 * Legge il file .csv passato come argomento e restituisce l'oggetto che lo mappa.
	 * Potrebbero generarsi errori di I/O da gestire.
	 * @param file Il file da leggere e mappare.
	 * @param trim specifica se i spazi e le virgolette nei titoli di colonna e dentro i campi di valore devono essere eliminati.
	 * @return un oggetto che mappa il file .csv.
	 * @throws Exception errori di I/O da gestire.
	 */
	public static FileCSV leggiFile(File file, boolean trim, String headerSeparator, String fieldSeparator) throws Exception {
		// Variabili d'appoggio
		HashMap<String, Integer> mappaColonne;
		String intestazione;
		ArrayList<String[]> righe = new ArrayList<String[]>();
		// Lettura del file
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = reader.readLine();
		if (line != null) {
			intestazione = line;
			line = reader.readLine();
		} else {
			intestazione = null;
		}
		while (line != null) {
			String[] valori = getValori(line, trim, fieldSeparator);
			righe.add(valori);
			line = reader.readLine();
		}
		reader.close();
		//Verifiche e mappaggio
		if (intestazione == null || intestazione.isEmpty())
			throw new RuntimeException("Il file .csv è vuoto. (intestazione vuota)");
		if (righe.isEmpty())
			throw new RuntimeException("Il file .csv non è stato valorizzato. (nessuna riga)");
		mappaColonne = creaMappa(intestazione, trim, headerSeparator);
		//Creazione oggetto
		FileCSV csv = new FileCSV(mappaColonne, intestazione, righe);
		return csv;
	}
	
	private static String[] getValori(String line, boolean trim, String separator) {
		String[] valori = line.split(separator, MAX_FIELD_LIMIT);
		if (trim) {
			for (int index = 0; index < valori.length; index++) {
				valori[index] = valori[index].replaceAll("\"", ""); //Elimina le virgolette "
				valori[index] = valori[index].trim();
			}
		}
		return valori;
	}
	
	private static HashMap<String, Integer> creaMappa(String intestazione, boolean trim, String separator) throws Exception {
		HashMap<String, Integer> mappaColonne = new HashMap<String, Integer>();
		String[] nomiColonne = intestazione.split(separator);
		if (nomiColonne.length < 2)
			throw new RuntimeException("Il separatore usato ha generato una sola colonna per il file .csv, verificare!");
		for (int index = 0; index < nomiColonne.length; index ++) {
			String nomeColonna = nomiColonne[index];
			if (trim) {
				nomeColonna = nomeColonna.replaceAll("\"", "");
				nomeColonna = nomeColonna.trim();
			}
			mappaColonne.put(nomeColonna, index);
		}
		return mappaColonne;
	}

}

//public class FileCSV {
//	
//	public static final String DEFAULT_SEPARATOR = ",";
//	
//	protected HashMap<String, Integer> mappaColonne;
//	protected String[] colonne;
//	protected final List<RigaCSV> righe;
//	
//	protected final String separator;
//	
//	public FileCSV(String[] nomiColonne) {
//		this.separator = DEFAULT_SEPARATOR;
//		colonne = nomiColonne.clone();
//		righe = new ArrayList<RigaCSV>();
//	}
//	
//	public FileCSV(File fileCSV) {
//		this(fileCSV, DEFAULT_SEPARATOR);
//	}
//	
//	public FileCSV(File fileCSV, String separator) {
//		this.separator = separator;
//		righe = new ArrayList<RigaCSV>();
//		try {
//			FileReader fis = new FileReader(fileCSV);
//			BufferedReader reader = new BufferedReader(fis);
//			String line = reader.readLine();
//			if (line != null) {
//				colonne = line.split(separator);
//				line = reader.readLine();
//				while (line != null) {
//					RigaCSV riga = leggiRiga(line);
//					righe.add(riga);
//					line = reader.readLine();
//				}
//			}
//			reader.close();
//		} catch (IOException e) {
//			colonne = null;
//		}
//	}
//	
//	public String[] getColonne() {
//		return colonne;
//	}
//	
//	/**
//	 * Restituisce l'indice della prima colonna trovata che si chiama come l'argomento.
//	 * Maiuscole o minuscole sono indifferenti.
//	 * @param nomeColonna il nome della colonna da cercare.
//	 * @return l'indice della colonna oppure -1 se non ne esiste una con quel nome.
//	 */
//	public int getIndiceColonna(String nomeColonna) {
//		int columnIndex = -1;
//		if (nomeColonna != null) //null check
//		for (int index = 0; index < colonne.length; index++) {
//			if (colonne[index].equalsIgnoreCase(nomeColonna)) {
//				columnIndex = index;
//				break;
//			}
//		}
//		return columnIndex;
//	}
//	
//	public List<RigaCSV> getRighe() {
//		return righe;
//	}
//	
//	public void aggiungiRiga(RigaCSV riga) {
//		righe.add(riga);
//	}
//	
//	protected RigaCSV leggiRiga(String riga) {
//		RigaCSV rigaCSV = new RigaCSV(colonne);
//		String[] valori = riga.split(separator);
//		int index = 0; 
//		for (String valore : valori) {
//			CampoCSV campo = new CampoCSV(colonne[index], index, valore);
//			rigaCSV.setCampo(index, campo);
//			index++;
//		}
//		return rigaCSV;
//	}
//
//}

package it.ltc.logica.common.controller.processi.sincronizzazione;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import it.ltc.logica.common.controller.processi.ProcessoWebService;
import it.ltc.logica.common.ws.RestClient;

public class ProcessoSincronizzazioneDati<T> extends ProcessoWebService {

	protected static final String PREFISSO_TITOLO = "Caricamento dati: ";

	private final String resource;
	private final Class<T[]> c;
	private final CriteriUltimaModifica criteri;
	
	private final CRUDDaoConProcessi<T> dao;
	
	private int status;
	
	public ProcessoSincronizzazioneDati(String title, String resource, Class<T[]> c, CriteriUltimaModifica criteri, CRUDDaoConProcessi<T> dao) {
		this(title, resource, c, criteri, dao, null, null, null);
	}
	
	public ProcessoSincronizzazioneDati(String title, String resource, Class<T[]> c, CriteriUltimaModifica criteri, CRUDDaoConProcessi<T> dao, String domain, String contextPath, String risorsaCommessa) {
		super(PREFISSO_TITOLO + title, -1, domain, contextPath, risorsaCommessa);
		this.resource = resource;
		this.c = c;
		this.status = -1;
		this.criteri = criteri;
		this.dao = dao;
	}
	
	public int getStatus() {
		return status;
	}

	public boolean getEsito() {
		return (status == 200 || status == 204 || status == 205);
	}
	
	@Override
	public void eseguiOperazioni() throws Exception {	
		RestClient client = getClient();
		T[] array = client.post(resource, criteri, c);
		status = client.getStatus();
		switch (status) {
			case 200 : aggiornaNormale(array); break;
			case 204 : break;
			case 205 : resetDati(); aggiornaNormale(array); break;
			default : throw new RuntimeException("Errore durante lo scaricamento: '" + client.getError() + "'");
		}
	}
	
	private void aggiornaNormale(T[] array) {
		List<T> lista = Arrays.asList(array);
		mergeDati(lista);
		Date dataUltimoAggiornamento = dao.trovaDataUltimoAggiornamento();
		criteri.setDataUltimaModifica(dataUltimoAggiornamento);
	}
	
	/**
	 * Metodo non utilizzato!<b>
	 * Da una stringa JSON parsa una lista di oggetti basandosi sulle librerie jackson.
	 * @param answer
	 * @param c
	 * @return
	 * @throws Exception
	 */
	protected List<T> parseAnswer(String answer, Class<T> c) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(LinkedList.class, c);
		LinkedList<T> lista = mapper.readValue(answer, listType);
		return lista;
	}
	
	/**
	 * Metodo non utilizzato!<b>
	 * Salva la risposta JSON a partire dallo stream nel path specificato.<br>
	 * Ci sarà un oggetto JSON per riga.
	 * @param s
	 * @param path
	 * @return
	 * @throws Exception
	 */
	protected int saveAnswer(InputStream s, String path) throws Exception {
		int records = 0;
		FileWriter output = new FileWriter(path);
		BufferedWriter writer = new BufferedWriter(output);
		JsonFactory f = new MappingJsonFactory();
		JsonParser jp = f.createParser(s);
		JsonToken current = jp.nextToken();
		if (current == JsonToken.START_ARRAY) {
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				JsonNode node = jp.readValueAsTree();
				String oggetto = node.toString();
				writer.append(oggetto);
				writer.newLine();
				records += 1;
			}
		}
		writer.flush();
		writer.close();
		return records;
	}
	
	/**
	 * Metodo non utilizzato!<b>
	 * Da un file con un oggetto JSON per riga ottiene una lista di oggetti.
	 * @param path
	 * @param c
	 * @return
	 * @throws Exception
	 */
	protected List<T> parseAnswer(File path, Class<T> c) throws Exception {
		List<T> lista = new LinkedList<>();
		FileReader input = new FileReader(path);
		BufferedReader reader = new BufferedReader(input);
		String oggetto = reader.readLine();
		ObjectMapper mapper = new ObjectMapper();
		while (oggetto != null) {
			T object = mapper.readValue(oggetto, c);
			lista.add(object);
			oggetto = reader.readLine();
		}
		reader.close();
		return lista;
	}
	
	/**
	 * Metodo non utilizzato!<b>
	 * Da una stringa JSON parsa una lista di oggetti uno alla volta sfruttando i token e la libreria jackson.
	 * @param s
	 * @param c
	 * @return
	 * @throws Exception
	 */
	protected List<T> parseAnswerWithToken(String s, Class<T> c) throws Exception {
		List<T> lista = new LinkedList<>();
		JsonFactory f = new MappingJsonFactory();
		JsonParser jp = f.createParser(s);
		JsonToken current = jp.nextToken();
		ObjectMapper mapper = new ObjectMapper();
		if (current == JsonToken.START_ARRAY) {
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				JsonNode node = jp.readValueAsTree();
				String oggetto = node.toString();
				T object = mapper.readValue(oggetto, c);
				lista.add(object);
			}
		}
		return lista;
	}
	
	private boolean resetDati() {
		boolean cancellazioneVecchiDati = dao.truncate();
		System.out.println("Cancellazione vecchi dati: " + cancellazioneVecchiDati);
		return cancellazioneVecchiDati;
	}
	
	private boolean mergeDati(List<T> lista) {
		boolean inserimento = dao.sincronizza(lista, this);
		System.out.println("Aggiornamento dati: " + inserimento);
		return inserimento;
	}

}

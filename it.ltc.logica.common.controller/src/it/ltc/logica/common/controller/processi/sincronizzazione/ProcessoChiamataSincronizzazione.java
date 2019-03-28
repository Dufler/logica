package it.ltc.logica.common.controller.processi.sincronizzazione;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.processi.ProcessoWebService;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.WSError;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public class ProcessoChiamataSincronizzazione<T> extends ProcessoWebService {
	
	protected static final String PREFISSO_TITOLO = "Caricamento dati: ";
	
	private final String resource;
	private final Class<T[]> c;
	private final CriteriUltimaModifica criteri;
	
	private T[] array;
	private int status;
	
	public ProcessoChiamataSincronizzazione(String title, String resource, Class<T[]> c, CriteriUltimaModifica criteri, Sede sede, Commessa commessa) {
		this(title, resource, c, criteri, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());
	}
	
	public ProcessoChiamataSincronizzazione(String title, String resource, Class<T[]> c, CriteriUltimaModifica criteri) {
		this(title, resource, c, criteri, null, null, null);
	}

	public ProcessoChiamataSincronizzazione(String title, String resource, Class<T[]> c, CriteriUltimaModifica criteri, String domain, String contextPath, String risorsaCommessa) {
		super(PREFISSO_TITOLO + title, -1, domain, contextPath, risorsaCommessa);
		this.resource = resource;
		this.c = c;
		this.criteri = criteri;
		this.status = -1;
	}
	
	public int getStatus() {
		return status;
	}

	public boolean getEsito() {
		return (status == 200 || status == 204 || status == 203);
	}
	
	public List<T> getEntities() {
		List<T> lista = array != null && array.length > 0  ? Arrays.asList(array) : new LinkedList<>();
		return lista;
	}

	@Override
	public void eseguiOperazioni() throws Exception {
		RestClient client = getClient();
		array = client.post(resource, criteri, c);
		status = client.getStatus();
		if (!getEsito()) {
			String message = "Errore durante lo scaricamento dei dati ";
			if (client.getStatus() == 400) {
				WSError errorMessage = client.getJSONError();
				if (errorMessage != null)
					message += ": " + errorMessage.toString();	
			} else {
				message += client.getError();
			}			
			throw new RuntimeException(message);
		}
	}

}

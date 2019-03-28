package it.ltc.logica.common.controller.processi;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.WSError;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public class ProcessoCaricamentoDato<T> extends ProcessoWebService {
	
	protected static final String PREFISSO_TITOLO = "Caricamento: ";
	
	private final String resource;
	private final Class<T> c;
	private T object;
	
	public ProcessoCaricamentoDato(String title, String resource, Class<T> c, Sede sede, Commessa commessa) {
		this(title, resource, c, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());
	}
	
	public ProcessoCaricamentoDato(String title, String resource, Class<T> c) {
		this(title, resource, c, null, null, null);
	}

	public ProcessoCaricamentoDato(String title, String resource, Class<T> c, String domain, String contextPath, String risorsaCommessa) {
		super(PREFISSO_TITOLO + title, -1, domain, contextPath, risorsaCommessa);
		this.resource = resource;
		this.c = c;
	}
	
	public T getObject() {
		return object;
	}
	
	@Override
	public void eseguiOperazioni() throws Exception {
		RestClient client = getClient();
		object = client.get(resource, c);
		if (!getEsito()) {
			String message = "Errore durante il caricamento dei dati ";
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

	@Override
	public boolean getEsito() {
		return object != null;
	}

}

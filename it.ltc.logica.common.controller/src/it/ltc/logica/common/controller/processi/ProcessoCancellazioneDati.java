package it.ltc.logica.common.controller.processi;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.WSError;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public class ProcessoCancellazioneDati<T> extends ProcessoWebService {
	
	protected static final String PREFISSO_TITOLO = "Eliminazione: ";

	private final String resource;
	private final Class<T> c;
	private T object;
	private boolean esito;
	private final boolean mapReturnValue;

	public ProcessoCancellazioneDati(String title, String resource, T object) {
		this(title, resource, object, true, null, null, null);
	}
	
	public ProcessoCancellazioneDati(String title, String resource, T object, Sede sede, Commessa commessa) {
		this(title, resource, object, true, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());
	}
	
	@SuppressWarnings("unchecked")
	public ProcessoCancellazioneDati(String title, String resource, T object, boolean mapReturnValue, String domain, String contextPath, String risorsaCommessa) {
		super(PREFISSO_TITOLO + title, -1, domain, contextPath, risorsaCommessa);
		this.resource = resource;
		this.object = object;
		this.c = (Class<T>) object.getClass();
		this.mapReturnValue = mapReturnValue;
	}
	
	public boolean getEsito() {
		return esito;
	}

	public T getObject() {
		return object;
	}
	
	@Override
	public void eseguiOperazioni() throws Exception {
		RestClient client = getClient();
		if (mapReturnValue)
			object = client.delete(resource, object, c);
		else
			client.delete(resource, object);
		esito = client.getStatus() == 200;
		if (!esito) {
			String message = "Errore durante l'eliminazione dati";
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

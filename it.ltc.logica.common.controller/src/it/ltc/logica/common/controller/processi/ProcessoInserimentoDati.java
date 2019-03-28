package it.ltc.logica.common.controller.processi;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.WSError;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public class ProcessoInserimentoDati<T> extends ProcessoWebService {
	
	protected static final String PREFISSO_TITOLO = "Inserimento: ";

	private final String resource;
	private final Class<T> c;
	private final boolean mapReturnValue;
	private T object;
	private boolean esito;
	
	public ProcessoInserimentoDati(String title, String resource, Sede sede, Commessa commessa, T object) {
		this(title, resource, object, true, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());
	}
	
	public ProcessoInserimentoDati(String title, String resource, T object) {
		this(title, resource, object, true, null, null, null);
	}
	
	@SuppressWarnings("unchecked")
	public ProcessoInserimentoDati(String title, String resource, T object, boolean mapReturnValue, String domain, String contextPath, String risorsaCommessa) {
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
			object = client.post(resource, object, c);
		else
			client.post(resource, object);
		esito = client.getStatus() == 201;
		if (!esito) {
			String message = "Errore durante l'inserimento dati";
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

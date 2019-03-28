package it.ltc.logica.common.controller.processi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.WSError;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public class ProcessoCaricamentoDati<T> extends ProcessoWebService {
	
	protected static final String PREFISSO_TITOLO = "Caricamento dati: ";

	private final String resource;
	private final List<T> lista;
	private final Class<T[]> c; 
	
	private int status;
	
	public ProcessoCaricamentoDati(String title, String resource, Class<T[]> c, Sede sede, Commessa commessa) {
		this(title, resource, c, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());
	}
	
	public ProcessoCaricamentoDati(String title, String resource, Class<T[]> c) {
		this(title, resource, c, null, null, null);
	}
	
	public ProcessoCaricamentoDati(String title, String resource, Class<T[]> c, String domain, String contextPath, String risorsaCommessa) {
		super(PREFISSO_TITOLO + title, -1, domain, contextPath, risorsaCommessa);
		this.resource = resource;
		this.c = c;
		this.lista = new LinkedList<>();
		this.status = -1;
	}
	
	public List<T> getLista() {
		return lista;
	}
	
	public int getStatus() {
		return status;
	}

	public boolean getEsito() {
		return (status == 200 || status == 204 || status == 203);
	}
	
	@Override
	public void eseguiOperazioni() throws Exception {
		RestClient client = getClient();
		T[] array =  client.get(resource, c);
		status = client.getStatus();
		if (array != null)
			lista.addAll(Arrays.asList(array));
		else
			System.out.println("DEBUG: Risposta vuota!?");
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

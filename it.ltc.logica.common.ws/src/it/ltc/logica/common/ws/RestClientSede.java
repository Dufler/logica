package it.ltc.logica.common.ws;

import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

/**
 * Client per fare richieste alla sede.
 * @author Damiano
 *
 */
public class RestClientSede extends RestClient {
	
	public RestClientSede() {
		this(null);
	}
	
	public RestClientSede(String risorsaCommessa) {
		super(null, CONTEXT_PATH_SEDE, null, risorsaCommessa);
	}
	
	@Override
	protected String getDefaultDomain() {
		String domain = ControllerVariabiliGlobali.getInstance().getString("indirizzo.server.sede");
		return domain;
	}
	
	@Override
	protected String getDefaultContextPath() {
		return CONTEXT_PATH_SEDE;
	}

}

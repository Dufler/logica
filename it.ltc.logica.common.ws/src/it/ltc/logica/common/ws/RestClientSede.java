package it.ltc.logica.common.ws;

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
		super(DOMAIN_SEDE, CONTEXT_PATH_SEDE, null, risorsaCommessa);
	}

}

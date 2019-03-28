package it.ltc.logica.common.controller.processi;

import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.task.Processo;

/**
 * Processo che chiama un web service.
 * @author Damiano
 *
 */
public abstract class ProcessoWebService extends Processo {
	
	protected final String domain;
	protected final String contextPath;
	protected final String risorsaCommessa;
	
	protected int status;
	
	/**
	 * Costruttore semplice a cui specificare solo il titolo e il numero di operazioni.
	 * @param title
	 * @param operations
	 */
	public ProcessoWebService(String title, Integer operations) {
		this(title, operations, null, null, null);
	}
	
	/**
	 * Costruttore adatto per recuperare le info da una commessa specifica.
	 * @param title
	 * @param operations
	 * @param sede
	 * @param commessa
	 */
	public ProcessoWebService(String title, Integer operations, Sede sede, Commessa commessa) {
		this(title, operations, sede.getIndirizzoWeb(), RestClient.CONTEXT_PATH_SEDE, commessa.getNomeRisorsa());	
	}

	/**
	 * Costruttore generico a cui specificare tutti i parametri.
	 * @param title
	 * @param operations
	 * @param domain
	 * @param contextPath
	 * @param risorsaCommessa
	 */
	public ProcessoWebService(String title, Integer operations, String domain, String contextPath, String risorsaCommessa) {
		super(title, operations);
		this.domain = domain;
		this.contextPath = contextPath;
		this.risorsaCommessa = risorsaCommessa;
	}
	
	/**
	 * Restituisce il client web parametrizzandolo con i valori corretti.
	 * @return il client già parametrizzato.
	 */
	protected RestClient getClient() {
		RestClient client = new RestClient(domain, contextPath, null, risorsaCommessa);
		return client;
	}
	
	/**
	 * Indica se la chiamata al web service è andata a buon fine.
	 * @return
	 */
	public abstract boolean getEsito();

}

package it.ltc.logica.common.ws;

/**
 * Classe che mappa l'oggetto JSON che contiene le informazione specifiche sull'errore.
 * @author Damiano
 *
 */
public class WSSpecificError {
	
	private String entity;
	private String cause;
	
	public WSSpecificError() {}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}

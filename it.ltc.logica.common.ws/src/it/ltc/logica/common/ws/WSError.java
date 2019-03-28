package it.ltc.logica.common.ws;

import java.util.List;

/**
 * Classe che mappa l'oggetto JSON contenente le informazioni sull'errore.
 * @author Damiano
 *
 */
public class WSError {
	
	private String message;
	private List<WSSpecificError> errors;
	
	public WSError() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<WSSpecificError> getErrors() {
		return errors;
	}

	public void setErrors(List<WSSpecificError> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		String errorMessage = message;
		if (errors != null) for (WSSpecificError specific : errors) {
			errorMessage += "\r\n" + specific.getEntity() + ": " + specific.getCause();
		}
		return errorMessage;
	}

}

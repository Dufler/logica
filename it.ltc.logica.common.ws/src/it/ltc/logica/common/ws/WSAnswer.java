package it.ltc.logica.common.ws;

/**
 * Classe wrapper che contiene la risposta del server.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class WSAnswer {
	
	private final int status;
	private final String message;
	
	
	public WSAnswer(int status, String answer) {
		this.status = status;
		this.message = answer;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}

package it.ltc.logica.database.model.centrale.ingressi;

/**
 * Classe che modella una stagione. Il codice è una chiave identificativa e dovrebbe essere nella forma XXYY dove XX sono 2 caratteri (es. AI, PE, CO, ...) e YY sono 2 cifre che indicano l'anno (es. 19)
 * @author Damiano
 *
 */
public class Stagione {
	
	protected String codice;
	protected String descrizione;
	
	public Stagione() {}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		String text = codice.equalsIgnoreCase(descrizione) ? descrizione : codice + " - " + descrizione;
		return text;
	}

}

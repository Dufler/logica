package it.ltc.logica.database.model.prodotto;

/**
 * Definisce i possibili tipi di cassa.
 * @author Damiano
 *
 */
public enum TipoCassa {
	
	NO("No"),
	STANDARD("Standard"),
	BUNDLE("Bundle");
	
	private final String descrizione;
	
	private TipoCassa(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

}

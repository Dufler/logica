package it.ltc.logica.trasporti.controller.importazione;

/**
 * Enum che definsce i possibili metodi d'importazione di spedizioni per effettuare simulazioni.
 * @author Damiano
 *
 */
public enum TipoFileImportazioneSpedizioni {
	
	CSV("File .csv"),
	FATTURA_BRT("Fattura BRT"),
	FATTURA_TNT("Fattura TNT");
	
	private final String descrizione;
	
	private TipoFileImportazioneSpedizioni(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

}

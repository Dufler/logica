package it.ltc.logica.database.model.centrale.ordini;

public enum StatiOrdine {
	
	INSE("Inserito"),
	ERRO("Errore: assegnazione fallita"),
	IMPO("Pronto per Assegnazione"),
	ASSE("Assegnato"),
	INCQ("Controllo Qualit\u00E0"),
	COCQ("Fine Controllo Qualit\u00E0"),
	INPR("Prelievo"),
	COPR("Prelievo Completato"),
	INIB("Imballo"),
	DIIB("Imballato con Differenze"),
	COIB("Imballo Completato"),
	ELAB("Pronto Per Spedizione"),
	INSP("In Spedizione"),
	SPED("Spedito"),
	ANNU("Annullato"),
	ANNT("Annullato e Archiviato"),
	FINE("Chiuso"),
	ATCO("Non Codificato");
	
	private final String descrizione;
	
	private StatiOrdine(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

}

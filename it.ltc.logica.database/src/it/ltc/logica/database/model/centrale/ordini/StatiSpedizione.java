package it.ltc.logica.database.model.centrale.ordini;

public enum StatiSpedizione {
	
	INSERITA("Inserita"),
	ABILITATA("Abilitata"),
	DELIVERY("Delivery Stampata"),
	TRASMESSA("Dati trasmessi"),
	LEGACY("Legacy (Non gestita)"),
	NONE("Nessuno (Non gestita)");
	
	private final String descrizione;
	
	private StatiSpedizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

}

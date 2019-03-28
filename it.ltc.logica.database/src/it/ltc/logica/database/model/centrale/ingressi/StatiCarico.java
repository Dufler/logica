package it.ltc.logica.database.model.centrale.ingressi;

/**
 * Enum che definisce i possibili stati dell'ingresso.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public enum StatiCarico {
	
	INSERITO("Inserito"),
	ARRIVATO("Arrivato"),
	IN_LAVORAZIONE("In lavorazione"), 
	LAVORATO("Lavorato"), 
	CHIUSO("Chiuso"),
	ANNULLATO("Annullato");
	
	private final String descrizione;
	
	private StatiCarico(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}

}

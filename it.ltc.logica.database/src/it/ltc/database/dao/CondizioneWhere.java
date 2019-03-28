package it.ltc.database.dao;

/**
 * Classe che permmette di parametrizzare la ricerca di entities.
 * @author Damiano
 *
 */
public class CondizioneWhere {
	
	public enum Operatore { EQUAL, LIKE, START_WITH, END_WITH, GREATER, GREATER_OR_EQUAL, LESSER, LESSER_OR_EQUAL }
	
	public enum Condizione { AND, OR } 
	
	private final String colonna;
	private final Object valore;
	private final Operatore operatore;
	private final Condizione condizione;
	
	public CondizioneWhere(String colonna, Object valore, Operatore operatore, Condizione condizione) {
		this.colonna = colonna;
		this.valore = valore;
		this.operatore = operatore;
		this.condizione = Condizione.AND;
	}
	
	public CondizioneWhere(String colonna, Object valore) {
		this(colonna, valore, Operatore.EQUAL, Condizione.AND);
	}

	public String getColonna() {
		return colonna;
	}

	public Object getValore() {
		return valore;
	}

	public Operatore getOperatore() {
		return operatore;
	}
	
	public Condizione getCondizione() {
		return condizione;
	}

}

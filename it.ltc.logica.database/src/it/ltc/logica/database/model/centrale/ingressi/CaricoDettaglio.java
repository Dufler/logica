package it.ltc.logica.database.model.centrale.ingressi;

import java.util.List;

/**
 * Classe che modella il flusso dati per una riga del dichiarato di un carico.
 * @author Damiano
 *
 */
public class CaricoDettaglio {
	
	private int id;
	
	private int carico;
	private int riga;
	private int articolo;
	private String magazzino;
	
	private int quantitaDichiarata;
	private int quantitaRiscontrata;
	
	private String note;
	private String madeIn;
	private String colloCliente;
	
	private List<String> seriali;
	
	public CaricoDettaglio() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarico() {
		return carico;
	}

	public void setCarico(int carico) {
		this.carico = carico;
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getArticolo() {
		return articolo;
	}

	public void setArticolo(int articolo) {
		this.articolo = articolo;
	}

	public String getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public int getQuantitaDichiarata() {
		return quantitaDichiarata;
	}

	public void setQuantitaDichiarata(int quantitaDichiarata) {
		this.quantitaDichiarata = quantitaDichiarata;
	}

	public int getQuantitaRiscontrata() {
		return quantitaRiscontrata;
	}

	public void setQuantitaRiscontrata(int quantitaRiscontrata) {
		this.quantitaRiscontrata = quantitaRiscontrata;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getColloCliente() {
		return colloCliente;
	}

	public void setColloCliente(String colloCliente) {
		this.colloCliente = colloCliente;
	}

	public List<String> getSeriali() {
		return seriali;
	}

	public void setSeriali(List<String> seriali) {
		this.seriali = seriali;
	}
	
}

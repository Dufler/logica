package it.ltc.logica.ufficio.report.caricopercollo;

/**
 * Semplice classe java che rappresenta un elemento. Vengono mappati i collipack dal sistema legacy.
 * @author Damiano
 *
 */
public class CaricoPerColloRiga {
	
	private String sku;
	private String descrizione;
	private String taglia;
	private int quantita;
	private String colloEUbicazione;
	private String magazzino;
	
	public CaricoPerColloRiga() {}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getColloEUbicazione() {
		return colloEUbicazione;
	}

	public void setColloEUbicazione(String colloEUbicazione) {
		this.colloEUbicazione = colloEUbicazione;
	}

	public String getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

}

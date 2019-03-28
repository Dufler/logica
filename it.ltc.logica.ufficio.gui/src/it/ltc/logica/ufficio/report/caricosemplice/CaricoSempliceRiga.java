package it.ltc.logica.ufficio.report.caricosemplice;

/**
 * Rappresento un elemento per costruire il report semplice di un carico.
 * @author Damiano
 *
 */
public class CaricoSempliceRiga {
	
	private String sku;
	private String descrizione;
	private String taglia;
	private String magazzino;
	private int dichiarato;
	private int riscontrato;
	private int mancante;
	private int eccedente;
	
	public CaricoSempliceRiga() {}

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

	public String getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public int getDichiarato() {
		return dichiarato;
	}

	public void setDichiarato(int dichiarato) {
		this.dichiarato = dichiarato;
	}

	public int getRiscontrato() {
		return riscontrato;
	}

	public void setRiscontrato(int riscontrato) {
		this.riscontrato = riscontrato;
	}

	public int getMancante() {
		return mancante;
	}

	public void setMancante(int mancante) {
		this.mancante = mancante;
	}

	public int getEccedente() {
		return eccedente;
	}

	public void setEccedente(int eccedente) {
		this.eccedente = eccedente;
	}

}

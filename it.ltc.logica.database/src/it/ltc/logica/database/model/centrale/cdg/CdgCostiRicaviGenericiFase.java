package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_costi_ricavi_generici_fase database table.
 * 
 */
public class CdgCostiRicaviGenericiFase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int generico;
	private int fase;
	private double percentuale;

	public CdgCostiRicaviGenericiFase() {}

	public int getGenerico() {
		return this.generico;
	}
	
	public void setGenerico(int generico) {
		this.generico = generico;
	}
	
	public int getFase() {
		return this.fase;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
	}

	public double getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fase;
		result = prime * result + generico;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdgCostiRicaviGenericiFase other = (CdgCostiRicaviGenericiFase) obj;
		if (fase != other.fase)
			return false;
		if (generico != other.generico)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CdgCostiRicaviGenericiFase [generico=" + generico + ", fase=" + fase + ", percentuale=" + percentuale
				+ "]";
	}

}
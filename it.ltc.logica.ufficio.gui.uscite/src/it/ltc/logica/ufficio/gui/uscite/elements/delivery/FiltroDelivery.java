package it.ltc.logica.ufficio.gui.uscite.elements.delivery;

import java.util.Date;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class FiltroDelivery extends CriteriFiltraggio {
	
	private final String corriere;
	private final Date da;
	private final Date a;
	
	public FiltroDelivery(String corriere, Date da, Date a) {
		this.corriere = corriere;
		this.da = da;
		this.a = a;
	}

	public String getCorriere() {
		return corriere;
	}

	public Date getDa() {
		return da;
	}

	public Date getA() {
		return a;
	}

}

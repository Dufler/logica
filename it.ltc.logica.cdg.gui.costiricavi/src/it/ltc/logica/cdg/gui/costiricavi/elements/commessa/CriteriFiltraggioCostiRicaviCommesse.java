package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import java.util.Date;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioCostiRicaviCommesse extends CriteriFiltraggio {
	
	private final Integer commessa;
	private final Date da;
	private final Date a;
	
	public CriteriFiltraggioCostiRicaviCommesse(Integer commessa, Date da, Date a) {
		this.commessa = commessa;
		this.da = da;
		this.a = a;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public Date getDa() {
		return da;
	}
	
	public Date getA() {
		return a;
	}

}

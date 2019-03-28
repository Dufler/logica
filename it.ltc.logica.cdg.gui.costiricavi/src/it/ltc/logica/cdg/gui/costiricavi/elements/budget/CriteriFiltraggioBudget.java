package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioBudget extends CriteriFiltraggio {
	
	private final Integer commessa;
	
	public CriteriFiltraggioBudget(Integer commessa) {
		this.commessa = commessa;
	}

	public Integer getCommessa() {
		return commessa;
	}

}

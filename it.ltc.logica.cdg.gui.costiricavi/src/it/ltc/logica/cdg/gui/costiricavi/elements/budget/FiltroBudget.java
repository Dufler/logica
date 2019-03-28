package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroBudget extends FiltroTabella<CdgBudget, CriteriFiltraggioBudget> {

	@Override
	protected boolean checkElemento(CdgBudget item) {
		boolean checkCommessa = checkIntValue(criteri.getCommessa(), item.getCommessa());
		return checkCommessa;
	}

}

package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreBudget extends Ordinatore<CdgBudget> {

	@Override
	protected int compare(CdgBudget t1, CdgBudget t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareInteger(t1.getCommessa(), t2.getCommessa()); break;
			case 1 : compare = compareDate(t1.getDataInizio(), t2.getDataInizio()); break;
			case 2 : compare = compareDate(t1.getDataFine(), t2.getDataFine()); break;
			default : compare = 0;
		}
		return compare;
	}

}

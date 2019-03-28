package it.ltc.logica.cdg.gui.costiricavi.elements.budgetpercentuale;

import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreBudgetPercentuale extends Ordinatore<CdgBudgetPercentualiCosto> {

	@Override
	protected int compare(CdgBudgetPercentualiCosto t1, CdgBudgetPercentualiCosto t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareInteger(t1.getCostoGenerico(), t2.getCostoGenerico()); break;
			case 1 : compare = compareDouble(t1.getPercentuale(), t2.getPercentuale()); break;
			default : compare = 0;
		}
		return compare;
	}

}

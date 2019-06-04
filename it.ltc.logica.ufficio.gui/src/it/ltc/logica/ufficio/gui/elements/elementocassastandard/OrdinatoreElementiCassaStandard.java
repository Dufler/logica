package it.ltc.logica.ufficio.gui.elements.elementocassastandard;

import it.ltc.logica.database.model.prodotto.ElementoCassaStandard;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreElementiCassaStandard extends Ordinatore<ElementoCassaStandard> {

	@Override
	protected int compare(ElementoCassaStandard t1, ElementoCassaStandard t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getTaglia(), t2.getTaglia()); break;
			case 1 : compare = compareInteger(t1.getQuantita(), t2.getQuantita()); break;
			default : compare = 0;
		}
		return compare;
	}

}

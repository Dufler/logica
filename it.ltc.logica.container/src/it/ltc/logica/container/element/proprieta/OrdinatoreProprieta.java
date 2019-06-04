package it.ltc.logica.container.element.proprieta;

import it.ltc.logica.database.model.locale.ProprietaLogica;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreProprieta extends Ordinatore<ProprietaLogica> {

	@Override
	protected int compare(ProprietaLogica t1, ProprietaLogica t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getKey(), t2.getKey()); break;
			case 1 : compare = compareString(t1.getValue(), t2.getValue()); break;
			default : compare = 0;
		}
		return compare;
	}

}

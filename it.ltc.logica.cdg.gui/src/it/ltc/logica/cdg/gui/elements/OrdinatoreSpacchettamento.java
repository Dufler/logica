package it.ltc.logica.cdg.gui.elements;

import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreSpacchettamento extends Ordinatore<CdgPezzoEvento> {

	@Override
	protected int compare(CdgPezzoEvento t1, CdgPezzoEvento t2, int property) {
		int compare;
		switch (property) {
			case 0 : case 1 : compare = compareInteger(t1.getEvento(), t2.getEvento()); break;
			case 2: compare = compareDouble(t1.getCosto(), t2.getCosto()); break;
			case 3 : compare = compareDouble(t1.getRicavo(), t2.getRicavo()); break;
			default : compare = 0;
		}
		return compare;
	}

}

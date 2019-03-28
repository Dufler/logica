package it.ltc.logica.ufficio.gui.elements.ordinestati;

import it.ltc.logica.database.model.centrale.ordini.OrdineStato;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreOrdineStati extends Ordinatore<OrdineStato> {

	@Override
	protected int compare(OrdineStato t1, OrdineStato t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = t1.getStato().compareTo(t2.getStato()); break;
			case 2 : compare = compareDate(t1.getData(), t2.getData()); break;
			default : compare = 0;
		}
		return compare;
	}
}

package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreOrdineDettagli extends Ordinatore<OrdineDettaglio> {

	@Override
	protected int compare(OrdineDettaglio t1, OrdineDettaglio t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareInteger(t1.getRiga(), t2.getRiga()); break;
			case 2 : compare = compareInteger(t1.getArticolo(), t2.getArticolo()); break;
			case 5 : compare = compareString(t1.getMagazzino(), t2.getMagazzino()); break;
			case 7 : compare = compareInteger(t1.getQuantitaOrdinata(), t2.getQuantitaOrdinata()); break;
			case 8 : compare = compareInteger(t1.getQuantitaAssegnata(), t2.getQuantitaAssegnata()); break;
			case 9 : compare = compareInteger(t1.getQuantitaImballata(), t2.getQuantitaImballata()); break;
			default : compare = 0;
		}
		return compare;
	}
}

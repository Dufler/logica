package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCaricoDettagli extends Ordinatore<CaricoDettaglio> {

	@Override
	protected int compare(CaricoDettaglio t1, CaricoDettaglio t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareInteger(t1.getRiga(), t2.getRiga()); break;
			case 2 : compare = compareInteger(t1.getArticolo(), t2.getArticolo()); break;
			case 5 : compare = compareString(t1.getMagazzino(), t2.getMagazzino()); break;
			case 7 : compare = compareInteger(t1.getQuantitaDichiarata(), t2.getQuantitaDichiarata()); break;
			case 8 : compare = compareInteger(t1.getQuantitaRiscontrata(), t2.getQuantitaRiscontrata()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.ufficio.gui.elements.caricostati;

import it.ltc.logica.database.model.centrale.ingressi.CaricoStato;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCaricoStati extends Ordinatore<CaricoStato> {

	@Override
	protected int compare(CaricoStato t1, CaricoStato t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = t1.getStato().compareTo(t2.getStato()); break;
			case 2 : compare = compareDate(t1.getData(), t2.getData()); break;
			default : compare = 0;
		}
		return compare;
	}

}

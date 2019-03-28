package it.ltc.logica.gui.common.tables.indirizzo;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreIndirizzi extends Ordinatore<Indirizzo> {

	@Override
	protected int compare(Indirizzo t1, Indirizzo t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getRagioneSociale(), t2.getRagioneSociale()); break;
			case 1 : compare = compareString(t1.getIndirizzo(), t2.getIndirizzo()); break;
			default : compare = 0;
		}
		return compare;
	}

}

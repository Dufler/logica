package it.ltc.logica.gui.common.tables.nazioni;

import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreNazioni extends Ordinatore<Nazione> {

	@Override
	protected int compare(Nazione t1, Nazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getCodiceIsoTre(), t2.getCodiceIsoTre()); break;
			default : compare = 0;
		}
		return compare;
	}

}

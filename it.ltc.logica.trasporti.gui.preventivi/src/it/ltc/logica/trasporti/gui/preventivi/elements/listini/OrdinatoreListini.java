package it.ltc.logica.trasporti.gui.preventivi.elements.listini;

import it.ltc.logica.database.model.centrale.listini.Listino;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreListini extends Ordinatore<Listino> {

	@Override
	protected int compare(Listino t1, Listino t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

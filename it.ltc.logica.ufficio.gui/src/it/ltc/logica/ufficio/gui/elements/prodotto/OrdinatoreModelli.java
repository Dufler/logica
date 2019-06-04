package it.ltc.logica.ufficio.gui.elements.prodotto;

import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreModelli extends Ordinatore<Modello> {

	@Override
	protected int compare(Modello t1, Modello t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getModello(), t2.getModello()); break;
			default : compare = 0;
		}
		return compare;
	}

}

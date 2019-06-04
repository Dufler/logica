package it.ltc.logica.ufficio.gui.elements.cassa;

import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCasse extends Ordinatore<Cassa> {

	@Override
	protected int compare(Cassa t1, Cassa t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getCodiceCassa(), t2.getCodiceCassa()); break;
			case 1 : compare = compareString(t1.getModello(), t2.getModello()); break;
			case 2 : compare = t1.getTipo().compareTo(t2.getTipo()); break;
			case 3 : compare = compareInteger(t1.getTotalePezzi(), t2.getTotalePezzi()); break;
			default : compare = 0;
		}
		return compare;
	}

}

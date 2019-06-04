package it.ltc.logica.amministrazione.gui.commesse.elements.brand;

import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreBrand extends Ordinatore<Brand> {

	@Override
	protected int compare(Brand t1, Brand t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareInteger(t1.getCodice(), t2.getCodice()); break;
			case 1 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

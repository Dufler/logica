package it.ltc.logica.ufficio.gui.elements.prodotto;

import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreProdotti extends Ordinatore<Prodotto> {

	@Override
	protected int compare(Prodotto t1, Prodotto t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getCodiceModello(), t2.getCodiceModello()); break;
			case 2 : compare = compareString(t1.getChiaveCliente(), t2.getChiaveCliente()); break;
			case 3 : compare = compareString(t1.getTaglia(), t2.getTaglia()); break;
			case 4 : compare = compareString(t1.getColore(), t2.getColore()); break;
			case 5 : compare = compareString(t1.getCategoria(), t2.getCategoria()); break;
			case 6 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.ufficio.gui.elements.elementocassa;

import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreElementiCassa extends Ordinatore<ElementoCassa> {

	@Override
	protected int compare(ElementoCassa t1, ElementoCassa t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getProdotto().getChiaveCliente(), t2.getProdotto().getChiaveCliente()); break;
			case 1 : compare = compareString(t1.getProdotto().getCodiceModello(), t2.getProdotto().getCodiceModello()); break;
			case 2 : compare = compareString(t1.getProdotto().getTaglia(), t2.getProdotto().getTaglia()); break;
			case 3 : compare = compareInteger(t1.getQuantita(), t2.getQuantita()); break;
			default : compare = 0;
		}
		return compare;
	}

}

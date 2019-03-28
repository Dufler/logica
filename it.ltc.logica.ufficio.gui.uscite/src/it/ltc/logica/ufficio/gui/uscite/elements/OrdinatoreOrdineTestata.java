package it.ltc.logica.ufficio.gui.uscite.elements;

import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreOrdineTestata extends Ordinatore<OrdineTestata> {

	@Override
	protected int compare(OrdineTestata t1, OrdineTestata t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareDate(t1.getDataOrdine(), t2.getDataOrdine()); break;
			case 2 : compare = compareString(t1.getRiferimento(), t2.getRiferimento()); break;
			case 3 : compare = compareString(t1.getRagioneSocialeDestinatario(), t2.getRagioneSocialeDestinatario()); break;
			case 4 : compare = compareInteger(t1.getQuantitaOrdinataTotale(), t2.getQuantitaOrdinataTotale()); break;
			case 5 : compare = compareString(t1.getTipo(), t2.getTipo()); break;
			case 6 : compare = t1.getStato().compareTo(t2.getStato()); break;
			default : compare = 0;
		}
		return compare;
	}

}

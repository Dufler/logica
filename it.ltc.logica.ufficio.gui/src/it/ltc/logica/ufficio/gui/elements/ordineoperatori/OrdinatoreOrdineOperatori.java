package it.ltc.logica.ufficio.gui.elements.ordineoperatori;

import it.ltc.logica.database.model.centrale.ordini.OperatoreOrdine;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreOrdineOperatori extends Ordinatore<OperatoreOrdine> {

	@Override
	protected int compare(OperatoreOrdine t1, OperatoreOrdine t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getOperatore(), t2.getOperatore()); break;
			case 1 : compare = compareString(t1.getOperazione(), t2.getOperazione()); break;
			case 2 : compare = compareInteger(t1.getQuantita(), t2.getQuantita()); break;
			default : compare = 0;
		}
		return compare;
	}

}

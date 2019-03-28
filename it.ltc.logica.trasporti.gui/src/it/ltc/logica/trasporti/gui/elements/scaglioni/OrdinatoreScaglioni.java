package it.ltc.logica.trasporti.gui.elements.scaglioni;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreScaglioni extends Ordinatore<Scaglione> {

	@Override
	protected int compare(Scaglione t1, Scaglione t2, int property) {
		int compare = t1.compareTo(t2);
		return compare;
	}

}

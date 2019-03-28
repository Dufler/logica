package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatorePreventivi extends Ordinatore<Calcolo> {

	@Override
	protected int compare(Calcolo t1, Calcolo t2, int property) {
		int compare;
		switch (property) {
		case 0 : compare = t1.getNome().compareTo(t2.getNome()); break;
		case 1 : case 2 : case 3 : compare = ordinaTotale(t1, t2); break;
		default : compare = 0;
		}
		return compare;
	}

	private int ordinaTotale(Calcolo t1, Calcolo t2) {
		Double d1 = t1.getTotale();
		Double d2 = t2.getTotale();
		int compare = d1.compareTo(d2);
		return compare;
	}

}

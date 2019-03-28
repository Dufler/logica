package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreVoceCalcolata extends Ordinatore<VoceCalcolata> {

	@Override
	protected int compare(VoceCalcolata t1, VoceCalcolata t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getNomeVoce().compareTo(t2.getNomeVoce()); break;
			case 1 : compare = t1.getNomeAmbito().compareTo(t2.getNomeAmbito()); break;
			case 2 : compare = comparaCosti(t1.getCosto(), t2.getCosto());
			default : compare = 0;
		}
		return compare;
	}
	
	private int comparaCosti(double t1, double t2) {
		int compare;
		if (t1 < t2) {
			compare = 1;
		} else if (t1 == t2) {
			compare = 0;
		} else {
			compare = -1;
		}
		return compare;
	}

}

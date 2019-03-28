package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreIndirizziSimulazione extends Ordinatore<IndirizzoSimulazione> {

	@Override
	protected int compare(IndirizzoSimulazione t1, IndirizzoSimulazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getRagioneSociale(), t2.getRagioneSociale()); break;
			case 1 : compare = compareString(t1.getIndirizzo(), t2.getIndirizzo()); break;
			default : compare = 0;
		}
		return compare;
	}

}

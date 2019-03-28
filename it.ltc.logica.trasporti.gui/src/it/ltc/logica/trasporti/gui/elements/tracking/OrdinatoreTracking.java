package it.ltc.logica.trasporti.gui.elements.tracking;

import it.ltc.logica.database.model.centrale.Tracking;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreTracking extends Ordinatore<Tracking> {

	@Override
	protected int compare(Tracking t1, Tracking t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.compareTo(t2); break;
			case 1 : compare = t1.getStato().compareTo(t2.getStato()); break;
			case 2 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}
	
}

package it.ltc.logica.trasporti.gui.elements.cap;

import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCap extends Ordinatore<Cap> {
	
	@Override
	protected int compare(Cap t1, Cap t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getCap(), t2.getCap()); break;
			case 1 : compare = compareString(t1.getLocalita(), t2.getLocalita()); break;
			case 2 : compare = compareBoolean(t1.getTntOreDieci(), t2.getTntOreDieci()); break;
			case 3 : compare = compareBoolean(t1.getTntOreDodici(), t2.getTntOreDodici()); break;
			case 4 : compare = compareBoolean(t1.getBrtDisagiate(), t2.getBrtDisagiate()); break;
			case 5 : compare = compareBoolean(t1.getBrtIsole(), t2.getBrtIsole()); break;
			case 6 : compare = compareBoolean(t1.getBrtZtl(), t2.getBrtZtl()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.admin.gui.elements.funzione;

import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreFunzioni extends Ordinatore<Funzione> {

	@Override
	protected int compare(Funzione t1, Funzione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getPartid(), t2.getPartid()); break;
			case 2 : compare = compareInteger(t1.getPermessoid(), t2.getPermessoid()); break;
			case 3 : compare = compareString(t1.getFeature(), t2.getFeature()); break;
			case 4 : compare = compareString(t1.getIcona(), t2.getIcona()); break;
			default : compare = 0;
		}
		return compare;
	}

}

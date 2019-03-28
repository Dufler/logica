package it.ltc.logica.admin.gui.elements.feature;

import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreFeature extends Ordinatore<Feature> {

	@Override
	protected int compare(Feature t1, Feature t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getVersione(), t2.getVersione()); break;
			case 2 : compare = compareString(t1.getFeatureid(), t2.getFeatureid()); break;
			case 3 : compare = compareString(t1.getPerspectiveid(), t2.getPerspectiveid()); break;
			case 4 : compare = compareInteger(t1.getPermessoid(), t2.getPermessoid()); break;
			case 5 : compare = compareString(t1.getIcona(), t2.getIcona()); break;
			case 6 : compare = compareString(t1.getColore(), t2.getColore()); break;
			case 7 : compare = compareInteger(t1.getPosizione(), t2.getPosizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

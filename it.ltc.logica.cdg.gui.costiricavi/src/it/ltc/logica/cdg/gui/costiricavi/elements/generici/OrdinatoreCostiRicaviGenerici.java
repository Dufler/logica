package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCostiRicaviGenerici extends Ordinatore<CdgCostiRicaviGenerici> {

	@Override
	protected int compare(CdgCostiRicaviGenerici t1, CdgCostiRicaviGenerici t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
//			case 1 : compare = compareInteger(t1.getSede(), t2.getSede()); break;
			case 2 : compare = t1.getDriver().compareTo(t2.getDriver()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCostiRicaviGenericiPerFase extends Ordinatore<CdgCostiRicaviGenericiFase> {

	@Override
	protected int compare(CdgCostiRicaviGenericiFase t1, CdgCostiRicaviGenericiFase t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareInteger(t1.getFase(), t2.getFase()); break;
			case 1 : compare = compareDouble(t1.getPercentuale(), t2.getPercentuale()); break;
			default : compare = 0;
		}
		return compare;
	}

}

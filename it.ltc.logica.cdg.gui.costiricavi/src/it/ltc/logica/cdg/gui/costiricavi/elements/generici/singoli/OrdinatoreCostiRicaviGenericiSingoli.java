package it.ltc.logica.cdg.gui.costiricavi.elements.generici.singoli;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCostiRicaviGenericiSingoli extends Ordinatore<CdgCostiRicaviGenericiDateValore> {

	@Override
	protected int compare(CdgCostiRicaviGenericiDateValore t1, CdgCostiRicaviGenericiDateValore t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareDate(t1.getDataEffettiva(), t2.getDataEffettiva()); break;
			case 1 : compare = compareDate(t1.getDataInizio(), t2.getDataInizio()); break;
			case 2 : compare = compareDate(t1.getDataFine(), t2.getDataFine()); break;
			case 3 : compare = compareDouble(t1.getValore(), t2.getValore()); break;
			case 4 : compare = compareInteger(t1.getSede(), t2.getSede()); break;
			case 5 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

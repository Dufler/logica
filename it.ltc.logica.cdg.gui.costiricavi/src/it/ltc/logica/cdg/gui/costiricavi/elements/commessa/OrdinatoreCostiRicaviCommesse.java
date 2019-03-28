package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCostiRicaviCommesse extends Ordinatore<CdgCostoRicavoCommessa> {

	@Override
	protected int compare(CdgCostoRicavoCommessa t1, CdgCostoRicavoCommessa t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareInteger(t1.getCommessa(), t2.getCommessa()); break;
			case 1 : compare = t1.getTipo().compareTo(t2.getTipo()); break;
			case 2 : compare = compareDouble(t1.getValore(), t2.getValore()); break;
			case 3 : compare = compareDate(t1.getDataEmissione(), t2.getDataEmissione()); break;
			case 4 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

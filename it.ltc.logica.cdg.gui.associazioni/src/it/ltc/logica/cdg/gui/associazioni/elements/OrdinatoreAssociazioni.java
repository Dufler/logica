package it.ltc.logica.cdg.gui.associazioni.elements;

import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreAssociazioni extends Ordinatore<CdgCostoAssociazione> {

	@Override
	protected int compare(CdgCostoAssociazione t1, CdgCostoAssociazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareInteger(t1.getSede(), t2.getSede()); break;
			case 2 : compare = compareDouble(t1.getCosto(), t2.getCosto()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.cdg.gui.eventi.elements.fasi;

import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreFasi extends Ordinatore<CdgFase> {

	@Override
	protected int compare(CdgFase t1, CdgFase t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

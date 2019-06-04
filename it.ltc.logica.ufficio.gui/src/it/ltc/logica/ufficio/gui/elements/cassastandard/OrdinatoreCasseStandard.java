package it.ltc.logica.ufficio.gui.elements.cassastandard;

import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCasseStandard extends Ordinatore<CassaStandard> {

	@Override
	protected int compare(CassaStandard t1, CassaStandard t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getCodiceCassa(), t2.getCodiceCassa()); break;
			case 1 : compare = compareInteger(t1.getTotalePezzi(), t2.getTotalePezzi());
			default : compare = 0;
		}
		return compare;
	}

}

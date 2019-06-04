package it.ltc.logica.ufficio.gui.uscite.elements.delivery;

import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreDelivery extends Ordinatore<Delivery> {

	@Override
	protected int compare(Delivery t1, Delivery t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareDate(t1.getDataGenerazione(), t2.getDataGenerazione()); break;
			case 1 : compare = compareString(t1.getCorriere(), t2.getCorriere());
			case 2 : compare = compareInteger(t1.getTotaleSpedizioni(), t2.getTotaleSpedizioni()); break;
			case 3 : compare = compareInteger(t1.getTotaleColli(), t2.getTotaleColli()); break;
			case 4 : compare = compareDouble(t1.getTotalePeso(), t2.getTotalePeso()); break;
			case 5 : compare = compareDouble(t1.getTotaleVolume(), t2.getTotaleVolume()); break;
			case 6 : compare = compareString(t1.getUtente(), t2.getUtente()); break;
			default : compare = 0;
		}
		return compare;
	}

}

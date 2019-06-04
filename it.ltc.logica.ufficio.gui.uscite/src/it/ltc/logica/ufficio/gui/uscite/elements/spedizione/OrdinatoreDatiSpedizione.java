package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreDatiSpedizione extends Ordinatore<DatiSpedizione> {

	@Override
	protected int compare(DatiSpedizione t1, DatiSpedizione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getRiferimento(), t2.getRiferimento()); break;
			case 2 : compare = compareInteger(t1.getColli(), t2.getColli()); break;
			case 3 : compare = compareDouble(t1.getPeso(), t2.getPeso()); break;
			case 4 : compare = compareDouble(t1.getVolume(), t2.getVolume()); break;
			case 5 : compare = compareString(t1.getCorriere(), t2.getCorriere()); break;
			case 6 : compare = compareString(t1.getServizioCorriere(), t2.getServizioCorriere()); break;
			case 7 : compare = compareDouble(t1.getValoreContrassegno(), t2.getValoreContrassegno()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.ufficio.gui.elements.ordineiballi;

import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreImballi extends Ordinatore<ImballoCollo> {

	@Override
	protected int compare(ImballoCollo t1, ImballoCollo t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getRiferimento(), t2.getRiferimento()); break;
			case 2 : compare = compareDouble(t1.getPeso(), t2.getPeso()); break;
			case 3 : compare = compareDouble(t1.getVolume(), t2.getVolume()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import it.ltc.logica.database.model.centrale.MovimentoProdotto;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreMovimentiProdotto extends Ordinatore<MovimentoProdotto> {

	@Override
	protected int compare(MovimentoProdotto t1, MovimentoProdotto t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = t1.getCausale().compareTo(t2.getCausale()); break;
			case 2 : compare = compareString(t1.getMagazzino(), t2.getMagazzino()); break;
			case 3 : compare = compareInteger(t1.getQuantita(), t2.getQuantita()); break;
			case 4 : compare = compareString(t1.getDocumentoRiferimento(), t2.getDocumentoRiferimento()); break;
			case 5 : compare = compareDate(t1.getDataMovimento(), t2.getDataMovimento()); break;
			default : compare = 0;
		}
		return compare;
	}

}

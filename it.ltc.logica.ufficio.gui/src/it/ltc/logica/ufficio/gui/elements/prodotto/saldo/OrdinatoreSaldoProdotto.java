package it.ltc.logica.ufficio.gui.elements.prodotto.saldo;

import it.ltc.logica.database.model.prodotto.SaldoProdotto;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreSaldoProdotto extends Ordinatore<SaldoProdotto> {

	@Override
	protected int compare(SaldoProdotto t1, SaldoProdotto t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getMagazzino(), t2.getMagazzino()); break;
			case 2 : compare = compareInteger(t1.getEsistente(), t2.getEsistente()); break;
			case 3 : compare = compareInteger(t1.getDisponibile(), t2.getDisponibile()); break;
			case 4 : compare = compareInteger(t1.getImpegnato(), t2.getImpegnato()); break;
			case 5 : compare = compareInteger(t1.getTotaleEntrato(), t2.getTotaleEntrato()); break;
			case 6 : compare = compareInteger(t1.getTotaleUscito(), t2.getTotaleUscito()); break;
			default : compare = 0;
		}
		return compare;
	}

}

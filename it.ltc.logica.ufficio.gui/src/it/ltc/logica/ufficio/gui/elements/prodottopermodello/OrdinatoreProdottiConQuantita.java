package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import it.ltc.logica.common.controller.prodotti.ProdottoConQuantita;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreProdottiConQuantita extends Ordinatore<ProdottoConQuantita> {

	@Override
	protected int compare(ProdottoConQuantita t1, ProdottoConQuantita t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerArticolo(t1, t2); break;
			case 1 : compare = ordinaPerTaglia(t1, t2); break;
			case 2 : compare = ordinaPerQuantita(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerArticolo(ProdottoConQuantita t1, ProdottoConQuantita t2) {
		int compare = compareString(t1.getProdotto().getChiaveCliente(), t2.getProdotto().getChiaveCliente());
		return compare;
	}

	private int ordinaPerTaglia(ProdottoConQuantita t1, ProdottoConQuantita t2) {
		int compare = compareString(t1.getProdotto().getTaglia(), t2.getProdotto().getTaglia());
		return compare;
	}

	private int ordinaPerQuantita(ProdottoConQuantita t1, ProdottoConQuantita t2) {
		int compare = compareInteger(t1.getQuantita(), t2.getQuantita());
		return compare;
	}

}

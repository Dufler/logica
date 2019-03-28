package it.ltc.logica.ufficio.gui.elements.fornitore;

import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreFornitori extends Ordinatore<Fornitore> {

	@Override
	protected int compare(Fornitore t1, Fornitore t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getRiferimentoCliente(), t2.getRiferimentoCliente()); break;
			case 2 : compare = compareString(t1.getNote(), t2.getNote()); break;
			default : compare = 0;
		}
		return compare;
	}

}

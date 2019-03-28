package it.ltc.logica.trasporti.gui.listini.elements.simulazione;

import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreListiniSimulazione extends Ordinatore<ListinoSimulazione> {

	@Override
	protected int compare(ListinoSimulazione t1, ListinoSimulazione t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getNome().compareTo(t2.getNome()); break;
			case 1 : compare = compareInteger(t1.getTipo(), t2.getTipo()); break;
			case 2 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

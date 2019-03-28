package it.ltc.logica.trasporti.gui.listini.elements.corriere;

import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreListiniCorrieri extends Ordinatore<ListinoCorriere> {

	@Override
	protected int compare(ListinoCorriere t1, ListinoCorriere t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getNome().compareTo(t2.getNome()); break;
			case 1 : compare = t1.getTipo().compareTo(t2.getTipo()); break;
			case 2 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}
	
}

package it.ltc.logica.amministrazione.gui.commesse.elements;

import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCommesse extends FiltroTabella<CommessaCentrale, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(CommessaCentrale item) {
		boolean check = checkStringValue(criteri.getTesto(), item.getNome());
		return check;
	}

}

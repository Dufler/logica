package it.ltc.logica.ufficio.gui.elements.fornitore;

import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroFornitori extends FiltroTabella<Fornitore, CriteriFiltraggioSoloTesto> {
	
	@Override
	protected boolean checkElemento(Fornitore fornitore) {
		boolean select = checkStringValue(criteri.getTesto(), fornitore.getNome());
		return select;
	}

}

package it.ltc.logica.trasporti.gui.elements.cap;

import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCAP extends FiltroTabella<Cap, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(Cap item) {
		return checkStringValue(criteri.getTesto(), item.getCap()) || checkStringValue(criteri.getTesto(), item.getLocalita());
	}	

}

package it.ltc.logica.cdg.gui.associazioni.elements;

import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroAssociazioni extends FiltroTabella<CdgCostoAssociazione, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(CdgCostoAssociazione item) {
		boolean toShow = checkStringValue(criteri.getTesto(), item.getNome());
		return toShow;
	}

}

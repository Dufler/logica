package it.ltc.logica.admin.gui.elements.funzione;

import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroFunzioni extends FiltroTabella<Funzione, CriteriFiltraggioFunzione> {

	@Override
	protected boolean checkElemento(Funzione item) {
		String selectedFeature = criteri.getFeature() != null ? criteri.getFeature().getPerspectiveid() : null;
		boolean check = item.getFeature() != null ? item.getFeature().equals(selectedFeature) : false;
		return check;
	}

}

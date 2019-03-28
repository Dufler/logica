package it.ltc.logica.admin.gui.elements.funzione;

import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioFunzione extends CriteriFiltraggio {
	
	private final Feature feature;
	
	public CriteriFiltraggioFunzione(Feature feature) {
		this.feature = feature;
	}

	public Feature getFeature() {
		return feature;
	}

}

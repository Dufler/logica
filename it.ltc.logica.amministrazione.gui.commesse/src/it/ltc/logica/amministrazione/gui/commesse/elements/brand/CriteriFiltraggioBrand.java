package it.ltc.logica.amministrazione.gui.commesse.elements.brand;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioBrand extends CriteriFiltraggio {
	
	private final Commessa commessa;
	private final String testo;
	
	public CriteriFiltraggioBrand(Commessa commessa, String testo) {
		this.commessa = commessa;
		this.testo = testo;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public String getTesto() {
		return testo;
	}

}

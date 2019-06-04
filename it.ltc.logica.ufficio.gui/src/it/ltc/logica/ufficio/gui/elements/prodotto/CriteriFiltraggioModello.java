package it.ltc.logica.ufficio.gui.elements.prodotto;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioModello extends CriteriFiltraggio {
	
	private final Commessa commessa;
	private final String modello;
	
	public CriteriFiltraggioModello(Commessa commessa, String filtro) {
		this.commessa = commessa;
		this.modello = filtro;
	}
	
	public Commessa getCommessa() {
		return commessa;
	}

	public String getModello() {
		return modello;
	}

}

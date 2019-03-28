package it.ltc.logica.ufficio.gui.elements.prodotto;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioProdotto extends CriteriFiltraggio {
	
	private final Commessa commessa;
	private final String skuModelloBarcode;
	
	public CriteriFiltraggioProdotto(Commessa commessa, String filtro) {
		this.commessa = commessa;
		this.skuModelloBarcode = filtro;
	}
	
	public Commessa getCommessa() {
		return commessa;
	}

	public String getSkuModelloBarcode() {
		return skuModelloBarcode;
	}
	

}

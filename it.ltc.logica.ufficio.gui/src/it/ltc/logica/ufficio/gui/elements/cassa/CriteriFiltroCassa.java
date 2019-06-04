package it.ltc.logica.ufficio.gui.elements.cassa;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.TipoCassa;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltroCassa extends CriteriFiltraggio {
	
	private final Commessa commessa;
	private final String testo;
	private final TipoCassa tipoCassa;
	
	public CriteriFiltroCassa(Commessa commessa, String testo, TipoCassa tipoCassa) {
		this.commessa = commessa;
		this.testo = testo;
		this.tipoCassa = tipoCassa;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public String getTesto() {
		return testo;
	}

	public TipoCassa getTipoCassa() {
		return tipoCassa;
	}
	

}

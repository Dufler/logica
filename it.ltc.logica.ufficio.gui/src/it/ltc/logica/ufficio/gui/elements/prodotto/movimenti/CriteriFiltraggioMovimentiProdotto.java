package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import java.util.Date;

import it.ltc.logica.database.model.centrale.CausaliMovimento;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioMovimentiProdotto extends CriteriFiltraggio {
	
	private final Date da;
	private final Date a;
	private final CausaliMovimento causale;
	private final String magazzino;
	
	public CriteriFiltraggioMovimentiProdotto(Date da, Date a, CausaliMovimento causale, String magazzino) {
		this.da = da;
		this.a = a;
		this.causale = causale;
		this.magazzino = magazzino;
	}

	@Override
	public String toString() {
		return "CriteriFiltraggioMovimentiProdotto [da=" + da + ", a=" + a + ", causale=" + causale + ", magazzino="
				+ magazzino + "]";
	}

	public Date getDa() {
		return da;
	}

	public Date getA() {
		return a;
	}

	public CausaliMovimento getCausale() {
		return causale;
	}

	public String getMagazzino() {
		return magazzino;
	}

}

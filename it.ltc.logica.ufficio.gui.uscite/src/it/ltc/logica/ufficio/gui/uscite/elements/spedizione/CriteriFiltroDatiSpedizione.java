package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.Date;

import it.ltc.logica.database.model.centrale.ordini.StatiSpedizione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltroDatiSpedizione extends CriteriFiltraggio {
	
	private final String riferimento;
	private final StatiSpedizione stato;
	private final Date da;
	private final Date a;
	
	public CriteriFiltroDatiSpedizione(String riferimento, StatiSpedizione stato, Date da, Date a) {
		this.riferimento = riferimento;
		this.stato = stato;
		this.da = da;
		this.a = a;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public StatiSpedizione getStato() {
		return stato;
	}

	public Date getDa() {
		return da;
	}

	public Date getA() {
		return a;
	}

}

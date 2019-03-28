package it.ltc.logica.ufficio.gui.ingressi.elements;

import java.util.Date;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTipo;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;

public class CriteriFiltraggioCaricoTestata extends CriteriFiltraggio {
	
	private Commessa commessa;
	private Fornitore fornitore;
	private StatiCarico stato;
	private String riferimento;
	private CaricoTipo tipo;
	private Date da;
	private Date a;
	
	public CriteriFiltraggioCaricoTestata() {}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public StatiCarico getStato() {
		return stato;
	}

	public void setStato(StatiCarico stato) {
		this.stato = stato;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public CaricoTipo getTipo() {
		return tipo;
	}

	public void setTipo(CaricoTipo tipo) {
		this.tipo = tipo;
	}

	public Date getDa() {
		return da;
	}

	public void setDa(Date da) {
		this.da = da;
	}

	public Date getA() {
		return a;
	}

	public void setA(Date a) {
		this.a = a;
	}

}

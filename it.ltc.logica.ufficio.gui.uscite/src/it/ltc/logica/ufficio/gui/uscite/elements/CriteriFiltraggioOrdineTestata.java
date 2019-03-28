package it.ltc.logica.ufficio.gui.uscite.elements;

import java.util.Date;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTipo;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioOrdineTestata extends CriteriFiltraggio {
	
	private Commessa commessa;
	private StatiOrdine stato;
	private String riferimento;
	private OrdineTipo tipo;
	private Date da;
	private Date a;
	
	public CriteriFiltraggioOrdineTestata() {}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public StatiOrdine getStato() {
		return stato;
	}

	public void setStato(StatiOrdine stato) {
		this.stato = stato;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public OrdineTipo getTipo() {
		return tipo;
	}

	public void setTipo(OrdineTipo tipo) {
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

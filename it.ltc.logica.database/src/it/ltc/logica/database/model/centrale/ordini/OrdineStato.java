package it.ltc.logica.database.model.centrale.ordini;

import java.util.Date;

public class OrdineStato {
	
	private int ordine;
	private StatiOrdine stato;
	private Date data;
	
	public OrdineStato() {}

	public int getOrdine() {
		return ordine;
	}

	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}

	public StatiOrdine getStato() {
		return stato;
	}

	public void setStato(StatiOrdine stato) {
		this.stato = stato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}

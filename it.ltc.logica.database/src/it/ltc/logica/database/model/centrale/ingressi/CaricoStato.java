package it.ltc.logica.database.model.centrale.ingressi;

import java.util.Date;

public class CaricoStato {
	
	private int carico;
	private StatiCarico stato;
	private Date data;
	
	public CaricoStato() {}

	public int getCarico() {
		return carico;
	}

	public void setCarico(int carico) {
		this.carico = carico;
	}

	public StatiCarico getStato() {
		return stato;
	}

	public void setStato(StatiCarico stato) {
		this.stato = stato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}

package it.ltc.logica.database.model.centrale.trasporti;

import java.io.Serializable;

public class CapPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String cap;
	protected String localita;
	
	public CapPK() {}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

}

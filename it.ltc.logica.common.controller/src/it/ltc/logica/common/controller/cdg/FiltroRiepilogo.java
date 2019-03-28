package it.ltc.logica.common.controller.cdg;

import java.util.Date;

public class FiltroRiepilogo {
	
	private int commessa;
	private Date inizio;
	private Date fine;
	
	public FiltroRiepilogo() {}

	public FiltroRiepilogo(int commessa, Date inizio, Date fine) {
		this.commessa = commessa;
		this.inizio = inizio;
		this.fine = fine;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

}

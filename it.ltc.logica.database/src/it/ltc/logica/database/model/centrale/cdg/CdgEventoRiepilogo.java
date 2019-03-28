package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the cdg_evento_riepilogo database table.
 * 
 */
public class CdgEventoRiepilogo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int evento;
	private int commessa;
	private Date giorno;
	private int durataTotale;
	private String operatore;
	private int pezzi;

	public CdgEventoRiepilogo() {}
	
	public int getEvento() {
		return this.evento;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}
	
	public int getCommessa() {
		return this.commessa;
	}
	
	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}
	
	public java.util.Date getGiorno() {
		return this.giorno;
	}
	
	public void setGiorno(java.util.Date giorno) {
		this.giorno = giorno;
	}

	public int getDurataTotale() {
		return this.durataTotale;
	}

	public void setDurataTotale(int durataTotale) {
		this.durataTotale = durataTotale;
	}

	public String getOperatore() {
		return this.operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + evento;
		result = prime * result + ((giorno == null) ? 0 : giorno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdgEventoRiepilogo other = (CdgEventoRiepilogo) obj;
		if (commessa != other.commessa)
			return false;
		if (evento != other.evento)
			return false;
		if (giorno == null) {
			if (other.giorno != null)
				return false;
		} else if (!giorno.equals(other.giorno))
			return false;
		return true;
	}

}
package it.ltc.logica.database.model.centrale.cdg;

public class CdgCommessaEvento {
	
	private int evento;
	private int commessa;
	private int durata;
	
	public CdgCommessaEvento() {}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	@Override
	public String toString() {
		return "CdgCommessaEvento [evento=" + evento + ", commessa=" + commessa + ", durata=" + durata + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + evento;
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
		CdgCommessaEvento other = (CdgCommessaEvento) obj;
		if (commessa != other.commessa)
			return false;
		if (evento != other.evento)
			return false;
		return true;
	}

}

package it.ltc.logica.database.model.centrale.listini;

public class VoceDiListinoScaglioni implements Comparable<VoceDiListinoScaglioni> {
	
	private Integer idVoce;
	private Double inizio;
	private Double fine;
	private Double valore;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVoce == null) ? 0 : idVoce.hashCode());
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
		VoceDiListinoScaglioni other = (VoceDiListinoScaglioni) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "inizio=" + inizio + ", fine=" + fine + ", valore=" + valore;
	}

	public Integer getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(Integer id) {
		this.idVoce = id;
	}

	public Double getInizio() {
		return inizio;
	}

	public void setInizio(Double inizio) {
		this.inizio = inizio;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	@Override
	public int compareTo(VoceDiListinoScaglioni o) {
		int order;
		if (inizio == null && o.getInizio() == null)
			order = 0;
		else if (inizio == null && o.getInizio() != null)
			order = -1;
		else if (inizio != null && o.getInizio() == null)
			order = 1;
		else if (inizio < o.getInizio())
			order = -1;
		else if (inizio > o.getInizio())
			order = 1;
		else
			order = 0;
		return order;
	}
}

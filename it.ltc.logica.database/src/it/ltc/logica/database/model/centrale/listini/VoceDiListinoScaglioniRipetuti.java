package it.ltc.logica.database.model.centrale.listini;

public class VoceDiListinoScaglioniRipetuti {
	
	private Integer idVoce;
	private Double intervallo;
	private Double minimo;
	private Double massimo;
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
		VoceDiListinoScaglioniRipetuti other = (VoceDiListinoScaglioniRipetuti) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "intervallo=" + intervallo + ", minimo=" + minimo + ", massimo=" + massimo + ", valore=" + valore;
	}

	public Integer getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(Integer id) {
		this.idVoce = id;
	}

	public Double getIntervallo() {
		return intervallo;
	}

	public void setIntervallo(Double intervallo) {
		this.intervallo = intervallo;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMassimo() {
		return massimo;
	}

	public void setMassimo(Double massimo) {
		this.massimo = massimo;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

}

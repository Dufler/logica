package it.ltc.logica.database.model.centrale.listini;

public class VoceDiListinoProporzionale {
	
	private Integer idVoce;
	private Double valore;
	private Double minimo;
	private Double massimo;
	
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
		VoceDiListinoProporzionale other = (VoceDiListinoProporzionale) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "valore=" + valore + ", minimo=" + minimo + ", massimo=" + massimo;
	}

	public Integer getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(Integer id) {
		this.idVoce = id;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
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

}

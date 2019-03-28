package it.ltc.logica.database.model.centrale.listini;

public class VoceDiListinoFissa {

	private Integer idVoce;
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
		VoceDiListinoFissa other = (VoceDiListinoFissa) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\u20AC " + valore.toString();
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

}

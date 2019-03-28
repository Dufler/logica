package it.ltc.logica.database.model.centrale.trasporti;

public class ParticolaritaSpedizione {

	private Integer idSpedizione;
	private Boolean appuntamento;

	@Override
	public String toString() {
		return "ParticolaritaSpedizione [idSpedizione=" + idSpedizione + ", appuntamento=" + appuntamento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSpedizione == null) ? 0 : idSpedizione.hashCode());
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
		ParticolaritaSpedizione other = (ParticolaritaSpedizione) obj;
		if (idSpedizione == null) {
			if (other.idSpedizione != null)
				return false;
		} else if (!idSpedizione.equals(other.idSpedizione))
			return false;
		return true;
	}

	public Integer getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(Integer idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public Boolean getAppuntamento() {
		return appuntamento;
	}

	public void setAppuntamento(Boolean appuntamento) {
		this.appuntamento = appuntamento;
	}

}

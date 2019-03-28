package it.ltc.logica.database.model.centrale.listini;

public class VoceDiListinoCorrierePercentuale {

	private Integer idVoce;
	private Double valore;
	private Double valoreMinimo;
	private Double valoreMassimo;
	
	@Override
	public String toString() {
		return "VoceDiListinoCorrierePercentuale [valore=" + valore + ", minimo=" + valoreMinimo + ", massimo=" + valoreMassimo + "]";
	}

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
		VoceDiListinoCorrierePercentuale other = (VoceDiListinoCorrierePercentuale) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
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

	public Double getValoreMinimo() {
		return valoreMinimo;
	}

	public void setValoreMinimo(Double valoreMinimo) {
		this.valoreMinimo = valoreMinimo;
	}

	public Double getValoreMassimo() {
		return valoreMassimo;
	}

	public void setValoreMassimo(Double valoreMassimo) {
		this.valoreMassimo = valoreMassimo;
	}

}

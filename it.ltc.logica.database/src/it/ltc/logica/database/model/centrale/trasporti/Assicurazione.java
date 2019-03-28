package it.ltc.logica.database.model.centrale.trasporti;

public class Assicurazione {
	
	private Integer idSpedizione;
	private Double valore;
	private String valuta;
	private String tipo;

	@Override
	public String toString() {
		return "Assicurazione [idSpedizione=" + idSpedizione + ", valore=" + valore + ", valuta=" + valuta + ", tipo=" + tipo + "]";
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
		Assicurazione other = (Assicurazione) obj;
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

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

package it.ltc.logica.database.model.centrale.ingressi;

public class IngressoDettaglioPK {
	
	private Integer idIngresso;
	private Integer idProdotto;
	
	public IngressoDettaglioPK() {}

	public Integer getIdIngresso() {
		return idIngresso;
	}

	public void setIdIngresso(Integer idIngresso) {
		this.idIngresso = idIngresso;
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idIngresso == null) ? 0 : idIngresso.hashCode());
		result = prime * result + ((idProdotto == null) ? 0 : idProdotto.hashCode());
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
		IngressoDettaglioPK other = (IngressoDettaglioPK) obj;
		if (idIngresso == null) {
			if (other.idIngresso != null)
				return false;
		} else if (!idIngresso.equals(other.idIngresso))
			return false;
		if (idProdotto == null) {
			if (other.idProdotto != null)
				return false;
		} else if (!idProdotto.equals(other.idProdotto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngressoDettaglioPK [idIngresso=" + idIngresso + ", idProdotto=" + idProdotto + "]";
	}
	
	

}

package it.ltc.logica.database.model.prodotto;

public class ElementoCassaStandard {
	
	private String taglia;
	private int quantita;
	
	public ElementoCassaStandard() {}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taglia == null) ? 0 : taglia.hashCode());
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
		ElementoCassaStandard other = (ElementoCassaStandard) obj;
		if (taglia == null) {
			if (other.taglia != null)
				return false;
		} else if (!taglia.equals(other.taglia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ElementoCassaStandard [taglia=" + taglia + ", quantita=" + quantita + "]";
	}

}

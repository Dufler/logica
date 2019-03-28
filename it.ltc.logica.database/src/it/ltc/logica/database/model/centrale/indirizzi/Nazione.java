package it.ltc.logica.database.model.centrale.indirizzi;

public class Nazione {

	private String codiceIsoTre;
	private String codiceIsoDue;
	private String nome;
	private Boolean ue;
	private String zona;
	private String zonaTnt;
	
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceIsoTre == null) ? 0 : codiceIsoTre.hashCode());
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
		Nazione other = (Nazione) obj;
		if (codiceIsoTre == null) {
			if (other.codiceIsoTre != null)
				return false;
		} else if (!codiceIsoTre.equals(other.codiceIsoTre))
			return false;
		return true;
	}

	public String getCodiceIsoTre() {
		return codiceIsoTre;
	}

	public void setCodiceIsoTre(String codiceISO3) {
		this.codiceIsoTre = codiceISO3;
	}

	public String getCodiceIsoDue() {
		return codiceIsoDue;
	}

	public void setCodiceIsoDue(String codiceISO2) {
		this.codiceIsoDue = codiceISO2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getUe() {
		return ue;
	}

	public void setUe(Boolean ue) {
		this.ue = ue;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getZonaTnt() {
		return zonaTnt;
	}

	public void setZonaTnt(String zonaTNT) {
		this.zonaTnt = zonaTNT;
	}

}

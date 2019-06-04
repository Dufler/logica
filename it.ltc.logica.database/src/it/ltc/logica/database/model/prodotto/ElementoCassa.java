package it.ltc.logica.database.model.prodotto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ElementoCassa {
	
	private int idProdotto;
	private int quantita;
	
	/**
	 * Campo presente solo nel client, al download gli viene associato il prodotto corretto.
	 */
	@JsonIgnore
	private Prodotto prodotto;
	
	public ElementoCassa() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProdotto;
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
		ElementoCassa other = (ElementoCassa) obj;
		if (idProdotto != other.idProdotto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ElementoCassa [quantita=" + quantita + ", prodotto=" + prodotto + "]";
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@JsonIgnore
	public Prodotto getProdotto() {
		return prodotto;
	}

	@JsonIgnore
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

}

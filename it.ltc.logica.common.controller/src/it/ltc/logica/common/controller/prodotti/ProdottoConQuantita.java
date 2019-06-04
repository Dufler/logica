package it.ltc.logica.common.controller.prodotti;

import it.ltc.logica.database.model.prodotto.Prodotto;

/**
 * Rappresenta un prodotto e la quantità di interesse per esso.
 * Viene usato per carichi e ordini.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ProdottoConQuantita {
	
	private final ProdottiPerModello modello;
	private final Prodotto prodotto;
	private int quantita;
	
	public ProdottoConQuantita(ProdottiPerModello modello, Prodotto prodotto) {
		this.modello = modello;
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public ProdottiPerModello getModello() {
		return modello;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodotto == null) ? 0 : prodotto.hashCode());
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
		ProdottoConQuantita other = (ProdottoConQuantita) obj;
		if (prodotto == null) {
			if (other.prodotto != null)
				return false;
		} else if (!prodotto.equals(other.prodotto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdottoConQuantita [prodotto=" + prodotto + ", quantita=" + quantita + "]";
	}

}

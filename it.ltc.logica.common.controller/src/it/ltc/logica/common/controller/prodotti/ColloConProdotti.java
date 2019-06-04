package it.ltc.logica.common.controller.prodotti;

import java.util.HashMap;

import it.ltc.logica.database.model.prodotto.Prodotto;

/**
 * Rappresenta la definizione di un collo in fase di importazione dei dati di un carico.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ColloConProdotti {
	
	private final String collo;
	private final HashMap<Prodotto, Integer> contenuto;
	
	public ColloConProdotti(String collo, HashMap<Prodotto, Integer> contenuto) {
		this.collo = collo;
		this.contenuto = contenuto;
	}

	public String getCollo() {
		return collo;
	}

	public HashMap<Prodotto, Integer> getContenuto() {
		return contenuto;
	}
	
	/**
	 * Restituisce il totale dei pezzi contenuti nel collo.
	 * @return un intero corrispondente al totale dei pezzi.
	 */
	public int getTotalePezzi() {
		int totale = 0;
		for (Integer i : contenuto.values()) {
			int pezzi = i != null ? i : 0;
			totale += pezzi;
		}
		return totale;
	}

	@Override
	public String toString() {
		return "ColloConProdotti [collo=" + collo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collo == null) ? 0 : collo.hashCode());
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
		ColloConProdotti other = (ColloConProdotti) obj;
		if (collo == null) {
			if (other.collo != null)
				return false;
		} else if (!collo.equals(other.collo))
			return false;
		return true;
	}

	
}

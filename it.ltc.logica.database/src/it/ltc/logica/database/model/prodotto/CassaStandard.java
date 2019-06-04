package it.ltc.logica.database.model.prodotto;

import java.util.Set;

public class CassaStandard {
	
	private String codiceCassa;
	private Set<ElementoCassaStandard> elementi;
	
	public CassaStandard() {}

	public String getCodiceCassa() {
		return codiceCassa;
	}

	public void setCodiceCassa(String codiceCassa) {
		this.codiceCassa = codiceCassa;
	}

	public Set<ElementoCassaStandard> getElementi() {
		return elementi;
	}

	public void setElementi(Set<ElementoCassaStandard> elementi) {
		this.elementi = elementi;
	}

	public int getTotalePezzi() {
		int totale = 0;
		if (elementi != null) for (ElementoCassaStandard elemento : elementi) {
			totale += elemento.getQuantita();
		}
		return totale;
	}

}

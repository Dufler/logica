package it.ltc.logica.database.model.prodotto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cassa {
	
	private int idCassa;
	private TipoCassa tipo;
	private String modello;
	private String codiceCassa;
	private Set<ElementoCassa> prodotti;
	
	/**
	 * Campo presente solo nel client, al download gli viene associato il prodotto a cassa corretto.
	 */
	@JsonIgnore
	private Prodotto cassa;
	
	public Cassa() {}

	public int getIdCassa() {
		return idCassa;
	}

	public void setIdCassa(int idCassa) {
		this.idCassa = idCassa;
	}

	public TipoCassa getTipo() {
		return tipo;
	}

	public void setTipo(TipoCassa tipo) {
		this.tipo = tipo;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getCodiceCassa() {
		return codiceCassa;
	}

	public void setCodiceCassa(String codiceCassa) {
		this.codiceCassa = codiceCassa;
	}

	public Set<ElementoCassa> getProdotti() {
		return prodotti;
	}

	public void setProdotti(Set<ElementoCassa> prodotti) {
		this.prodotti = prodotti;
	}

	public Prodotto getCassa() {
		return cassa;
	}

	public void setCassa(Prodotto cassa) {
		this.cassa = cassa;
	}

	@JsonIgnore
	public int getTotalePezzi() {
		int totale = 0;
		if (prodotti != null) for (ElementoCassa prodotto : prodotti) {
			totale += prodotto.getQuantita();
		}
		return totale;
	}

}

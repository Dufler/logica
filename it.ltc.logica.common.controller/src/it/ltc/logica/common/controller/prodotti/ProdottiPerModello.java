package it.ltc.logica.common.controller.prodotti;

import java.util.Collection;
import java.util.HashMap;

import it.ltc.logica.database.model.centrale.Prodotto;

/**
 * Le istanze di questa classe raggruppano prodotti dello stesso modello.
 * Vengono usati per realizzare una vista orizzontale per taglia dei prodotti.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ProdottiPerModello {
	
	//private Integer idCliente;
	private String categoriaMerceologica;
	private String brand;
	private String codiceModello;
	private String colore;
	private String descrizione;
	private final HashMap<Integer, ProdottoConQuantita> prodotti;
	
	public ProdottiPerModello(Prodotto prodotto) {
		if (prodotto != null) {
			categoriaMerceologica = prodotto.getCategoria();
			brand = prodotto.getBrand();
			codiceModello = prodotto.getCodiceModello();
			colore = prodotto.getColore();
			descrizione = prodotto.getDescrizione();
		}
		prodotti = new HashMap<Integer, ProdottoConQuantita>();
	}
	
	public ProdottiPerModello() {
		this(null);
	}
	
//	public Integer getIdCliente() {
//		return idCliente;
//	}
//	
//	public void setIdCliente(Integer idCliente) {
//		this.idCliente = idCliente;
//	}
	
	public String getCategoriaMerceologica() {
		return categoriaMerceologica;
	}
	
	public void setCategoriaMerceologica(String categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCodiceModello() {
		return codiceModello;
	}
	
	public void setCodiceModello(String codiceModello) {
		this.codiceModello = codiceModello;
	}
	
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Collection<ProdottoConQuantita> getProdotti() {
		return prodotti.values();
	}

	public void addProdotto(Prodotto prodotto, int quantita) {
		ProdottoConQuantita pcq = new ProdottoConQuantita(this, prodotto);
		pcq.setQuantita(quantita);
		prodotti.put(prodotto.getId(), pcq);
	}
	
	public boolean appartieneProdotto(int idProdotto) {
		return prodotti.containsKey(idProdotto);
	}
	
	/**
	 * Riporta a 0 la quantità per ogni prodotto in lista.
	 */
	public void svuotaListaProdotti() {
		for (Integer idProdotto : prodotti.keySet()) {
			prodotti.get(idProdotto).setQuantita(0);
		}
	}
	
	/**
	 * Elimina tutti i prodotti.
	 */
	public void eliminaProdotti() {
		prodotti.clear();
	}
	
	public int getTotale() {
		int totale = 0;
		for (ProdottoConQuantita pcq : prodotti.values())
			totale += pcq.getQuantita();
		return totale;
	}
	
	/**
	 * Restituisce una stringa rappresentativa delle taglie per ogni prodotto di questo modello.
	 * @return
	 */
	public String getRappresentazioneTaglie() {
		String taglie = "";
		int totale = 0;
		for (Integer idProdotto : prodotti.keySet()) {
			ProdottoConQuantita pcq =  prodotti.get(idProdotto);
			totale += pcq.getQuantita();
			Prodotto prodotto = pcq.getProdotto();
			taglie += prodotto.getTaglia() + ", ";
		}
		if (!taglie.isEmpty())
			taglie = taglie.substring(0, taglie.length() - 2);
		if (totale > 0)
			taglie += " (" + totale + ")";
		return taglie;
	}
	
	/**
	 * Restituisce una stringa rappresentativa delle taglie per ogni prodotto di questo modello.
	 * @return
	 */
	public String getRappresentazioneTaglieConQuantita() {
		String taglie = "";
		for (Integer idProdotto : prodotti.keySet()) {
			ProdottoConQuantita pcq =  prodotti.get(idProdotto);
			int quantita = pcq.getQuantita();
			if (quantita > 0) {
				Prodotto prodotto = pcq.getProdotto();
				taglie += prodotto.getTaglia();
				taglie += " (" + quantita + ")\r\n";
			}
		}
		if (!taglie.isEmpty())
			taglie = taglie.substring(0, taglie.length() - 2);
		return taglie;
	}

	@Override
	public String toString() {
		return codiceModello;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceModello == null) ? 0 : codiceModello.hashCode());
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
		ProdottiPerModello other = (ProdottiPerModello) obj;
		if (categoriaMerceologica != other.categoriaMerceologica)
			return false;
		if (codiceModello == null) {
			if (other.codiceModello != null)
				return false;
		} else if (!codiceModello.equals(other.codiceModello))
			return false;
		return true;
	}

}

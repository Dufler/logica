package it.ltc.logica.database.model.prodotto;

public class Modello {
	
	private String modello;
	private int commessa;
//	private String colore;
//	private String descrizione;
//	private String composizione;
//	private String brand;
//	private String categoria;
//	private String sottoCategoria;
//	private String madeIn;
//	private String stagione;
	
	public Modello() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + ((modello == null) ? 0 : modello.hashCode());
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
		Modello other = (Modello) obj;
		if (commessa != other.commessa)
			return false;
		if (modello == null) {
			if (other.modello != null)
				return false;
		} else if (!modello.equals(other.modello))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return modello;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

//	public String getColore() {
//		return colore;
//	}
//
//	public void setColore(String colore) {
//		this.colore = colore;
//	}
//
//	public String getDescrizione() {
//		return descrizione;
//	}
//
//	public void setDescrizione(String descrizione) {
//		this.descrizione = descrizione;
//	}
//
//	public String getComposizione() {
//		return composizione;
//	}
//
//	public void setComposizione(String composizione) {
//		this.composizione = composizione;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public String getCategoria() {
//		return categoria;
//	}
//
//	public void setCategoria(String categoria) {
//		this.categoria = categoria;
//	}
//
//	public String getSottoCategoria() {
//		return sottoCategoria;
//	}
//
//	public void setSottoCategoria(String sottoCategoria) {
//		this.sottoCategoria = sottoCategoria;
//	}
//
//	public String getMadeIn() {
//		return madeIn;
//	}
//
//	public void setMadeIn(String madeIn) {
//		this.madeIn = madeIn;
//	}
//
//	public String getStagione() {
//		return stagione;
//	}
//
//	public void setStagione(String stagione) {
//		this.stagione = stagione;
//	}

}

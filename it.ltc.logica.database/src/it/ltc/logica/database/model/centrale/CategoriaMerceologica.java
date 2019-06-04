package it.ltc.logica.database.model.centrale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CategoriaMerceologica {
	
	public static final String NESSUNA = "NESSUNA";
	
	public enum StatoCategoria { ATTIVO, DISATTIVO };
	
	private String nome;
	private String descrizione;
	private int commessa;
	private StatoCategoria stato;
	private int brand;
	
	public CategoriaMerceologica() {}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		CategoriaMerceologica other = (CategoriaMerceologica) obj;
		if (commessa != other.commessa)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public StatoCategoria getStato() {
		return stato;
	}

	public void setStato(StatoCategoria stato) {
		this.stato = stato;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

}

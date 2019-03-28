package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_evento database table.
 * 
 */
public class CdgEvento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String categoriaMerceologica;
	private String descrizione;
	private int fase;
	private String nome;

	public CdgEvento() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaMerceologica() {
		return this.categoriaMerceologica;
	}

	public void setCategoriaMerceologica(String categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getFase() {
		return this.fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		CdgEvento other = (CdgEvento) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
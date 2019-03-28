package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_fase database table.
 * 
 */
public class CdgFase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ID_EXTRA = 5;
	public static final int ID_UFFICIO = 4;

	private int id;
	private String descrizione;
	private String nome;

	public CdgFase() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		CdgFase other = (CdgFase) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
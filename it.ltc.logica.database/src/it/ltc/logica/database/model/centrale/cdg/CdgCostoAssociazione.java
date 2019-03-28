package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_costo_associazione database table.
 * 
 */
public class CdgCostoAssociazione implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private double costo;
	private String nome;
	private int sede;

	public CdgCostoAssociazione() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSede() {
		return this.sede;
	}

	public void setSede(int sede) {
		this.sede = sede;
	}
	
	@Override
	public String toString() {
		return "CdgCostoAssociazione [id=" + id + ", costo=" + costo + ", nome=" + nome + ", sede=" + sede + "]";
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
		CdgCostoAssociazione other = (CdgCostoAssociazione) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
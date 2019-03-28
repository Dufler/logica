package it.ltc.logica.database.model.centrale.fatturazione;

import java.io.Serializable;


/**
 * The persistent class for the coordinate_bancarie database table.
 * 
 */
public class CoordinateBancarie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String coordinate;
	private String ente;

	public CoordinateBancarie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getEnte() {
		return this.ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
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
		CoordinateBancarie other = (CoordinateBancarie) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
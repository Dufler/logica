package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_periodico database table.
 * 
 */
public class CdgPeriodico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Tipo { INGRESSO, USCITA }
	
	
	private int id;
	private String descrizione;
	private String nome;
	private int periodo;
	private Tipo tipo;
	private double valore;

	public CdgPeriodico() {}

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

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
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
		CdgPeriodico other = (CdgPeriodico) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
package it.ltc.logica.database.model.centrale.ingressi;

import java.io.Serializable;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;

/**
 * The persistent class for the fornitore database table.
 * 
 */
public class Fornitore implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String note;
	private String riferimentoCliente;
	
	private Indirizzo indirizzo;

	public Fornitore() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRiferimentoCliente() {
		return riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

}
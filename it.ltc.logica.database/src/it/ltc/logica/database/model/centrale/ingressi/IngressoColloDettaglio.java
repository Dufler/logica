package it.ltc.logica.database.model.centrale.ingressi;

import java.io.Serializable;


/**
 * The persistent class for the ingresso_collo_dettaglio database table.
 * 
 */
public class IngressoColloDettaglio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String collo;

	private int idIngresso;

	private int idProdotto;

	private int quantitaStimata;

	private int quantitaVerificata;

	public IngressoColloDettaglio() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollo() {
		return this.collo;
	}

	public void setCollo(String collo) {
		this.collo = collo;
	}

	public int getIdIngresso() {
		return this.idIngresso;
	}

	public void setIdIngresso(int idIngresso) {
		this.idIngresso = idIngresso;
	}

	public int getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantitaStimata() {
		return this.quantitaStimata;
	}

	public void setQuantitaStimata(int quantitaStimata) {
		this.quantitaStimata = quantitaStimata;
	}

	public int getQuantitaVerificata() {
		return this.quantitaVerificata;
	}

	public void setQuantitaVerificata(int quantitaVerificata) {
		this.quantitaVerificata = quantitaVerificata;
	}

}
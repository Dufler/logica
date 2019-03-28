package it.ltc.logica.database.model.centrale;

import java.util.Date;

/**
 * Rappresenta una movimentazione di prodotto in un magazzino.
 * @author Damiano
 *
 */
public class MovimentoProdotto {
	
	private int id;
	
	private int idProdotto;
	private String skuProdotto;
	
	private String documentoRiferimento;
	private int documentoID;
	private Date dataMovimento;
	
	private CausaliMovimento causale;
	private String magazzino;
	private int quantita;
	
	public MovimentoProdotto() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getSkuProdotto() {
		return skuProdotto;
	}

	public void setSkuProdotto(String skuProdotto) {
		this.skuProdotto = skuProdotto;
	}

	public String getDocumentoRiferimento() {
		return documentoRiferimento;
	}

	public void setDocumentoRiferimento(String documentoRiferimento) {
		this.documentoRiferimento = documentoRiferimento;
	}

	public int getDocumentoID() {
		return documentoID;
	}

	public void setDocumentoID(int documentoID) {
		this.documentoID = documentoID;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date data) {
		this.dataMovimento = data;
	}

	public CausaliMovimento getCausale() {
		return causale;
	}

	public void setCausale(CausaliMovimento causale) {
		this.causale = causale;
	}

	public String getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
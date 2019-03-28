package it.ltc.logica.database.model.centrale.documenti;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the documento database table.
 * 
 */
public class Documento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum TipoDocumento { DDT, FAT, FATTURA, INGRESSO, INVENTARIO, CARICO, RESO, ORDINE, CAMPIONARIO, LAVORAZIONE, ALTRO }
	
	private int id;

	private Date dataCreazione;

	private int idCommessa;

	private String riferimentoCliente;

	private String riferimentoInterno;

	private TipoDocumento tipo;

	public Documento() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public String getRiferimentoCliente() {
		return this.riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getRiferimentoInterno() {
		return this.riferimentoInterno;
	}

	public void setRiferimentoInterno(String riferimentoInterno) {
		this.riferimentoInterno = riferimentoInterno;
	}

	public TipoDocumento getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}

}
package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the cdg_costi_ricavi_generici_date_valore database table.
 * 
 */
public class CdgCostiRicaviGenericiDateValore implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private Date dataEffettiva;
	private Date dataFine;
	private Date dataInizio;
	private String descrizione;
	private int generico;
	private Integer sede;
	private double valore;

	public CdgCostiRicaviGenericiDateValore() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataEffettiva() {
		return this.dataEffettiva;
	}

	public void setDataEffettiva(Date dataEffettiva) {
		this.dataEffettiva = dataEffettiva;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getGenerico() {
		return this.generico;
	}

	public void setGenerico(int generico) {
		this.generico = generico;
	}
	
	public Integer getSede() {
		return this.sede;
	}

	public void setSede(Integer sede) {
		this.sede = sede;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
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
		CdgCostiRicaviGenericiDateValore other = (CdgCostiRicaviGenericiDateValore) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CdgCostiRicaviGenericiDateValore [id=" + id + ", dataEffettiva=" + dataEffettiva + ", dataFine="
				+ dataFine + ", dataInizio=" + dataInizio + ", generico=" + generico + ", valore=" + valore + "]";
	}

}
package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * The persistent class for the cdg_budget database table.
 * 
 */
public class CdgBudget implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private int commessa;
	private double costo;
	private Date dataFine;
	private Date dataInizio;
	private String descrizione;
	private double ricavo;

	public CdgBudget() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
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
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
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
		CdgBudget other = (CdgBudget) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CdgBudget [commessa=" + commessa + ", costo=" + costo + ", dataFine=" + dataFine + ", dataInizio="
				+ dataInizio + ", ricavo=" + ricavo + "]";
	}
	
	public double getDurataInGiorni() {
		GregorianCalendar giornoInizioBudget = new GregorianCalendar();
		giornoInizioBudget.setTime(dataInizio);
		GregorianCalendar giornoFineBudget = new GregorianCalendar();
		giornoFineBudget.setTime(dataFine);
		double durataGiorniBudget = giornoFineBudget.get(Calendar.DAY_OF_YEAR) - giornoInizioBudget.get(Calendar.DAY_OF_YEAR);
		if (durataGiorniBudget < 0)
			durataGiorniBudget += 365;
		return durataGiorniBudget;
	}

}
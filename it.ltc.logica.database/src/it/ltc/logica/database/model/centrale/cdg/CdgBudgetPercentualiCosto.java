package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_budget_percentuali_costo database table.
 * 
 */
public class CdgBudgetPercentualiCosto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int budget;
	private int costoGenerico;
	private double percentuale;

	public CdgBudgetPercentualiCosto() {}

	public int getBudget() {
		return this.budget;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public int getCostoGenerico() {
		return this.costoGenerico;
	}
	
	public void setCostoGenerico(int costoGenerico) {
		this.costoGenerico = costoGenerico;
	}

	public double getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}

}
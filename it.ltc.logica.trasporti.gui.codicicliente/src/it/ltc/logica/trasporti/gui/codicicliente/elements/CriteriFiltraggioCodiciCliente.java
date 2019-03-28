package it.ltc.logica.trasporti.gui.codicicliente.elements;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioCodiciCliente extends CriteriFiltraggio {
	
	private String codice;
	private String stato;
	private Integer corriere;
	private Integer commessa;
	
	public CriteriFiltraggioCodiciCliente(String codice, String stato, Integer corriere, Integer commessa) {
		this.codice = codice;
		this.stato = stato;
		this.corriere = corriere;
		this.commessa = commessa;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getCorriere() {
		return corriere;
	}

	public void setCorriere(Integer corriere) {
		this.corriere = corriere;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public void setCommessa(Integer commessa) {
		this.commessa = commessa;
	}

}

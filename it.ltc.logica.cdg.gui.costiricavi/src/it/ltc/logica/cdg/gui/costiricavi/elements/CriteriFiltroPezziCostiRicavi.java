package it.ltc.logica.cdg.gui.costiricavi.elements;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltroPezziCostiRicavi extends CriteriFiltraggio {
	
	private final Integer commessa;
	private final String categoria;
	
	public CriteriFiltroPezziCostiRicavi(Integer commessa, String categoria) {
		this.commessa = commessa;
		this.categoria = categoria;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public String getCategoria() {
		return categoria;
	} 

}

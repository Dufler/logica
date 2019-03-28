package it.ltc.logica.admin.gui.elements;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriAmbiti extends CriteriFiltraggio {
	
	private final Integer idAmbito;
	
	public CriteriAmbiti(Integer id) {
		idAmbito = id;
	}

	public Integer getIdAmbito() {
		return idAmbito;
	}

}

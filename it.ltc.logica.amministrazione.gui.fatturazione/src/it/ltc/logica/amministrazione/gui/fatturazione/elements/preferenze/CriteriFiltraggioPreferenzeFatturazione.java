package it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioPreferenzeFatturazione extends CriteriFiltraggio {
	
	private Integer commessa;
	private Integer ambito;
	
	public CriteriFiltraggioPreferenzeFatturazione(Integer commessa, Integer ambito) {
		this.commessa = commessa;
		this.ambito = ambito;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public void setCommessa(Integer commessa) {
		this.commessa = commessa;
	}

	public Integer getAmbito() {
		return ambito;
	}

	public void setAmbito(Integer ambito) {
		this.ambito = ambito;
	}

}

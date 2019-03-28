package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltroEventi extends CriteriFiltraggio {
	
	private final Integer fase;
	private final String categoriaMerceologica;
	private final String testo;

	public CriteriFiltroEventi(Integer fase, String categoriaMerceologica, String testo) {
		this.fase = fase;
		this.categoriaMerceologica = categoriaMerceologica;
		this.testo = testo;
	}
	
	public Integer getFase() {
		return fase;
	}

	public String getCategoriaMerceologica() {
		return categoriaMerceologica;
	}

	public String getTesto() {
		return testo;
	}

}

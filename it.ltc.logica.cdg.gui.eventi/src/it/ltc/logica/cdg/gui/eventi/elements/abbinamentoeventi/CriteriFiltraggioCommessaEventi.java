package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioCommessaEventi extends CriteriFiltraggio {
	
	private Integer commessa;
	private Integer evento;

	public Integer getCommessa() {
		return commessa;
	}

	public void setCommessa(Integer commessa) {
		this.commessa = commessa;
	}

	public Integer getEvento() {
		return evento;
	}

	public void setEvento(Integer evento) {
		this.evento = evento;
	}

}

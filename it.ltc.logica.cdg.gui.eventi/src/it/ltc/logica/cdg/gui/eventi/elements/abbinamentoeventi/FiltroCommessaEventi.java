package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCommessaEventi extends FiltroTabella<CdgCommessaEvento, CriteriFiltraggioCommessaEventi> {

	@Override
	protected boolean checkElemento(CdgCommessaEvento item) {
		boolean checkCommessa = checkCommessa(item);
		boolean checkEvento = checkEvento(item);
		return checkCommessa && checkEvento;
	}

	private boolean checkEvento(CdgCommessaEvento item) {
		boolean check = criteri.getEvento() != null ? criteri.getEvento() == item.getEvento() : true; 
		return check;
	}

	private boolean checkCommessa(CdgCommessaEvento item) {
		boolean check = criteri.getCommessa() != null ? criteri.getCommessa() == item.getCommessa() : true; 
		return check;
	}

}

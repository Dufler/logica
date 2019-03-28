package it.ltc.logica.gui.common.tables.indirizzo;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroIndirizzi extends FiltroTabella<Indirizzo, CriteriFiltraggioSoloTesto> {
	
	@Override
	protected boolean checkElemento(Indirizzo indirizzo) {
		boolean toShow;
		toShow = checkRagioneSociale(indirizzo);
		if (toShow)
			toShow = checkIndirizzo(indirizzo);
		return toShow;
	}

	private boolean checkRagioneSociale(Indirizzo indirizzo) {
		boolean check = checkStringValue(criteri.getTesto(), indirizzo.getRagioneSociale());
		return check;
	}
	
	private boolean checkIndirizzo(Indirizzo indirizzo) {
		boolean checkIndirizzo = checkStringValue(criteri.getTesto(), indirizzo.getIndirizzo());
		boolean checkLocalita = checkStringValue(criteri.getTesto(), indirizzo.getLocalita());
		return checkIndirizzo || checkLocalita;
	}

}

package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroIndirizziSimulazione extends FiltroTabella<IndirizzoSimulazione, CriteriFiltraggioSoloTesto> {
	
	@Override
	protected boolean checkElemento(IndirizzoSimulazione indirizzo) {
		boolean toShow;
		toShow = checkRagioneSociale(indirizzo);
		if (toShow)
			toShow = checkIndirizzo(indirizzo);
		return toShow;
	}

	private boolean checkRagioneSociale(IndirizzoSimulazione indirizzo) {
		boolean check = checkStringValue(criteri.getTesto(), indirizzo.getRagioneSociale());
		return check;
	}
	
	private boolean checkIndirizzo(IndirizzoSimulazione indirizzo) {
		boolean checkIndirizzo = checkStringValue(criteri.getTesto(), indirizzo.getIndirizzo());
		boolean checkLocalita = checkStringValue(criteri.getTesto(), indirizzo.getLocalita());
		return checkIndirizzo || checkLocalita;
	}

}

package it.ltc.logica.trasporti.gui.listini.elements.simulazione;

import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroListiniSimulazione extends FiltroTabella<ListinoSimulazione, CriteriFiltraggioSoloTesto> {
	
	@Override
	protected boolean checkElemento(ListinoSimulazione listino) {
		boolean checkNome = checkStringValue(criteri.getTesto(), listino.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), listino.getDescrizione());
		return checkNome || checkDescrizione;
	}
	
}

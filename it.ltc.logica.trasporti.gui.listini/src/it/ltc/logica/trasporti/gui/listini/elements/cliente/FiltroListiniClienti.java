package it.ltc.logica.trasporti.gui.listini.elements.cliente;

import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroListiniClienti extends FiltroTabella<ListinoCommessa, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(ListinoCommessa listino) {
		boolean checkNome = checkStringValue(criteri.getTesto(), listino.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), listino.getDescrizione());
		return checkNome || checkDescrizione;
	}

}

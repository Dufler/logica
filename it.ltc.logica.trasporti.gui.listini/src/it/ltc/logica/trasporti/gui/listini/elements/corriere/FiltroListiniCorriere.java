package it.ltc.logica.trasporti.gui.listini.elements.corriere;

import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroListiniCorriere extends FiltroTabella<ListinoCorriere, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(ListinoCorriere listino) {
		boolean checkNome = checkStringValue(criteri.getTesto(), listino.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), listino.getDescrizione());
		return checkNome || checkDescrizione;
	}

}

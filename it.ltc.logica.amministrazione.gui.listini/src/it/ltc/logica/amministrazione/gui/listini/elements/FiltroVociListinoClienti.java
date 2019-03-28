package it.ltc.logica.amministrazione.gui.listini.elements;

import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroVociListinoClienti extends FiltroTabella<VoceDiListino, CriteriFiltraggioSoloTesto> {
	
	@Override
	protected boolean checkElemento(VoceDiListino voce) {
		boolean checkNome = checkStringValue(criteri.getTesto(), voce.getNome());
		return checkNome;
	}

}

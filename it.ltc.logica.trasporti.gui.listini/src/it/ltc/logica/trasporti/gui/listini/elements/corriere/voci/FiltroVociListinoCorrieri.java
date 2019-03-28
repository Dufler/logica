package it.ltc.logica.trasporti.gui.listini.elements.corriere.voci;

import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroVociListinoCorrieri extends FiltroTabella<VoceDiListinoCorriere, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(VoceDiListinoCorriere item) {
		boolean checkNome = checkStringValue(criteri.getTesto(), item.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), item.getDescrizione());
		return checkNome || checkDescrizione;
	}

}

package it.ltc.logica.trasporti.gui.listini.elements.cliente.voci;

import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroVociListinoClienti extends FiltroTabella<VoceDiListino, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(VoceDiListino item) {
		boolean checkNome = checkStringValue(criteri.getTesto(), item.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), item.getDescrizione());
		return checkNome || checkDescrizione;
	}

}

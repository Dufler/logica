package it.ltc.logica.amministrazione.gui.commesse.elements;

import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroClienti extends FiltroTabella<Cliente, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(Cliente item) {
		boolean check = checkStringValue(criteri.getTesto(), item.getRagioneSociale());
		return check;
	}

}

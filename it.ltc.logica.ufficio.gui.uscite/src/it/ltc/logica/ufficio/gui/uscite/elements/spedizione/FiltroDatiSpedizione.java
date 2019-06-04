package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroDatiSpedizione extends FiltroTabella<DatiSpedizione, CriteriFiltroDatiSpedizione> {

	@Override
	protected boolean checkElemento(DatiSpedizione item) {
		
		return false;
	}

}

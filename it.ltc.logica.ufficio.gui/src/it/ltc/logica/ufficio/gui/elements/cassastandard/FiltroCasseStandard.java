package it.ltc.logica.ufficio.gui.elements.cassastandard;

import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCasseStandard extends FiltroTabella<CassaStandard, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(CassaStandard item) {
		boolean check = checkStringValue(criteri.getTesto(), item.getCodiceCassa());
		return check;
	}

}

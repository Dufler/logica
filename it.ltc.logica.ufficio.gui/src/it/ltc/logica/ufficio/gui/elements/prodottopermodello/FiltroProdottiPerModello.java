package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import it.ltc.logica.common.controller.prodotti.ProdottiPerModello;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroProdottiPerModello extends FiltroTabella<ProdottiPerModello, CriteriFiltraggioSoloTesto> {

	@Override
	protected boolean checkElemento(ProdottiPerModello ppm) {
		boolean select = checkStringValue(criteri.getTesto(), ppm.getCodiceModello());
		return select;
	}

}

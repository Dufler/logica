package it.ltc.logica.cdg.gui.costiricavi.elements;

import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroPezziCostiRicavi extends FiltroTabella<CdgPezzo, CriteriFiltroPezziCostiRicavi> {

	@Override
	protected boolean checkElemento(CdgPezzo item) {
		boolean checkCommessa = checkIntValue(criteri.getCommessa(), item.getCommessa());
		boolean checkCategoria = checkStringValue(criteri.getCategoria(), item.getCategoriaMerceologica());
		return checkCommessa && checkCategoria;
	}

}

package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCostiRicaviGenerici extends FiltroTabella<CdgCostiRicaviGenerici, CriteriFiltraggioCostiRicaviGenerici> {

	@Override
	protected boolean checkElemento(CdgCostiRicaviGenerici item) {
		boolean checkNome = checkStringValue(criteri.getTesto(), item.getNome());
		//boolean checkSede = checkIntValue(criteri.getSede(), item.getSede());
		boolean checkDriver = criteri.getDriver() != null ? criteri.getDriver() == item.getDriver() : true;
		return checkNome && /*checkSede &&*/ checkDriver;
	}

}

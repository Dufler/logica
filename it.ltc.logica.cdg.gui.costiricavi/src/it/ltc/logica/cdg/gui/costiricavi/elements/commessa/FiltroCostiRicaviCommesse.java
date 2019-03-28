package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCostiRicaviCommesse extends FiltroTabella<CdgCostoRicavoCommessa, CriteriFiltraggioCostiRicaviCommesse> {

	@Override
	protected boolean checkElemento(CdgCostoRicavoCommessa item) {
		boolean checkCommessa = checkIntValue(criteri.getCommessa(), item.getCommessa());
		boolean checkData = checkDateBetween(item.getDataEmissione(), criteri.getDa(), criteri.getA());
		return checkCommessa && checkData;
	}

}

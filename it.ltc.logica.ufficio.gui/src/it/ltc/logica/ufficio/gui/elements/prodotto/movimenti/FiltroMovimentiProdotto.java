package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import it.ltc.logica.database.model.centrale.MovimentoProdotto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroMovimentiProdotto extends FiltroTabella<MovimentoProdotto, CriteriFiltraggioMovimentiProdotto> {

	@Override
	protected boolean checkElemento(MovimentoProdotto item) {
		boolean checkDataDa = checkDateAfter(item.getDataMovimento(), criteri.getDa());
		boolean checkDataA = checkDateBefore(item.getDataMovimento(), criteri.getA());
		boolean checkCausale = checkStringValue(criteri.getCausale() != null ? criteri.getCausale().name() : null, item.getCausale().name());
		boolean checkMagazzino = checkStringValue(criteri.getMagazzino(), item.getMagazzino());
		return checkDataDa && checkDataA && checkCausale && checkMagazzino;
	}

}

package it.ltc.logica.ufficio.gui.elements.cassa;

import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCasse extends FiltroTabella<Cassa, CriteriFiltroCassa> {

	@Override
	protected boolean checkElemento(Cassa item) {
		boolean codice = checkStringValue(criteri.getTesto(), item.getCodiceCassa());
		boolean modello = checkStringValue(criteri.getTesto(), item.getModello());
		boolean tipo = criteri.getTipoCassa() != null ? criteri.getTipoCassa() == item.getTipo() : true;
		return tipo && (codice || modello);
	}

}

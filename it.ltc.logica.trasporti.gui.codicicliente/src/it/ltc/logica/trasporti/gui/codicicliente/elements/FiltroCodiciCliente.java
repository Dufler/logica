package it.ltc.logica.trasporti.gui.codicicliente.elements;

import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCodiciCliente extends FiltroTabella<CodiceClienteCorriere, CriteriFiltraggioCodiciCliente> {

	@Override
	protected boolean checkElemento(CodiceClienteCorriere item) {
		boolean checkCodice = checkStringValue(criteri.getCodice(), item.getCodiceCliente());
		boolean checkCorriere = checkIntValue(criteri.getCorriere(), item.getCorriere());
		boolean checkCommessa = checkIntValue(criteri.getCommessa(), item.getCommessa());
		boolean checkStato = checkStringValue(criteri.getStato(), item.getStato());
		return checkCodice && checkCorriere && checkCommessa && checkStato;
	}

}

package it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie;

import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroTabellaCategorie extends FiltroTabella<CategoriaMerceologica, CriteriFiltroCategoria> {

	@Override
	protected boolean checkElemento(CategoriaMerceologica item) {
		boolean checkCommessa = checkIntValue(criteri.getCommessa() != null ? criteri.getCommessa().getId() : null, item.getCommessa());
		boolean checkCodice = checkStringValue(criteri.getTesto(), item.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), item.getDescrizione());
		return checkCommessa && (checkCodice || checkDescrizione);
	}

}

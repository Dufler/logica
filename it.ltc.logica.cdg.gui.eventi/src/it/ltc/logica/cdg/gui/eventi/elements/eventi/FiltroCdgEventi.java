package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public class FiltroCdgEventi extends FiltroTabella<CdgEvento, CriteriFiltroEventi> {

	@Override
	protected boolean checkElemento(CdgEvento item) {
		boolean check = checkFase(item);
		if (check) {
			check = checkCategoria(item);
			if (check) {
				check = checkTesto(item);
			}
		}
		return check;
	}
	
	private boolean checkTesto(CdgEvento item) {
		boolean checkNome = checkStringValue(criteri.getTesto(), item.getNome());
		boolean checkDescrizione = checkStringValue(criteri.getTesto(), item.getDescrizione());
		return checkNome || checkDescrizione;
	}

	private boolean checkCategoria(CdgEvento item) {
		boolean check = checkStringValue(criteri.getCategoriaMerceologica(), item.getCategoriaMerceologica());
		return check;
	}

	private boolean checkFase(CdgEvento item) {
		boolean check = criteri.getFase() != null ? criteri.getFase() == item.getFase() : true;
		return check;
	}

}

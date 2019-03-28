package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCdgEventi extends Ordinatore<CdgEvento> {

	@Override
	protected int compare(CdgEvento t1, CdgEvento t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareInteger(t1.getFase(), t2.getFase()); break;
			case 2 : compare = compareString(t1.getCategoriaMerceologica(), t2.getCategoriaMerceologica()); break;
			case 3 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0; 
		}
		return compare;
	}

}

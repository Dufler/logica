package it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie;

import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCategorie extends Ordinatore<CategoriaMerceologica> {

	@Override
	protected int compare(CategoriaMerceologica t1, CategoriaMerceologica t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = compareString(t1.getNome(), t2.getNome()); break;
			case 1 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			case 2 : compare = compareInteger(t1.getCommessa(), t2.getCommessa()); break;
			case 4 : compare = t1.getStato().compareTo(t2.getStato()); break;
			default : compare = 0;
		}
		return compare;
	}

}

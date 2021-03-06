package it.ltc.logica.trasporti.gui.listini.elements.corriere.voci;

import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreVociListinoCorrieri extends Ordinatore<VoceDiListinoCorriere> {

	@Override
	protected int compare(VoceDiListinoCorriere t1, VoceDiListinoCorriere t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getNome().compareTo(t2.getNome()); break;
			case 1 : compare = t1.getIdSottoAmbito().compareTo(t2.getIdSottoAmbito()); break;
			case 2 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}
	
}

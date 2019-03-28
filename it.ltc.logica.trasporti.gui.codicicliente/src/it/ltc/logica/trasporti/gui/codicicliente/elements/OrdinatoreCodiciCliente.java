package it.ltc.logica.trasporti.gui.codicicliente.elements;

import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreCodiciCliente extends Ordinatore<CodiceClienteCorriere> {

	@Override
	protected int compare(CodiceClienteCorriere t1, CodiceClienteCorriere t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = t1.getCodiceCliente().compareTo(t2.getCodiceCliente()); break;
			case 1 : compare = t1.getStato().compareTo(t2.getStato()); break;
			case 2 : compare = t1.getCorriere().compareTo(t2.getCorriere()); break;
			//case 3 : compare = t1.getIdListinoCorriere().compareTo(t2.getIdListinoCorriere()); break;
			case 4 : compare = t1.getCommessa().compareTo(t2.getCommessa()); break;
			//case 5 : compare = t1.getIdListinoCommessa().compareTo(t2.getIdListinoCommessa()); break;
			case 6 : compare = t1.getDescrizione().compareTo(t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

package it.ltc.logica.gui.common.tables.fatturazione;

import java.util.Date;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura.Stato;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreDocumentiFatturazione extends Ordinatore<DocumentoFattura> {

	@Override
	protected int compare(DocumentoFattura t1, DocumentoFattura t2, int property) {
		int compare;
		switch (property) {
			case 0 : compare = ordinaPerCommessa(t1, t2); break;
			case 1 : compare = ordinaPerTipoDocumento(t1, t2); break;
			case 2 : compare = ordinaPerStato(t1, t2); break;
			case 3 : compare = ordinaPerPeriodo(t1, t2); break;
			case 4 : compare = ordinaPerData(t1, t2); break;
			case 5 : compare = ordinaPerUtente(t1, t2); break;
			default : compare = 0;
		}
		return compare;
	}

	private int ordinaPerPeriodo(DocumentoFattura t1, DocumentoFattura t2) {
		int compare = t1.getMeseAnno().compareTo(t2.getMeseAnno());
		return compare;
	}

	private int ordinaPerUtente(DocumentoFattura t1, DocumentoFattura t2) {
		int compare = t1.getUtenteCreatore().compareTo(t2.getUtenteCreatore());
		return compare;
	}

	private int ordinaPerStato(DocumentoFattura t1, DocumentoFattura t2) {
		int compare = 0;
		Stato s1 = t1.getStato();
		Stato s2 = t2.getStato();
		if (s1 != null && s2 != null)
			compare = s1.compareTo(s2);
		return compare;
	}

	private int ordinaPerData(DocumentoFattura t1, DocumentoFattura t2) {
		Date d1 = t1.getDataGenerazione();
		Date d2 = t2.getDataGenerazione();
		int compare = d1.compareTo(d2);
		return compare;
	}

	private int ordinaPerTipoDocumento(DocumentoFattura t1, DocumentoFattura t2) {
		int compare = 0;
		AmbitoFattura m1 = ControllerAmbitiFatturazione.getInstance().getAmbito(t1.getIdAmbito());
		AmbitoFattura m2 = ControllerAmbitiFatturazione.getInstance().getAmbito(t2.getIdAmbito());
		if (m1 != null && m2 != null)
			compare = m1.getNome().compareTo(m2.getNome());
		return compare;
	}

	private int ordinaPerCommessa(DocumentoFattura t1, DocumentoFattura t2) {
		int compare = 0;
		Commessa c1 = ControllerCommesse.getInstance().getCommessa(t1.getIdCommessa());
		Commessa c2 = ControllerCommesse.getInstance().getCommessa(t2.getIdCommessa());
		if (c1 != null && c2 != null)
			compare = c1.getNome().compareTo(c2.getNome());
		return compare;
	}

}

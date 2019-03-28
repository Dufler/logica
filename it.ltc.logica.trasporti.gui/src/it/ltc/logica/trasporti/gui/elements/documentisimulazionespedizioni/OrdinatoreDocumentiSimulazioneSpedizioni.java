package it.ltc.logica.trasporti.gui.elements.documentisimulazionespedizioni;

import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;
import it.ltc.logica.gui.elements.Ordinatore;

public class OrdinatoreDocumentiSimulazioneSpedizioni extends Ordinatore<DocumentoSpedizioniSimulazione> {

	@Override
	protected int compare(DocumentoSpedizioniSimulazione t1, DocumentoSpedizioniSimulazione t2, int property) {
		int compare;
		switch (property) {
			case 1 : compare = compareString(t1.getNomeFile(), t2.getNomeFile()); break;
			case 2 : compare = compareString(t1.getTipo(), t2.getTipo()); break;
			case 3 : compare = compareDate(t1.getDataImportazione(), t2.getDataImportazione()); break;
			case 4 : compare = compareString(t1.getDescrizione(), t2.getDescrizione()); break;
			default : compare = 0;
		}
		return compare;
	}

}

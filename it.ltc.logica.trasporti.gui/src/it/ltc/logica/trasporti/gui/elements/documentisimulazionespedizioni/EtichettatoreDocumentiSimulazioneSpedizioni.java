package it.ltc.logica.trasporti.gui.elements.documentisimulazionespedizioni;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreDocumentiSimulazioneSpedizioni extends Etichettatore<DocumentoSpedizioniSimulazione> {

	@Override
	public String getTesto(DocumentoSpedizioniSimulazione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getNomeFile(); break;
			case 2 : testo = oggetto.getTipo(); break;
			case 3 : testo = sdf.format(oggetto.getDataImportazione()); break;
			case 4 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(DocumentoSpedizioniSimulazione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(DocumentoSpedizioniSimulazione oggetto, int colonna) {
		return null;
	}

}

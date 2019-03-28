package it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCoordinate extends Etichettatore<CoordinateBancarie> {

	@Override
	public String getTesto(CoordinateBancarie oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome() != null ? oggetto.getNome() : "N/A"; break;
			case 1 : testo = oggetto.getEnte() != null ? oggetto.getEnte() : "N/A"; break;
			case 2 : testo = oggetto.getCoordinate() != null ? oggetto.getCoordinate() : "N/A"; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(CoordinateBancarie oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CoordinateBancarie oggetto, int colonna) {
		return null;
	}

}

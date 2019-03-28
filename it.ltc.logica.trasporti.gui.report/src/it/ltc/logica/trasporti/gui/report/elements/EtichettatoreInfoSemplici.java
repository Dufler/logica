package it.ltc.logica.trasporti.gui.report.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.trasporti.controller.ReportElement;

public class EtichettatoreInfoSemplici extends Etichettatore<ReportElement> {

	@Override
	public String getTesto(ReportElement oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getKey(); break;
			case 2 : testo = oggetto.getValue(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ReportElement oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ReportElement oggetto, int colonna) {
		return null;
	}

}

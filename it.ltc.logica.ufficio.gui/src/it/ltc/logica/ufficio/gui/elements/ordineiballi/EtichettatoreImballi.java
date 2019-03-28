package it.ltc.logica.ufficio.gui.elements.ordineiballi;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreImballi extends Etichettatore<ImballoCollo> {

	@Override
	public String getTesto(ImballoCollo oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getRiferimento(); break;
			case 2 : testo = formatDecimali.format(oggetto.getPeso()) + " Kg"; break;
			case 3 : testo = formatDecimali.format(oggetto.getVolume()) + " mc"; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ImballoCollo oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ImballoCollo oggetto, int colonna) {
		return null;
	}

}

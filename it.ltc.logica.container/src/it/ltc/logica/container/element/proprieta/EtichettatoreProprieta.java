package it.ltc.logica.container.element.proprieta;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.locale.ProprietaLogica;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreProprieta extends Etichettatore<ProprietaLogica> {

	@Override
	public String getTesto(ProprietaLogica oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getKey(); break;
			case 1 : testo = oggetto.getValue(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ProprietaLogica oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ProprietaLogica oggetto, int colonna) {
		return null;
	}

}

package it.ltc.logica.gui.common.tables.nazioni;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreNazioni extends Etichettatore<Nazione> {

	@Override
	public String getTesto(Nazione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getCodiceIsoTre(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Nazione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Nazione oggetto, int colonna) {
		return null;
	}

}

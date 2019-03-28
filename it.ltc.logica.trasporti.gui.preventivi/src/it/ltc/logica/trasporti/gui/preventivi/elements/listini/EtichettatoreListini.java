package it.ltc.logica.trasporti.gui.preventivi.elements.listini;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.listini.Listino;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreListini extends Etichettatore<Listino> {

	@Override
	public String getTesto(Listino oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Listino oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Listino oggetto, int colonna) {
		return null;
	}

}

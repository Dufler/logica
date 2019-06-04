package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreModelli extends Etichettatore<Modello> {

	@Override
	public String getTesto(Modello oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getModello(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Modello oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Modello oggetto, int colonna) {
		return null;
	}

}

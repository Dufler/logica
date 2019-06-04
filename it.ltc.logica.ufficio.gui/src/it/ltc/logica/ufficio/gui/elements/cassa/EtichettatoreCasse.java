package it.ltc.logica.ufficio.gui.elements.cassa;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCasse extends Etichettatore<Cassa> {

	@Override
	public String getTesto(Cassa oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getCodiceCassa(); break;
			case 1 : testo = oggetto.getModello(); break;
			case 2 : testo = oggetto.getTipo().toString(); break;
			case 3 : testo = Integer.toString(oggetto.getTotalePezzi()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Cassa oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Cassa oggetto, int colonna) {
		return null;
	}

}

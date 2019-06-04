package it.ltc.logica.ufficio.gui.uscite.elements.delivery;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreDelivery extends Etichettatore<Delivery> {

	@Override
	public String getTesto(Delivery oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = sdf.format(oggetto.getDataGenerazione()); break;
			case 1 : testo = oggetto.getCorriere(); break;
			case 2 : testo = Integer.toString(oggetto.getTotaleSpedizioni()); break;
			case 3 : testo = Integer.toString(oggetto.getTotaleColli()); break;
			case 4 : testo = Double.toString(oggetto.getTotalePeso()) + " Kg"; break;
			case 5 : testo = Double.toString(oggetto.getTotaleVolume()) + " Mc"; break;
			case 6 : testo = oggetto.getUtente(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Delivery oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Delivery oggetto, int colonna) {
		return null;
	}

}

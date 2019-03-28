package it.ltc.logica.trasporti.gui.elements.tracking;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.Tracking;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreTracking extends Etichettatore<Tracking> {
	
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
	
	public EtichettatoreTracking() {
		super(DATE_FORMAT, DEFAULT_EURO_FORMAT, DEFAULT_DECIMAL_FORMAT, DEFAULT_PERCENTAGE_FORMAT);
	}

	@Override
	public String getTesto(Tracking oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = sdf.format(oggetto.getData()); break;
			case 1 : testo = oggetto.getStato(); break;
			case 2 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Tracking oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Tracking oggetto, int colonna) {
		return null;
	}

}

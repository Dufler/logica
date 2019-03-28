package it.ltc.logica.trasporti.gui.elements.cap;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCap extends Etichettatore<Cap> {

	@Override
	public String getTesto(Cap oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getCap(); break;
			case 1 : testo = oggetto.getLocalita(); break;
			case 2 : testo = null; break;
			case 3 : testo = null; break;
			case 4 : testo = oggetto.getBrtDisagiate() ? "Si" : "No"; break;
			case 5 : testo = oggetto.getBrtIsole() ? "Si" : "No"; break;
			case 6 : testo = oggetto.getBrtZtl() ? "Si" : "No"; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Cap oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Cap oggetto, int colonna) {
		Image iconToShow;
		switch (colonna) {
			case 2 : iconToShow = oggetto.getTntOreDieci() ? Immagine.SPUNTAVERDE_16X50.getImage() : Immagine.CROCEROSSA_16X50.getImage(); break;
			case 3 : iconToShow = oggetto.getTntOreDodici() ? Immagine.SPUNTAVERDE_16X50.getImage() : Immagine.CROCEROSSA_16X50.getImage(); break;
			default : iconToShow = null;
		}
		return iconToShow;
	}

}

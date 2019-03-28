package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreVoceCalcolata extends Etichettatore<VoceCalcolata> {

	@Override
	public String getTesto(VoceCalcolata oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNomeVoce(); break;
			case 1 : testo = oggetto.getNomeAmbito(); break;
			case 2 : testo = Decorator.getEuroValue(oggetto.getCosto()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(VoceCalcolata oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNomeVoce(); break;
			case 1 : testo = oggetto.getDescrizioneAmbito(); break;
			case 2 : testo = Decorator.getEuroValue(oggetto.getCosto()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(VoceCalcolata oggetto, int colonna) {
		Image image = null;
		if (colonna == 0 && oggetto.getCosto() <= 0) {
			image = Immagine.RED_MARK_12X20.getImage();
		}
		return image;
	}

}

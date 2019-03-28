package it.ltc.logica.trasporti.gui.preventivi.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.trasporti.controller.preventivi.TotaleListino;

public class EtichettatoreTotali extends Etichettatore<TotaleListino> {

	@Override
	public String getTesto(TotaleListino oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome() != null ? oggetto.getNome() : "-"; break;
			case 1 : testo = oggetto.getTipoListino() != null ? oggetto.getTipoListino() : "-"; break;
			case 2 : testo = Decorator.getEuroValue(oggetto.getTotale()); break;
			case 3 : testo = Decorator.getEuroValue(oggetto.getNolo()); break;
			case 4 : testo = Decorator.getEuroValue(oggetto.getContrassegno()); break;
			case 5 : testo = Decorator.getEuroValue(oggetto.getExtra()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(TotaleListino oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(TotaleListino oggetto, int colonna) {
		Image iconToShow;
		if (colonna == 0 && oggetto.isCostoMinore())
			iconToShow = Immagine.SOLDIGIUGIALLO_16X32.getImage();
		else if (colonna == 0 && oggetto.isRicavoMaggiore())
			iconToShow = Immagine.SOLDISU_16X32.getImage();
		else
			iconToShow = null;
		return iconToShow;
	}

}

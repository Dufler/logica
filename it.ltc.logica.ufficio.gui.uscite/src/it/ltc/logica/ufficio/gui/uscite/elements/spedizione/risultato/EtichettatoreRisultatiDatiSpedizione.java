package it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreRisultatiDatiSpedizione extends Etichettatore<RisultatoSalvataggioDatiSpedizione> {

	@Override
	public String getTesto(RisultatoSalvataggioDatiSpedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getRiferimento(); break;
			case 1 : testo = oggetto.getOrdine(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(RisultatoSalvataggioDatiSpedizione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(RisultatoSalvataggioDatiSpedizione oggetto, int colonna) {
		Image image;
		if (colonna == 0) {
			image = oggetto.isRisultato() ? Immagine.SPUNTAVERDE_16X50.getImage() : Immagine.CROCEROSSA_16X50.getImage();
		} else {
			image = null;
		}
		return image;
	}

}

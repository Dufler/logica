package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatorePreventivi extends Etichettatore<Calcolo> {
	
	private final int colli;
	private final int pezzi;
	
	public EtichettatorePreventivi(int pezzi, int colli) {
		this.pezzi = pezzi;
		this.colli = colli;
	}

	@Override
	public String getTesto(Calcolo oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = getTotale(oggetto); break;
			case 2 : testo = getIncidenzaPezzo(oggetto); break;
			case 3 : testo = getIncidenzaCollo(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Calcolo oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(Calcolo oggetto, int colonna) {
		Image icon;
		if (colonna == 0 && oggetto.isCostoMinore()) {
			icon = Immagine.SALVADANAIO_16X16.getImage();
		} else if (colonna == 0 && oggetto.isRicavoMaggiore()){
			icon = Immagine.MONETE_16X16.getImage();
		} else {
			icon = null;
		}
		return icon;
	}
	
	private String getTotale(Calcolo oggetto) {
		double valore = oggetto.getTotale();
		String testo = Decorator.getEuroValue(valore);
		return testo;
	}
	
	private String getIncidenzaPezzo(Calcolo oggetto) {
		double valore = oggetto.getTotale() / pezzi;
		String testo = formatEuro.format(valore);
		return testo;
	}
	
	private String getIncidenzaCollo(Calcolo oggetto) {
		double valore = oggetto.getTotale() / colli;
		String testo = formatEuro.format(valore);
		return testo;
	}

}

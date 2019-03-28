package it.ltc.logica.trasporti.gui.elements.scaglioni;

import java.text.DecimalFormat;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.trasporti.gui.elements.scaglioni.TabellaScaglioni.TipoScaglioni;

public class EtichettatoreScaglioni extends Etichettatore<Scaglione> {
	
	private final DecimalFormat f;
	
	public EtichettatoreScaglioni(TipoScaglioni tipo) {
		switch (tipo) {
			case DECIMALI : f = formatDecimali; break;
			case INTERI : f = formatInteri; break;
			default : f = formatDecimali;
		}
	}

	@Override
	public String getTesto(Scaglione oggetto, int colonna) {
		String testo;
		switch (colonna) {
		case 1 : testo = f.format(oggetto.getInizio()); break;
		case 2 : testo = f.format(oggetto.getFine()); break;
		case 3 : testo = formatEuro.format(oggetto.getValore()); break;
		default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Scaglione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Scaglione oggetto, int colonna) {
		return null;
	}

}

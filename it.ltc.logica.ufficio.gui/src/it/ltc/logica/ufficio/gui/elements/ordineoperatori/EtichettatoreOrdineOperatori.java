package it.ltc.logica.ufficio.gui.elements.ordineoperatori;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.OperatoreOrdine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreOrdineOperatori extends Etichettatore<OperatoreOrdine> {

	@Override
	public String getTesto(OperatoreOrdine oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getOperatore(); break;
			case 1 : testo = oggetto.getOperazione(); break;
			case 2 : testo = Integer.toString(oggetto.getQuantita()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(OperatoreOrdine oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(OperatoreOrdine oggetto, int colonna) {
		return null;
	}

}

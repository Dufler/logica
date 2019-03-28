package it.ltc.logica.ufficio.gui.elements.ordinestati;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.OrdineStato;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreOrdineStati extends Etichettatore<OrdineStato> {
	
	public EtichettatoreOrdineStati() {
		super(HIGH_PRECISION_DATE_FORMAT, DEFAULT_EURO_FORMAT, DEFAULT_DECIMAL_FORMAT, DEFAULT_PERCENTAGE_FORMAT);
	}

	@Override
	public String getTesto(OrdineStato oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getStato().toString(); break;
			case 2 : testo = sdf.format(oggetto.getData()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(OrdineStato oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(OrdineStato oggetto, int colonna) {
		return null;
	}
}

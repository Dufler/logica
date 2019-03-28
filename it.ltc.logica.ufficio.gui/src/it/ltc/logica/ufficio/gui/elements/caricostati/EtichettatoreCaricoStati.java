package it.ltc.logica.ufficio.gui.elements.caricostati;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ingressi.CaricoStato;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCaricoStati extends Etichettatore<CaricoStato> {
	
	public EtichettatoreCaricoStati() {
		super(HIGH_PRECISION_DATE_FORMAT, DEFAULT_EURO_FORMAT, DEFAULT_DECIMAL_FORMAT, DEFAULT_PERCENTAGE_FORMAT);
	}

	@Override
	public String getTesto(CaricoStato oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getStato().toString(); break;
			case 2 : testo = sdf.format(oggetto.getData()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(CaricoStato oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CaricoStato oggetto, int colonna) {
		return null;
	}

}

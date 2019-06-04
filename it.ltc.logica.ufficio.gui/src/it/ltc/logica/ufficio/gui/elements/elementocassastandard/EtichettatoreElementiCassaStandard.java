package it.ltc.logica.ufficio.gui.elements.elementocassastandard;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.ElementoCassaStandard;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreElementiCassaStandard extends Etichettatore<ElementoCassaStandard> {

	@Override
	public String getTesto(ElementoCassaStandard oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getTaglia(); break;
			case 1 : testo = Integer.toString(oggetto.getQuantita()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ElementoCassaStandard oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ElementoCassaStandard oggetto, int colonna) {
		return null;
	}

}

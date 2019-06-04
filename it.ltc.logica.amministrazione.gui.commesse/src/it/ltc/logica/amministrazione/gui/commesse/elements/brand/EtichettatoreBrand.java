package it.ltc.logica.amministrazione.gui.commesse.elements.brand;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreBrand extends Etichettatore<Brand> {

	@Override
	public String getTesto(Brand oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = Integer.toString(oggetto.getCodice()); break;
			case 1 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Brand oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Brand oggetto, int colonna) {
		return null;
	}

}

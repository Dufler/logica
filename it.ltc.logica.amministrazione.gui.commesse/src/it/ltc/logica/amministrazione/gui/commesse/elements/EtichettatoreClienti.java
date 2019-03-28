package it.ltc.logica.amministrazione.gui.commesse.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreClienti extends Etichettatore<Cliente> {

	@Override
	public String getTesto(Cliente oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getRagioneSociale(); break;
			case 1 : testo = oggetto.getPartitaIva(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Cliente oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Cliente oggetto, int colonna) {
		return null;
	}

}

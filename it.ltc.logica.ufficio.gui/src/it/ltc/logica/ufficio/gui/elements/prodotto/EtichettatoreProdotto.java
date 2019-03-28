package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreProdotto extends Etichettatore<Prodotto> {

	@Override
	public String getTesto(Prodotto oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getCodiceModello(); break;
			case 2 : testo = oggetto.getChiaveCliente(); break;
			case 3 : testo = oggetto.getTaglia(); break;
			case 4 : testo = oggetto.getColore(); break;
			case 5 : testo = oggetto.getCategoria(); break;
			case 6 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(Prodotto oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Prodotto oggetto, int colonna) {
		return null;
	}

}

package it.ltc.logica.gui.common.tables.indirizzo;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreIndirizzi extends Etichettatore<Indirizzo> {

	@Override
	public String getTesto(Indirizzo oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto != null ? oggetto.getRagioneSociale() : "N/A"; break;
			case 1 : testo = oggetto != null ? getIndirizzo(oggetto) : "N/A"; break;
			default : testo = "";
		}
		return testo;
	}

	private String getIndirizzo(Indirizzo indirizzo) {
		String text = indirizzo.getIndirizzo();
		text += " " + indirizzo.getLocalita();
		String provincia = indirizzo.getProvincia();
		if (provincia != null && !provincia.isEmpty())
			text += " (" + provincia + ") ";
		text += " - " + indirizzo.getCap();
		return text;
	}

	@Override
	public String getTestoTooltip(Indirizzo oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(Indirizzo oggetto, int colonna) {
		return null;
	}

}

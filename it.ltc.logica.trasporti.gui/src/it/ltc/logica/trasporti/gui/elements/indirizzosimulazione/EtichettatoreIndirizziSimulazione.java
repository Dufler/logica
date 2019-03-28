package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreIndirizziSimulazione extends Etichettatore<IndirizzoSimulazione> {
	
	@Override
	public String getTesto(IndirizzoSimulazione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto != null ? oggetto.getRagioneSociale() : "N/A"; break;
			case 1 : testo = oggetto != null ? getIndirizzo(oggetto) : "N/A"; break;
			default : testo = "";
		}
		return testo;
	}

	private String getIndirizzo(IndirizzoSimulazione indirizzo) {
		String text = indirizzo.getIndirizzo();
		text += " " + indirizzo.getLocalita();
		String provincia = indirizzo.getProvincia();
		if (provincia != null && !provincia.isEmpty())
			text += " (" + provincia + ") ";
		text += " - " + indirizzo.getCap();
		return text;
	}

	@Override
	public String getTestoTooltip(IndirizzoSimulazione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(IndirizzoSimulazione oggetto, int colonna) {
		return null;
	}

}

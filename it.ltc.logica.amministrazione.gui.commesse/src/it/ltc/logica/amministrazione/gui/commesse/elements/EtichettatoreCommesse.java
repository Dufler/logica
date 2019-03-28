package it.ltc.logica.amministrazione.gui.commesse.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCommesse extends Etichettatore<CommessaCentrale> {

	@Override
	public String getTesto(CommessaCentrale oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = getSede(oggetto); break;
			case 2 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	private String getSede(CommessaCentrale oggetto) {
		Sede sede = ControllerSedi.getInstance().getSede(oggetto.getIdSede());
		String testo = sede != null ? sede.getSede() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CommessaCentrale oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CommessaCentrale oggetto, int colonna) {
		return null;
	}

}

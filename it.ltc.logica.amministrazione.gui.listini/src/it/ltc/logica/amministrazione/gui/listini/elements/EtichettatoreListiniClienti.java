package it.ltc.logica.amministrazione.gui.listini.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreListiniClienti extends Etichettatore<ListinoCommessa> {

	@Override
	public String getTesto(ListinoCommessa oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = getTipo(oggetto); break;
			case 2 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ListinoCommessa oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(ListinoCommessa oggetto, int colonna) {
		return null;
	}

	private String getTipo(ListinoCommessa listino) {
		AmbitoFattura tipo = ControllerAmbitiFatturazione.getInstance().getAmbito(listino.getTipo());
		String testo = (tipo != null) ? tipo.toString() : "";
		return testo;
	}
}

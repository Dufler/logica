package it.ltc.logica.trasporti.gui.listini.elements.simulazione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreListiniSimulazione extends Etichettatore<ListinoSimulazione> {

	@Override
	public String getTesto(ListinoSimulazione oggetto, int colonna) {
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
	public String getTestoTooltip(ListinoSimulazione oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(ListinoSimulazione oggetto, int colonna) {
		return null;
	}

	private String getTipo(ListinoSimulazione listino) {
		AmbitoFattura tipo = ControllerAmbitiFatturazione.getInstance().getAmbito(listino.getTipo());
		String testo = (tipo != null) ? tipo.toString() : "";
		return testo;
	}
}

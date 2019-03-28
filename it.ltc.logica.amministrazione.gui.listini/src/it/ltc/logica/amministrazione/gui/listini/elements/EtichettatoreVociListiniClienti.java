package it.ltc.logica.amministrazione.gui.listini.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreVociListiniClienti extends Etichettatore<VoceDiListino> {

	@Override
	public String getTesto(VoceDiListino oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(oggetto.getIdSottoAmbito()).toString(); break;
			case 2 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(VoceDiListino oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(VoceDiListino oggetto, int colonna) {
		return null;
	}

}

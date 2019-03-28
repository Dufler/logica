package it.ltc.logica.trasporti.gui.listini.elements.corriere.voci;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreVociListiniCorriere extends Etichettatore<VoceDiListinoCorriere> {

	@Override
	public String getTesto(VoceDiListinoCorriere oggetto, int colonna) {
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
	public String getTestoTooltip(VoceDiListinoCorriere oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(VoceDiListinoCorriere oggetto, int colonna) {
		return null;
	}
	
}

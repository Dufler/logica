package it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreVociListiniSimulazione extends Etichettatore<ListinoSimulazioneVoce> {

	@Override
	public String getTesto(ListinoSimulazioneVoce oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = ControllerAmbitiFatturazione.getInstance().getSottoAmbito(oggetto.getIdsottoAmbito()).toString(); break;
			case 2 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(ListinoSimulazioneVoce oggetto, int colonna) {
		String testo = getTesto(oggetto, colonna);
		return testo;
	}

	@Override
	public Image getIcona(ListinoSimulazioneVoce oggetto, int colonna) {
		return null;
	}

}

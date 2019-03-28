package it.ltc.logica.amministrazione.gui.commesse.elements.preferenze;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatorePreferenzeFatturazione extends Etichettatore<PreferenzeFatturazione> {

	@Override
	public String getTesto(PreferenzeFatturazione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getAmbito(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	private String getAmbito(PreferenzeFatturazione oggetto) {
		AmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getAmbito(oggetto.getAmbito());
		String testo = ambito != null ? ambito.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(PreferenzeFatturazione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(PreferenzeFatturazione oggetto, int colonna) {
		return null;
	}

}

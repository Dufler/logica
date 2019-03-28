package it.ltc.logica.cdg.gui.eventi.elements.fasi;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreFasi extends Etichettatore<CdgFase> {

	@Override
	public String getTesto(CdgFase oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgFase oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgFase oggetto, int colonna) {
		return null;
	}

}

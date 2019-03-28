package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCostiRicaviGenerici extends Etichettatore<CdgCostiRicaviGenerici> {

	@Override
	public String getTesto(CdgCostiRicaviGenerici oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
//			case 1 : testo = getSede(oggetto); break;
			case 2 : testo = oggetto.getDriver().toString(); break;
			default : testo = "";
		}
		return testo;
	}

//	private String getSede(CdgCostiRicaviGenerici oggetto) {
//		Sede sede = ControllerSedi.getInstance().getSede(oggetto.getSede() != null ? oggetto.getSede() : -1);
//		String testo = sede != null ? sede.getSede() : "Tutte";
//		return testo;
//	}

	@Override
	public String getTestoTooltip(CdgCostiRicaviGenerici oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCostiRicaviGenerici oggetto, int colonna) {
		return null;
	}

}

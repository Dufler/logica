package it.ltc.logica.cdg.gui.costiricavi.elements.generici.singoli;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCostiRicaviGenericiSingoli extends Etichettatore<CdgCostiRicaviGenericiDateValore> {

	@Override
	public String getTesto(CdgCostiRicaviGenericiDateValore oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = sdf.format(oggetto.getDataEffettiva()); break;
			case 1 : testo = sdf.format(oggetto.getDataInizio()); break;
			case 2 : testo = sdf.format(oggetto.getDataFine()); break;
			case 3 : testo = formatEuro.format(oggetto.getValore()); break;
			case 4 : testo = getSede(oggetto); break;
			case 5 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getSede(CdgCostiRicaviGenericiDateValore oggetto) {
		Sede sede = ControllerSedi.getInstance().getSede(oggetto.getSede() != null ? oggetto.getSede() : -1);
		String testo = sede != null ? sede.getSede() : "Tutte";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgCostiRicaviGenericiDateValore oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCostiRicaviGenericiDateValore oggetto, int colonna) {
		return null;
	}

}

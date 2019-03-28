package it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCostiRicaviGenericiPerFase extends Etichettatore<CdgCostiRicaviGenericiFase> {

	@Override
	public String getTesto(CdgCostiRicaviGenericiFase oggetto, int colonna) {
		String testo;
		switch (colonna) {
		case 0 : testo = getFase(oggetto); break;
		case 1 : testo = formatPercentuali.format(oggetto.getPercentuale() / 100.0); break;
		default : testo = "";
		}
		return testo;
	}

	private String getFase(CdgCostiRicaviGenericiFase oggetto) {
		CdgFase fase = ControllerCdgFasi.getInstance().getFase(oggetto.getFase());
		String testo = fase != null ? fase.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgCostiRicaviGenericiFase oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCostiRicaviGenericiFase oggetto, int colonna) {
		return null;
	}

}

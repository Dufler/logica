package it.ltc.logica.cdg.gui.costiricavi.elements.budgetpercentuale;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreBudgetPercentuale extends Etichettatore<CdgBudgetPercentualiCosto> {

	@Override
	public String getTesto(CdgBudgetPercentualiCosto oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getCostoGenerico(oggetto); break;
			case 1 : testo = formatPercentuali.format(oggetto.getPercentuale() / 100.0); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getCostoGenerico(CdgBudgetPercentualiCosto oggetto) {
		CdgCostiRicaviGenerici costo = ControllerCdgCostiRicaviGenerici.getInstance().getCostoRicavoGenericoDaID(oggetto.getCostoGenerico());
		String testo = costo != null ? costo.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgBudgetPercentualiCosto oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgBudgetPercentualiCosto oggetto, int colonna) {
		return null;
	}

}

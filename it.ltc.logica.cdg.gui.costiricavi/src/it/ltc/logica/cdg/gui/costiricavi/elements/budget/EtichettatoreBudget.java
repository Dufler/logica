package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreBudget extends Etichettatore<CdgBudget> {

	@Override
	public String getTesto(CdgBudget oggetto, int colonna) {
		String testo;
		switch (colonna) {
		case 0 : testo = getCommessa(oggetto); break;
		case 1 : testo = sdf.format(oggetto.getDataInizio()); break;
		case 2 : testo = sdf.format(oggetto.getDataFine()); break;
		default : testo = "";
		}
		return testo;
	}
	
	private String getCommessa(CdgBudget oggetto) {
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(oggetto.getCommessa());
		String testo = commessa != null ? commessa.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgBudget oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgBudget oggetto, int colonna) {
		return null;
	}

}

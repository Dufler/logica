package it.ltc.logica.cdg.gui.costiricavi.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatorePezziCostiRicavi extends Etichettatore<CdgPezzo> {
	
	private final ControllerCommesse controller;
	
	protected EtichettatorePezziCostiRicavi() {
		controller = ControllerCommesse.getInstance();
	}

	@Override
	public String getTesto(CdgPezzo oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getCommessa(oggetto); break;
			case 1 : testo = oggetto.getCategoriaMerceologica(); break;
			case 2 : testo = formatEuro.format(oggetto.getCosto()); break;
			case 3 : testo = formatEuro.format(oggetto.getRicavo()); break;
			default : testo = "";
		}
		return testo;
	}

	private String getCommessa(CdgPezzo oggetto) {
		Commessa commessa = controller.getCommessa(oggetto.getCommessa());
		String testo = commessa != null ? commessa.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgPezzo oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgPezzo oggetto, int colonna) {
		return null;
	}

}

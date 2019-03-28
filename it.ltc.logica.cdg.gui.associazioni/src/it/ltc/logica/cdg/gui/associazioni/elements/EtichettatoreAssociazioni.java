package it.ltc.logica.cdg.gui.associazioni.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreAssociazioni extends Etichettatore<CdgCostoAssociazione> {
	
	private final ControllerSedi controller;
	
	protected EtichettatoreAssociazioni() {
		super(DEFAULT_DATE_FORMAT, HIGH_PRECISION_EURO_FORMAT, DEFAULT_DECIMAL_FORMAT, DEFAULT_PERCENTAGE_FORMAT);
		controller = ControllerSedi.getInstance();
	}

	@Override
	public String getTesto(CdgCostoAssociazione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getNome(); break;
			case 1 : testo = getSede(oggetto); break;
			case 2 : testo = formatEuro.format(oggetto.getCosto()); break;
			default : testo = "";
		}
		return testo;
	}

	private String getSede(CdgCostoAssociazione oggetto) {
		Sede sede = controller.getSede(oggetto.getSede());
		String testo = sede != null ? sede.getSede() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgCostoAssociazione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCostoAssociazione oggetto, int colonna) {
		return null;
	}

}

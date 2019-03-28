package it.ltc.logica.trasporti.gui.elements.scaglioni;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Tabella;

public class ModificatoreScaglioni extends ModificatoreValoriCelle<Scaglione> {

	public ModificatoreScaglioni(Tabella<Scaglione> tabella) {
		super(tabella);
	}

	@Override
	protected Object getValore(Scaglione elemento, int indiceColonna) {
		Object valore;
		switch (indiceColonna) {
			case 1 : valore = elemento.getInizio(); break;
			case 2 : valore = elemento.getFine(); break;
			case 3 : valore = elemento.getValore(); break;
			default : valore = null;
		}
		return valore;
	}

	@Override
	protected void setValore(Scaglione elemento, Object valore, int indiceColonna) {
		Double value = parseValue(elemento, valore);
		//Se il parse del valore va a buon fine lo imposto.
		if (value != null) 
		switch (indiceColonna) {
			case 1 : if (elemento.getFine() > value) elemento.setInizio(value); break;
			case 2 : if (elemento.getInizio() < value) elemento.setFine(value); break;
			case 3 : if (value > 0) elemento.setValore(value); break;
		}
	}
	
	private Double parseValue(Scaglione scaglione, Object v) {
		String value = v != null ? v.toString() : "";
		Double valore;
		try {
			int index = value.indexOf(" \u20AC");
			if (index != -1)
				value = value.substring(0, index);
			value = value.replace(',', '.');
			valore = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			valore = null;
		}
		return valore;
	}

}

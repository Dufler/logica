package it.ltc.logica.ufficio.gui.elements.elementocassa;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreElementiCassa extends Etichettatore<ElementoCassa> {

	@Override
	public String getTesto(ElementoCassa oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getSKU(oggetto); break;
			case 1 : testo = getModello(oggetto); break;
			case 2 : testo = getTaglia(oggetto); break;
			case 3 : testo = Integer.toString(oggetto.getQuantita()); break;
			default : testo = "";
		}
		return testo;
	}

	private String getTaglia(ElementoCassa oggetto) {
		String testo = oggetto != null && oggetto.getProdotto() != null ? oggetto.getProdotto().getTaglia() : "N/A";
		return testo;
	}

	private String getModello(ElementoCassa oggetto) {
		String testo = oggetto != null && oggetto.getProdotto() != null ? oggetto.getProdotto().getCodiceModello() : "N/A";
		return testo;
	}

	private String getSKU(ElementoCassa oggetto) {
		String testo = oggetto != null && oggetto.getProdotto() != null ? oggetto.getProdotto().getChiaveCliente() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(ElementoCassa oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ElementoCassa oggetto, int colonna) {
		return null;
	}

}

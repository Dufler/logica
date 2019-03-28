package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.prodotti.ProdottoConQuantita;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreProdottiConQuantita extends Etichettatore<ProdottoConQuantita> {

	@Override
	public String getTesto(ProdottoConQuantita oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getArticolo(oggetto); break;
			case 1 : testo = getTaglia(oggetto); break;
			case 2 : testo = getQuantita(oggetto); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getQuantita(ProdottoConQuantita oggetto) {
		String testo = oggetto.getQuantita() > 1 ? Integer.toString(oggetto.getQuantita()) : "-";
		return testo;
	}

	private String getTaglia(ProdottoConQuantita oggetto) {
		String testo = oggetto.getProdotto().getTaglia() != null ? oggetto.getProdotto().getTaglia() : "-";
		return testo;
	}

	private String getArticolo(ProdottoConQuantita oggetto) {
		String testo = oggetto.getProdotto().getChiaveCliente() != null ? oggetto.getProdotto().getChiaveCliente() : "-";
		return testo;
	}

	@Override
	public String getTestoTooltip(ProdottoConQuantita oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(ProdottoConQuantita oggetto, int colonna) {
		return null;
	}

}

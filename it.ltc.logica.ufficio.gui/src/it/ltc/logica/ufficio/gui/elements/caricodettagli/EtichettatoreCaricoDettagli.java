package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCaricoDettagli extends Etichettatore<CaricoDettaglio> {

	private final ControllerProdotti controller;
	
	public EtichettatoreCaricoDettagli(ControllerProdotti controller) {
		this.controller = controller;
	}
	
	@Override
	public String getTesto(CaricoDettaglio oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = Integer.toString(oggetto.getRiga()); break;
			case 2 : testo = getProdotto(oggetto.getArticolo()); break;
			case 3 : testo = getDescrizioneProdotto(oggetto.getArticolo()); break;
			case 4 : testo = getColoreProdotto(oggetto.getArticolo()); break;
			case 5 : testo = getTagliaProdotto(oggetto.getArticolo()); break;
			case 6 : testo = oggetto.getMagazzino(); break;
			case 7 : testo = Integer.toString(oggetto.getQuantitaDichiarata()); break;
			case 8 : testo = Integer.toString(oggetto.getQuantitaRiscontrata()); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getDescrizioneProdotto(int articolo) {
		Prodotto prodotto = controller.trovaDaID(articolo);
		String testo = prodotto != null ? prodotto.getDescrizione() : "N/A";
		return testo;
	}

	private String getQuantita(CaricoDettaglio oggetto) {
		String testo = oggetto.getQuantitaRiscontrata() + "/" + oggetto.getQuantitaDichiarata();
		return testo;
	}

	private String getProdotto(int articolo) {
		Prodotto prodotto = controller.trovaDaID(articolo);
		String testo = prodotto != null ? prodotto.getChiaveCliente() : "N/A";
		return testo;
	}
	
	private String getTagliaProdotto(int articolo) {
		Prodotto prodotto = controller.trovaDaID(articolo);
		String testo = prodotto != null ? prodotto.getTaglia() : "N/A";
		return testo;
	}
	
	private String getColoreProdotto(int articolo) {
		Prodotto prodotto = controller.trovaDaID(articolo);
		String testo = prodotto != null ? prodotto.getColore() : "N/A";
		return testo;
	}
	
	private String getProdottoTooltip(int articolo) {
		Prodotto prodotto = controller.trovaDaID(articolo);
		String testo;
		if (prodotto != null) {
			testo = "SKU: " + prodotto.getChiaveCliente() + "\r\n";
			testo += "Modello: " + prodotto.getCodiceModello() + "\r\n";
			testo += prodotto.getDescrizione() + "\r\n";
		} else {
			testo = "N/A";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(CaricoDettaglio oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = Integer.toString(oggetto.getRiga()); break;
			case 2 : testo = getProdottoTooltip(oggetto.getArticolo()); break;
			case 3 : testo = oggetto.getMagazzino(); break;
			case 4 : testo = getQuantita(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(CaricoDettaglio oggetto, int colonna) {
		Image image = null;
		if (colonna == 0 && oggetto.getQuantitaRiscontrata() == oggetto.getQuantitaDichiarata()) {
			image = Immagine.SPUNTAVERDE_16X16.getImage();
		}
		return image;
	}

}

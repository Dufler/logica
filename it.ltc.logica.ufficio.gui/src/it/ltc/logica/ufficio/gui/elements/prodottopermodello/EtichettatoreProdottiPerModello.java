package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.prodotti.ProdottiPerModello;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreProdottiPerModello extends Etichettatore<ProdottiPerModello> {

	private final ControllerCategorieMerceologiche controllerCategorie;
	
	public EtichettatoreProdottiPerModello() {
		controllerCategorie = ControllerCategorieMerceologiche.getInstance();
	}
	
	@Override
	public String getTesto(ProdottiPerModello oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getBrand(oggetto); break;
			case 1 : testo = getCategoriaMerceologica(oggetto); break;
			case 2 : testo = getModello(oggetto); break;
			case 3 : testo = getTaglie(oggetto); break;
			case 4 : testo = getTotale(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	private String getTotale(ProdottiPerModello oggetto) {
		int totale = oggetto.getTotale();
		String testo = totale > 0 ? Integer.toString(totale) : "";
		return testo;
	}

	private String getTaglie(ProdottiPerModello oggetto) {
		return oggetto.getRappresentazioneTaglie();
	}
	
	private String getDescrizioneTaglie(ProdottiPerModello oggetto) {
		return oggetto.getRappresentazioneTaglieConQuantita();
	}

	private String getModello(ProdottiPerModello oggetto) {
		String testo = oggetto.getCodiceModello() != null ? oggetto.getCodiceModello() : "-";
		return testo;
	}

	private String getCategoriaMerceologica(ProdottiPerModello oggetto) {
		CategoriaMerceologica categoria = controllerCategorie.getCategoria(oggetto.getCategoriaMerceologica(), 0); //FIXME
		String testo = categoria != null ? categoria.getNome() : "-";
		return testo;
	}

	private String getBrand(ProdottiPerModello oggetto) {
		String testo = oggetto.getBrand() != null ? oggetto.getBrand() : "-";
		return testo;
	}

	@Override
	public String getTestoTooltip(ProdottiPerModello oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getBrand(oggetto); break;
			case 1 : testo = getCategoriaMerceologica(oggetto); break;
			case 2 : testo = getModello(oggetto); break;
			case 3 : case 4: testo = getDescrizioneTaglie(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public Image getIcona(ProdottiPerModello oggetto, int colonna) {
		return null;
	}

}

package it.ltc.logica.ufficio.gui.uscite.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreOrdineTestata extends Etichettatore<OrdineTestata> {
	
	@Override
	public String getTesto(OrdineTestata oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = getData(oggetto); break;
			case 2 : testo = getRiferimento(oggetto); break;
			case 3 : testo = getDestinatario(oggetto); break;
			case 4 : testo = getPezziOrdinati(oggetto); break;
			case 5 : testo = getPezziAssegnati(oggetto); break;
			case 6 : testo = getPezziImballati(oggetto); break;
			case 7 : testo = getTipo(oggetto); break;
			case 8 : testo = getStato(oggetto); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getStato(OrdineTestata oggetto) {
		String testo = oggetto.getStato() != null ? oggetto.getStato().toString() : "-";
		return testo;
	}

	private String getTipo(OrdineTestata oggetto) {
		String testo = oggetto.getTipo() != null ? oggetto.getTipo() : "-";
		return testo;
	}

	private String getPezziOrdinati(OrdineTestata oggetto) {
		String testo = Integer.toString(oggetto.getQuantitaOrdinataTotale());
		return testo;
	}
	
	private String getPezziAssegnati(OrdineTestata oggetto) {
		String testo = Integer.toString(oggetto.getQuantitaAssegnataTotale());
		return testo;
	}
	
	private String getPezziImballati(OrdineTestata oggetto) {
		String testo = Integer.toString(oggetto.getQuantitaImballataTotale());
		return testo;
	}

	private String getRiferimento(OrdineTestata oggetto) {
		String testo = oggetto.getRiferimento() != null ? oggetto.getRiferimento() : "-";
		return testo;
	}

	private String getDestinatario(OrdineTestata oggetto) {
		String testo = oggetto.getRagioneSocialeDestinatario() != null ? oggetto.getRagioneSocialeDestinatario() : "-";
		return testo;
	}

	private String getData(OrdineTestata oggetto) {
		String testo = oggetto.getDataOrdine() != null? sdf.format(oggetto.getDataOrdine()) : "-";
		return testo;
	}

	@Override
	public String getTestoTooltip(OrdineTestata oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(OrdineTestata oggetto, int colonna) {
		Image image = colonna == 1 && oggetto.getPriorita() > 1 ? Immagine.RED_MARK_12X20.getImage() : null;
		return image;
	}

}

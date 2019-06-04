package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreDatiSpedizione extends Etichettatore<DatiSpedizione> {

	@Override
	public String getTesto(DatiSpedizione oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getRiferimento(); break;
			case 1 : testo = getOrdini(oggetto); break;
			case 2 : testo = Integer.toString(oggetto.getColli()); break;
			case 3 : testo = formatDecimali.format(oggetto.getPeso()) + " Kg"; break;
			case 4 : testo = formatDecimali.format(oggetto.getVolume()) + " Mc"; break;
			case 5 : testo = oggetto.getCorriere(); break;
			case 6 : testo = oggetto.getServizioCorriere(); break;
			case 7 : testo = getContrassegno(oggetto); break;
			case 8 : testo = oggetto.getStato().toString(); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getContrassegno(DatiSpedizione oggetto) {
		StringBuilder testo = new StringBuilder();
		if (oggetto.getValoreContrassegno() != null && oggetto.getValoreContrassegno() > 0) {
			testo.append(formatEuro.format(oggetto.getValoreContrassegno()));
			testo.append(" (");
			testo.append(oggetto.getTipoContrassegno());
			testo.append(")");
		}
		return testo.toString();
	}

	private String getOrdini(DatiSpedizione oggetto) {
		StringBuilder testo = new StringBuilder();
		if (oggetto.getOrdini() == null || oggetto.getOrdini().isEmpty()) {
			testo.append("N/A");
		} else {
			for (OrdineTestata ordine : oggetto.getOrdini()) {
				testo.append(ordine.getNumeroLista());
				testo.append("\r\n");
			}
			//Elimino l'ultimo a capo.
			testo.deleteCharAt(testo.length() -1);
			testo.deleteCharAt(testo.length() -1);
		}
		return testo.toString();
	}

	@Override
	public String getTestoTooltip(DatiSpedizione oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(DatiSpedizione oggetto, int colonna) {
		return null;
	}

}

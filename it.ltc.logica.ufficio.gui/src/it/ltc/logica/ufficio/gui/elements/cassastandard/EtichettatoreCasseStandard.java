package it.ltc.logica.ufficio.gui.elements.cassastandard;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.database.model.prodotto.ElementoCassaStandard;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCasseStandard extends Etichettatore<CassaStandard> {

	@Override
	public String getTesto(CassaStandard oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getCodiceCassa(); break;
			case 1 : testo = Integer.toString(oggetto.getTotalePezzi()); break;
			case 2 : testo = getComposizione(oggetto); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getComposizione(CassaStandard oggetto) {
		StringBuilder sb = new StringBuilder();
		for (ElementoCassaStandard elemento : oggetto.getElementi()) {
			sb.append(elemento.getTaglia());
			sb.append(" X ");
			sb.append(elemento.getQuantita());
			sb.append(", ");
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : sb.toString();
	}

	@Override
	public String getTestoTooltip(CassaStandard oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CassaStandard oggetto, int colonna) {
		return null;
	}

}

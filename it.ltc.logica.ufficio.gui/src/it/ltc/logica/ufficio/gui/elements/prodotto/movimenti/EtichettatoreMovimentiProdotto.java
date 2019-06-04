package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.prodotto.MovimentoProdotto;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreMovimentiProdotto extends Etichettatore<MovimentoProdotto> {

	@Override
	public String getTesto(MovimentoProdotto oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getCausale().getDescrizione(); break;
			case 2 : testo = oggetto.getMagazzino(); break;
			case 3 : testo = Integer.toString(oggetto.getQuantita()); break;
			case 4 : testo = oggetto.getDocumentoRiferimento(); break;
			case 5 : testo = oggetto.getDataMovimento() != null ? sdf.format(oggetto.getDataMovimento()) : "-"; break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(MovimentoProdotto oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(MovimentoProdotto oggetto, int colonna) {
		return null;
	}

}

package it.ltc.logica.ufficio.gui.elements.prodotto.saldo;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.database.model.centrale.SaldoProdotto;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreSaldoProdotto extends Etichettatore<SaldoProdotto> {

	@Override
	public String getTesto(SaldoProdotto oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 1 : testo = oggetto.getMagazzino(); break;
			case 2 : testo = Integer.toString(oggetto.getEsistente()); break;
			case 3 : testo = Integer.toString(oggetto.getDisponibile()); break;
			case 4 : testo = Integer.toString(oggetto.getImpegnato()); break;
			case 5 : testo = Integer.toString(oggetto.getTotaleEntrato()); break;
			case 6 : testo = Integer.toString(oggetto.getTotaleUscito()); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(SaldoProdotto oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(SaldoProdotto oggetto, int colonna) {
		return null;
	}

}

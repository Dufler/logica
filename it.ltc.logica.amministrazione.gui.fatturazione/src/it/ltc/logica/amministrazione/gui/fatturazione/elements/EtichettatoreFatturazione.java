package it.ltc.logica.amministrazione.gui.fatturazione.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.amministrazione.calcolo.algoritmi.LogisticaModel;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreFatturazione extends Etichettatore<LogisticaModel> {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	@Override
	public String getTesto(LogisticaModel oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = getRiferimento(oggetto); break;
			case 1 : testo = getData(oggetto); break;
			case 2 : testo = getTipo(oggetto); break;
			case 3 : testo = getTotale(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	private String getTotale(LogisticaModel oggetto) {
		double totale = oggetto.getCostoTotale(Scopo.RICAVO);
		String testo = Decorator.getEuroValue(totale);
		return testo;
	}
	
	private String getTipo(LogisticaModel oggetto) {
		String testo = oggetto.getTipo() != null ? oggetto.getTipo() : "-";
		return testo;
	}

	private String getData(LogisticaModel oggetto) {
		Date data = oggetto.getData();
		String testo = data != null ? sdf.format(data) : "-";
		return testo;
	}

	private String getRiferimento(LogisticaModel oggetto) {
		String testo = oggetto.getRiferimento() != null ? oggetto.getRiferimento() : "-";
		return testo;
	}

	@Override
	public String getTestoTooltip(LogisticaModel oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(LogisticaModel oggetto, int colonna) {
		return null;
	}

}

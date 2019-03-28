package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCostiRicaviCommesse extends Etichettatore<CdgCostoRicavoCommessa> {

	@Override
	public String getTesto(CdgCostoRicavoCommessa oggetto, int colonna) {
		String testo;
		switch (colonna) {
		case 0 : testo = getCommessa(oggetto); break;
		case 1 : testo = oggetto.getTipo().toString(); break;
		case 2 : testo = formatEuro.format(oggetto.getValore()); break;
		case 3 : testo = sdf.format(oggetto.getDataEmissione()); break;
		case 4 : testo = oggetto.getDescrizione(); break;
		default : testo = "";
		}
		return testo;
	}

	private String getCommessa(CdgCostoRicavoCommessa oggetto) {
		Commessa commessa = ControllerCommesse.getInstance().getCommessa(oggetto.getCommessa());
		String testo = commessa != null ? commessa.getNome() : "N/A";
		return testo;
	}

	@Override
	public String getTestoTooltip(CdgCostoRicavoCommessa oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(CdgCostoRicavoCommessa oggetto, int colonna) {
		return null;
	}

}

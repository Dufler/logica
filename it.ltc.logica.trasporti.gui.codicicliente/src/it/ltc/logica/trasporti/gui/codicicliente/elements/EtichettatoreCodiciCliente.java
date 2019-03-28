package it.ltc.logica.trasporti.gui.codicicliente.elements;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.elements.Etichettatore;

public class EtichettatoreCodiciCliente extends Etichettatore<CodiceClienteCorriere> {
	
	public EtichettatoreCodiciCliente() {}

	@Override
	public String getTesto(CodiceClienteCorriere oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 0 : testo = oggetto.getCodiceCliente();  break;
			case 1 : testo = getDescrizioneStato(oggetto); break;
			case 2 : testo = ControllerCorrieri.getInstance().getCorriere(oggetto.getCorriere()).getNome(); break;
			case 4 : testo = ControllerCommesse.getInstance().getCommessa(oggetto.getCommessa()).getNome(); break;
			case 6 : testo = oggetto.getDescrizione(); break;
			default : testo = "";
		}
		return testo;
	}
	
	private String getDescrizioneStato(CodiceClienteCorriere oggetto) {
		CodiceClienteCorriere.Stato stato = CodiceClienteCorriere.Stato.valueOf(oggetto.getStato());
		return stato.toString();
	}
	

	@Override
	public String getTestoTooltip(CodiceClienteCorriere oggetto, int colonna) {
		String tooltip = getTesto(oggetto, colonna);
		return tooltip;
	}

	@Override
	public Image getIcona(CodiceClienteCorriere oggetto, int colonna) {
		return null;
	}

}

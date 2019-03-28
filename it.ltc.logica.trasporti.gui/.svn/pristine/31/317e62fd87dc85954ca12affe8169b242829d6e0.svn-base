package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import org.eclipse.swt.graphics.Image;

import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.gui.decoration.Decorator;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class EtichettatoreFatturazioneModel extends Etichettatore<SpedizioneModel> {

	private final VoceDiListino voce;
	
	public EtichettatoreFatturazioneModel() {
		this.voce = null;
	}
	
	public EtichettatoreFatturazioneModel(VoceDiListino voce) {
		this.voce = voce;
	}

	@Override
	public String getTesto(SpedizioneModel oggetto, int colonna) {
		String testo;
		switch (colonna) {
			case 4 : testo = getTotale(oggetto); break;
			case 5 : testo = getCostoVoce(oggetto); break;
			default : testo = "";
		}
		return testo;
	}

	@Override
	public String getTestoTooltip(SpedizioneModel oggetto, int colonna) {
		return getTesto(oggetto, colonna);
	}

	@Override
	public Image getIcona(SpedizioneModel oggetto, int colonna) {
		return null;
	}
	
	private String getTotale(SpedizioneModel oggetto) {
		double totale = oggetto.getCostoTotale(Scopo.RICAVO);
		return Decorator.getEuroValue(totale);
	}
	
	private String getCostoVoce(SpedizioneModel oggetto) {
		double costo = 0;
		if (voce != null)
		for (VoceCalcolata vc : oggetto.getPreventivoRicavo().getVoci()) {
			if (vc.getIdVoce().equals(voce.getId())) {
				costo = vc.getCosto();
				break;
			}
		}
		String testo = costo > 0 ? Decorator.getEuroValue(costo) : "";
		return testo;
	}

}

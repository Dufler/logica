 
package it.ltc.logica.cdg.gui.costiricavi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneCostiRicaviPart extends FunzionePart {
	
	private static final String label = "Costi e Ricavi";
	private static final String tooltip = "Qui puoi gestire i costi e i ricavi legati alle gestione di un pezzo.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.eventi.part.costiricavi";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.eventi.part.funzionecostiricavi";
	
	@Inject
	public FunzioneCostiRicaviPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_COSTI_RICAVI.getID());
		return visible;
	}
}
 
package it.ltc.logica.trasporti.gui.preventivi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzionePreventiviPart extends FunzionePart {
	
	private static final String label = "Preventivi";
	private static final String tooltip = "Qui puoi generare preventivi di costo e di ricavo sulle spedizioni.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.preventivi.part.preventivi";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.preventivi.part.funzionepreventivi";
	
	@Inject
	public FunzionePreventiviPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_PREVENTIVI.getID());
		return visible;
	}
	
}

package it.ltc.logica.trasporti.gui.spedizioni.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneSpedizioniPart extends FunzionePart {
	
	private static final String label = "Spedizioni";
	private static final String tooltip = "Qui puoi visualizzare le spedizioni.";
	
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.spedizioni.part.funzionespedizioni";
	public static final String partID = "it.ltc.logica.trasporti.gui.spedizioni.part.spedizioni";

	@Inject
	public FunzioneSpedizioniPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_SPEDIZIONI.getID());
		return visible;
	}

}
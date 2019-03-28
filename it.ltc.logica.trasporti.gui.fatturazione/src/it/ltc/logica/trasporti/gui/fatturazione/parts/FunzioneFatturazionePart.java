 
package it.ltc.logica.trasporti.gui.fatturazione.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneFatturazionePart extends FunzionePart {
	
	private static final String label = "Fatturazione";
	private static final String tooltip = "Qui puoi generare fatture relative ai trasporti.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.fatturazione.part.fatturazione";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.fatturazione.part.funzionefatturazione";
	
	@Inject
	public FunzioneFatturazionePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_FATTURAZIONE.getID());
		return visible;
	}
	
}
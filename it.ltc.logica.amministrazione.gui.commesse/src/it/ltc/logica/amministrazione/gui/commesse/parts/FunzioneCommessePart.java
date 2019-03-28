 
package it.ltc.logica.amministrazione.gui.commesse.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneCommessePart extends FunzionePart {
	
	private static final String label = "Commesse";
	private static final String tooltip = "Qui puoi gestire le commesse.";
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.commesse.part.commesse";
	public static final String funzionePartID = "it.ltc.logica.amministrazione.gui.commesse.part.funzionecommesse";
	
	@Inject
	public FunzioneCommessePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}	
	
}
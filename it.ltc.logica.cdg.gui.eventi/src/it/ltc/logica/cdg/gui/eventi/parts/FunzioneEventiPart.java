 
package it.ltc.logica.cdg.gui.eventi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneEventiPart extends FunzionePart {
	
	private static final String label = "Eventi";
	private static final String tooltip = "Qui puoi gestire i tipi di evento.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.eventi.part.eventi";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.eventi.part.funzioneeventi";
	
	@Inject
	public FunzioneEventiPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
}
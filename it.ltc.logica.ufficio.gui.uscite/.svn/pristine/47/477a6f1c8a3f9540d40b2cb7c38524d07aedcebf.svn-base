 
package it.ltc.logica.ufficio.gui.uscite.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneUscitePart extends FunzionePart {
	
	private static final String label = "Ordini";
	private static final String tooltip = "Qui puoi gestire gli ordini dei clienti.";
	
	public static final String partID = "it.ltc.logica.ufficio.gui.uscite.part.uscite";
	public static final String funzionePartID = "it.ltc.logica.ufficio.gui.uscite.part.funzioneuscite";
	
	@Inject
	public FunzioneUscitePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
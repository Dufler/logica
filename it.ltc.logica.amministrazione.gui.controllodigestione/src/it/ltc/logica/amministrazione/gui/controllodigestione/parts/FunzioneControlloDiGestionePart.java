 
package it.ltc.logica.amministrazione.gui.controllodigestione.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneControlloDiGestionePart extends FunzionePart {
	
	private static final String label = "Controllo di gestione";
	private static final String tooltip = "Qui puoi effettuare il controllo di gestione.";
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.controllodigestione.part.controllo";
	public static final String funzionePartID = "it.ltc.logica.amministrazione.gui.controllodigestione.part.funzionecontrollo";
	
	@Inject
	public FunzioneControlloDiGestionePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = false; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
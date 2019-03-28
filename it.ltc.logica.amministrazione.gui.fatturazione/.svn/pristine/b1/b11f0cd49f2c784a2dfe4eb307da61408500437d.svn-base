 
package it.ltc.logica.amministrazione.gui.fatturazione.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneFatturazionePart extends FunzionePart {
	
	private static final String label = "Fatturazione";
	private static final String tooltip = "Qui puoi fatturare.";
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.fatturazione.part.fatturazione";
	public static final String funzionePartID = "it.ltc.logica.amministrazione.gui.fatturazione.part.funzionefatturazione";
	
	@Inject
	public FunzioneFatturazionePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
 
package it.ltc.logica.ufficio.gui.ingressi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneIngressiPart extends FunzionePart {
	
	private static final String label = "Ingressi";
	private static final String tooltip = "Qui puoi gestire la merce in ingresso.";
	
	public static final String partID = "it.ltc.logica.ufficio.gui.ingressi.part.ingressi";
	public static final String funzionePartID = "it.ltc.logica.ufficio.ordini.part.funzioneingressi";
	
	@Inject
	public FunzioneIngressiPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
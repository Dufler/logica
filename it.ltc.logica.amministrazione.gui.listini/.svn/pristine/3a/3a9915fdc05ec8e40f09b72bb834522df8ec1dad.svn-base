 
package it.ltc.logica.amministrazione.gui.listini.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneListiniPart extends FunzionePart {
	
	private static final String label = "Listini";
	private static final String tooltip = "Qui puoi gestire i listini dei clienti.";
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.listini.part.listini";
	public static final String funzionePartID = "it.ltc.logica.amministrazione.gui.listini.part.funzionelistini";
	
	@Inject
	public FunzioneListiniPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
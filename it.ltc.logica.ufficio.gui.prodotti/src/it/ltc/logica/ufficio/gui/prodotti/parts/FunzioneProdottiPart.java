
package it.ltc.logica.ufficio.gui.prodotti.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;

public class FunzioneProdottiPart extends FunzionePart {
	
	private static final String label = "Prodotti";
	private static final String tooltip = "Qui puoi gestire tutte le informazioni sui prodotti.";
	
	public static final String partID = "it.ltc.logica.ufficio.gui.prodotti.part.prodotti";
	public static final String funzionePartID = "it.ltc.logica.ufficio.gui.prodotti.part.funzioneprodotti";
	
	@Inject
	public FunzioneProdottiPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = true; //ControllerUtente.getInstance().isAllowed(Permessi.CODICI_CLIENTE.getID());
		return visible;
	}
	
}
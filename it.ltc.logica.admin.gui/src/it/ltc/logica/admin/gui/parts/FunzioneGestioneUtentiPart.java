 
package it.ltc.logica.admin.gui.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneGestioneUtentiPart extends FunzionePart {
	
	private static final String label = "Gestione Utenti";
	private static final String tooltip = "Qui puoi gestire le propriet\u00E0 degli utenti.";
	
	public static final String funzionePartID = "it.ltc.logica.admin.gui.part.funzionegestioneutenti";
	public static final String partID = "it.ltc.logica.admin.gui.part.gestioneutenti";
	
	@Inject
	public FunzioneGestioneUtentiPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visibile = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.ADMIN.getID());
		return visibile;
	}
	
}
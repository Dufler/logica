 
package it.ltc.logica.admin.gui.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzionePermessi extends FunzionePart {
	
	private static final String label = "Gestione Permessi";
	private static final String tooltip = "Qui puoi gestire i permessi dell'applicazione.";
	
	public static final String funzionePartID = "it.ltc.logica.admin.gui.part.funzionepermessi";
	public static final String partID = "it.ltc.logica.admin.gui.part.permessi";
	
	@Inject
	public FunzionePermessi() {
		super(label, tooltip, funzionePartID, partID);
	}
	
	@Override
	public boolean isVisible() {
		boolean visibile = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.ADMIN.getID());
		return visibile;
	}
	
}
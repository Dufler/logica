 
package it.ltc.logica.admin.gui.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneNuovoUtente extends FunzionePart {
	
	private static final String label = "Nuovo Utente";
	private static final String tooltip = "Qui puoi aggiungere un nuovo utente.";
	
	public static final String funzionePartID = "it.ltc.logica.admin.gui.part.funzionenuovoutente";
	public static final String partID = "it.ltc.logica.admin.gui.part.nuovoutente";
	
	@Inject
	public FunzioneNuovoUtente() {
		super(label, tooltip, funzionePartID, partID);
	}
	
	@Override
	public boolean isVisible() {
		boolean visibile = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.ADMIN.getID());
		return visibile;
	}
	
}
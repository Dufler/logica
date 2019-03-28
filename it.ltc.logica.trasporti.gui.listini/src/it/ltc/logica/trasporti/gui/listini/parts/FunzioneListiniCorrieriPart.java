 
package it.ltc.logica.trasporti.gui.listini.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneListiniCorrieriPart extends FunzionePart {
	
	private static final String label = "Listini Corrieri";
	private static final String tooltip = "Qui puoi gestire i listini dei corrieri.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.listini.part.listinicorrieri";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.listini.part.funzionelistinicorrieri";
	
	@Inject
	public FunzioneListiniCorrieriPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_LISTINI_CORRIERI.getID());
		return visible;
	}
	
}
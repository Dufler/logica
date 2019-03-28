 
package it.ltc.logica.trasporti.gui.indirizzi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneIndirizziPart extends FunzionePart {
	
	private static final String label = "CAP e Indirizzi";
	private static final String tooltip = "Qui puoi gestire i CAP e gli indirizzi.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.indirizzi.part.indirizzi";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.indirizzi.part.funzioneindirizzi";
	
	@Inject
	public FunzioneIndirizziPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean capVisible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_CAP.getID());
		boolean indirizziVisibile = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_INDIRIZZI.getID());
		return capVisible || indirizziVisibile;
	}
	
}
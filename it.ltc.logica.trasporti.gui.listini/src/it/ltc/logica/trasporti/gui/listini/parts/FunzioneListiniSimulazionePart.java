 
package it.ltc.logica.trasporti.gui.listini.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneListiniSimulazionePart extends FunzionePart {
	
	private static final String label = "Listini di Simulazione";
	private static final String tooltip = "Qui puoi gestire i tuoi listini di simulazione.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.listini.part.listinisimulazione";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.listini.part.funzionelistinisimulazione";
	
	@Inject
	public FunzioneListiniSimulazionePart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID());
		return visible;
	}
	
}
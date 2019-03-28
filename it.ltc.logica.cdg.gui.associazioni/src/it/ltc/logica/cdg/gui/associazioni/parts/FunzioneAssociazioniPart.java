 
package it.ltc.logica.cdg.gui.associazioni.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneAssociazioniPart extends FunzionePart {
	
	private static final String label = "Costo del personale";
	private static final String tooltip = "Qui puoi gestire il costo del personale.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.associazioni.part.associazioni";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.associazioni.part.funzioneassociazioni";
	
	@Inject
	public FunzioneAssociazioniPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_COSTO_PERSONALE.getID());
		return visible;
	}
}
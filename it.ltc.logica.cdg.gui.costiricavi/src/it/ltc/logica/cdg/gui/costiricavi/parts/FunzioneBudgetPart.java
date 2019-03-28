
package it.ltc.logica.cdg.gui.costiricavi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneBudgetPart extends FunzionePart {
	
	private static final String label = "Budget";
	private static final String tooltip = "Qui puoi gestire i budget.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.eventi.part.budget";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.eventi.part.funzionebudget";
	
	@Inject
	public FunzioneBudgetPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_COSTI_RICAVI.getID());
		return visible;
	}

}

package it.ltc.logica.cdg.gui.analisi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneAnalisiBudgetPart extends FunzionePart {
	
	private static final String label = "Analisi Budget";
	private static final String tooltip = "Qui puoi analizzare i budget ed effettuare il controllo di Gestione.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.analisi.part.analisibudget";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.analisi.part.funzioneanalisibudget";
	
	@Inject
	public FunzioneAnalisiBudgetPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_ANALISI.getID());
		return visible;
	}

}
 
package it.ltc.logica.trasporti.gui.report.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneReportPart extends FunzionePart {
	
	private static final String label = "Report";
	private static final String tooltip = "Qui puoi generare report sui trasporti.";
	
	public static final String partID = "it.ltc.logica.trasporti.gui.report.part.report";
	public static final String funzionePartID = "it.ltc.logica.trasporti.gui.report.part.funzionereport";
	
	@Inject
	public FunzioneReportPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_REPORT.getID());
		return visible;
	}
	
}
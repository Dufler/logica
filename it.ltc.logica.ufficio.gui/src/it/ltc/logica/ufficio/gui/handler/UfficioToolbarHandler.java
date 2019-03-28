 
package it.ltc.logica.ufficio.gui.handler;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.utilities.variabili.Permessi;

public class UfficioToolbarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID = "it.ltc.logica.ufficio.gui.perspective.ufficio";

	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO.getID();
	}
		
}
 
package it.ltc.logica.magazzino.gui.handlers;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.utilities.variabili.Permessi;

public class MagazzinoToolbarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID = "it.ltc.logica.magazzino.gui.perspective.magazzino";

	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO.getID();
	}
		
}
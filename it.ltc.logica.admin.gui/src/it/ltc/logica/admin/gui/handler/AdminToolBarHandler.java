 
package it.ltc.logica.admin.gui.handler;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.utilities.variabili.Permessi;

public class AdminToolBarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID_ADMIN = "it.ltc.logica.admin.gui.perspective.admin";
	
	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID_ADMIN;
	}
	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN.getID();
	}
		
}
 
package it.ltc.logica.trasporti.gui.handler.toolbar;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.trasporti.controller.SincronizzazioneDatiTrasportiController;
import it.ltc.logica.utilities.variabili.Permessi;

public class TrasportiToolBarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID = "it.ltc.logica.trasporti.gui.perspective.trasporti";

	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI.getID();
	}
	
	@Override
	protected void operazioniAddizionaliPerAttivazionePerpespective() {
		System.out.println("Sincronizzo i dati dei trasporti!");
		SincronizzazioneDatiTrasportiController.getInstance();
	}
		
}
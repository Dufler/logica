 
package it.ltc.logica.amministrazione.gui.handler;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.utilities.variabili.Permessi;

public class AmministrazioneToolBarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID = "it.ltc.logica.amministrazione.gui.perspective.amministrazione";

	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE.getID();
	}
	
	
//	@Execute
//	public void execute(MApplication app, EPartService partService, EModelService modelService) {
//		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);
//		for (MPerspective perspective : perspectives) {
//			if (perspective.getElementId().equals(PERSPECTIVE_ID)) {
//				partService.switchPerspective(perspective);
//				perspective.setVisible(true);
//				break;
//			}
//		}
//	}
		
}
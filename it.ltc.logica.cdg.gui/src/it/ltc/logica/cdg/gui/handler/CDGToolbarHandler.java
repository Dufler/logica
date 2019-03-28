 
package it.ltc.logica.cdg.gui.handler;

import it.ltc.logica.gui.elements.ToolbarHandler;
import it.ltc.logica.utilities.variabili.Permessi;

public class CDGToolbarHandler extends ToolbarHandler {
	
	private static final String PERSPECTIVE_ID_CDG = "it.ltc.logica.cdg.gui.perspective.cdg";
	//private static final String PERSPECTIVE_ID_USER = "it.ltc.logica.container.perspective.utente";
	
//	@Execute
//	public void execute(MApplication app, EPartService partService, EModelService modelService) {
//		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);
//		for (MPerspective perspective : perspectives) {
//			if (perspective.getElementId().equals(getPerspectiveID())) {
//				partService.switchPerspective(perspective);
//				perspective.setVisible(true);
//				break;
//			}
//		}
//	}
//	
//	private String getPerspectiveID() {
//		String id = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.ADMIN.getID()) ? PERSPECTIVE_ID_CDG : PERSPECTIVE_ID_USER;
//		return id;
//	}

	@Override
	protected String getOwnPerspectiveID() {
		return PERSPECTIVE_ID_CDG;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG.getID();
	}
		
}
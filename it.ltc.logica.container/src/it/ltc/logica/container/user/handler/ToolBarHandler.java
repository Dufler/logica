 
package it.ltc.logica.container.user.handler;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ToolBarHandler {
	
	private static final String PERSPECTIVE_ID = "it.ltc.logica.container.perspective.utente";
	
	@Execute
	public void execute(MApplication app, EPartService partService, EModelService modelService) {
		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);
		for (MPerspective perspective : perspectives) {
			if (perspective.getElementId().equals(PERSPECTIVE_ID)) {
				partService.switchPerspective(perspective);
				perspective.setVisible(true);
				break;
			}
		}
	}
		
}
 
package it.ltc.logica.container.sample.handlers;

import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SwitchPerspectiveHandler {
	
	@Execute
	public void execute(MWindow window, EPartService partService, EModelService modelService, @Named("it.ltc.logica.container.commandparameter.perspectivename") String perspectiveID) {
		List<MPerspective> perspectives = modelService.findElements(window, perspectiveID, MPerspective.class, null);
		if (perspectives.isEmpty()) {
			throw new RuntimeException("Perspective ID " + perspectiveID + " non trovata!");
		} else {
			partService.switchPerspective(perspectives.get(0));
		}
	}
		
}
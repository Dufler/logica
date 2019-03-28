package it.ltc.logica.container.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.common.controller.sistema.ControllerFunzioni;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.decoration.Colore;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ControllerFeatureFunzioni {
	
	private static ControllerFeatureFunzioni instance;
	
	private final ControllerVariabiliGlobali controller;
	private final ControllerFeature controllerFeature;
	private final ControllerFunzioni controllerFunzioni;
	
	private final EPartService partService;
	private final MApplication app;
	private final EModelService modelService;

	private ControllerFeatureFunzioni(EPartService partService, MApplication app, EModelService modelService) {
		this.partService = partService;
		this.app = app;
		this.modelService = modelService;
		controller = ControllerVariabiliGlobali.getInstance();
		controllerFeature = ControllerFeature.getInstance();
		controllerFunzioni = ControllerFunzioni.getInstance();
	}

	public static synchronized ControllerFeatureFunzioni getInstance() {
		if (null == instance) {
			throw new RuntimeException("Il controller non è ancora stato instanziato.");
		}
		return instance;
	}
	
	public List<Modulo> getFeatures() {
		List<Modulo> moduli = new LinkedList<>();
		//Recupero tutte le features esistenti.
		Collection<Feature> features = controllerFeature.getFeatures();
		for (Feature feature : features) {
			//Controllo se la feature è disponibile per l'utente.
			if (controller.haPermesso(feature.getPermessoid())) {
				//Aggiungo il modulo alla lista di quelli disponibili.
				Modulo modulo = new Modulo(feature.getNome(), feature.getPerspectiveid(), feature.getIcona(), Colore.valueOf(feature.getColore()).getCodiceSWT(), feature.getPosizione());
				moduli.add(modulo);
				//Recupero tutte le funzioni contenute nella feature.
				List<Funzione> funzioni = controllerFunzioni.getFunzioniPerFeature(feature);
				for (Funzione funzione : funzioni) {
					//Controllo se la funzione è disponibile per l'utente.
					if (controller.haPermesso(funzione.getPermessoid())) {
						//Aggiungo la funzione alla lista di quelli disponibili.
						FunzioneModulo funzioneModulo = new FunzioneModulo(funzione.getNome(), funzione.getPartid(), funzione.getIcona());
						modulo.addFunzione(funzioneModulo);
					}
				}
			}
		}
		//li ordino
		moduli.sort(null);
		return moduli;
	}
	
	public void cambiaPerspective(String perspectiveID) {
//		try {
//			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//			workbenchWindow.getWorkbench().showPerspective(perspectiveID, workbenchWindow);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);
		for (MPerspective perspective : perspectives) {
			if (perspective.getElementId().equals(perspectiveID)) {
				partService.switchPerspective(perspective);
				perspective.setVisible(true);
				break;
			}
		}
	}

	public void mostraPart(String partID) {
		MPart part = partService.findPart(partID);
		if (part != null) {
			part.setVisible(true);
			partService.showPart(part, PartState.VISIBLE);
		}		
	}

	public static ControllerFeatureFunzioni injectInstance(EPartService partService, MApplication app, EModelService modelService) {
		if (instance != null)
			throw new RuntimeException("Tentativo di Injection non riuscito. Il controller è già stato instanziato.");
		instance = new ControllerFeatureFunzioni(partService, app, modelService);
		return instance;
	}

}

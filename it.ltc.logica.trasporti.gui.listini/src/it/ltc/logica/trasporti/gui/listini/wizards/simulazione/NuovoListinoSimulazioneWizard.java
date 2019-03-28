package it.ltc.logica.trasporti.gui.listini.wizards.simulazione;

import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.wizard.WizardConFinale;

public class NuovoListinoSimulazioneWizard extends WizardConFinale {
	
	private static final String title = "Nuovo listino di simulazione";
	
	private ListiniSimulazioneController controller;
	
	NuovoListinoSimulazioneProprietaWizardPage paginaProprieta;

	public NuovoListinoSimulazioneWizard() {
		super(title, false);
		controller = ListiniSimulazioneController.getInstance();
		paginaProprieta = new NuovoListinoSimulazioneProprietaWizardPage();
	}

	@Override
	public void addPages() {
		addPage(paginaProprieta);
	}

	@Override
	public boolean finisci() {
		paginaProprieta.copyDataToModel();
		ListinoSimulazione listino = paginaProprieta.getListino();
		boolean insert = controller.inserisciListino(listino);
		return insert;
	}

}

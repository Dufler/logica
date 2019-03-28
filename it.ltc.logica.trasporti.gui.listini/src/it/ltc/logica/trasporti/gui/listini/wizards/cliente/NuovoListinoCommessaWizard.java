package it.ltc.logica.trasporti.gui.listini.wizards.cliente;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.wizard.WizardConFinale;

public class NuovoListinoCommessaWizard extends WizardConFinale {
	
	private static final String title = "Nuovo listino cliente";
	
	private ControllerListiniClienti controller;
	
	NuovoListinoCommessaProprietaWizardPage paginaProprieta;

	public NuovoListinoCommessaWizard() {
		super(title, false);
		
		controller = ControllerListiniClienti.getInstance();
		paginaProprieta = new NuovoListinoCommessaProprietaWizardPage();
	}

	@Override
	public void addPages() {
		addPage(paginaProprieta);
	}

	@Override
	public boolean finisci() {
		paginaProprieta.copyDataToModel();
		ListinoCommessa listino = paginaProprieta.getListino();
		boolean insert = controller.inserisciListino(listino);
		return insert;
	}

}

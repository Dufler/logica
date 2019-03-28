package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.wizard.WizardConFinale;

public class NuovoListinoCorriereWizard extends WizardConFinale {
	
	private static final String title = "Nuovo listino per corriere";
	
	private ControllerListiniCorrieri controller;
		
	private NuovoListinoCorriereProprietaWizardPage paginaProprieta;

	public NuovoListinoCorriereWizard() {
		super(title, false);
		paginaProprieta = new NuovoListinoCorriereProprietaWizardPage();
		controller = ControllerListiniCorrieri.getInstance();
	}

	@Override
	public void addPages() {
		addPage(paginaProprieta);
	}

	@Override
	public boolean finisci() {
		paginaProprieta.copyDataToModel();
		ListinoCorriere listino = paginaProprieta.getListino();
		boolean esito = controller.inserisciListino(listino);
		return esito;
	}

}

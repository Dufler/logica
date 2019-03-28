package it.ltc.logica.trasporti.gui.codicicliente.wizard;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.wizard.WizardConFinale;

public class NuovoCodiceClienteWizard extends WizardConFinale {
	
	private static final String title = "Nuovo Codice Cliente per Corriere";
	
	private ControllerCodiciClienteCorriere controller;
	
	private NuovoCodiceClienteWizardPage page;

	public NuovoCodiceClienteWizard() {
		super(title, false);

		page = new NuovoCodiceClienteWizardPage();
		controller = ControllerCodiciClienteCorriere.getInstance();
	}

	@Override
	public void addPages() {
		addPage(page);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		return null;
	}

	@Override
	public boolean finisci() {
		page.copyDataToModel();
		CodiceClienteCorriere nuovoCodice = page.getNuovoCodice();
		boolean inserimento = controller.inserisci(nuovoCodice);
		return inserimento;
	}

}

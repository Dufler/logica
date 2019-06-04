package it.ltc.logica.trasporti.gui.report.wizards.colli;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;

public class DistribuzioneColliWizard extends WizardConRisultati {
	
	private static final String titolo = "Distribuzione del numero di colli per spedizione";
	
	private SelezionaInputWizardPage selezionePage;
	private RisultatiWizardPage risultatiPage;

	public DistribuzioneColliWizard() {
		super(titolo, true);
		selezionePage = new SelezionaInputWizardPage();
		risultatiPage = new RisultatiWizardPage();
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(risultatiPage);
	}
	
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (selezionePage.equals(page)) {
			nextPage = risultatiPage;
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

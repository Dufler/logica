package it.ltc.logica.trasporti.gui.report.wizards.semplice;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;

public class StatisticheSempliciSpedizioniWizard extends WizardConRisultati {
	
	private static final String titolo = "Statistiche sulle spedizioni";
	
	private StatisticheSempliciSpedizioniSelezionaInputWizardPage selezionePage;
	private StatisticheSempliciSpedizioniRisultatiWizardPage risultatiPage;

	public StatisticheSempliciSpedizioniWizard() {
		super(titolo, true);
		selezionePage = new StatisticheSempliciSpedizioniSelezionaInputWizardPage();
		risultatiPage = new StatisticheSempliciSpedizioniRisultatiWizardPage();
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

package it.ltc.logica.trasporti.gui.report.wizards.destinazione;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;

public class DistribuzioneDestinazioneWizard extends WizardConRisultati {
	
	private static final String titolo = "Distribuzione delle spedizioni per destinazione";
	
	private SelezionaInputWizardPage selezionePage;
	private RisultatiWizardPage risultatiPage;

	public DistribuzioneDestinazioneWizard() {
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

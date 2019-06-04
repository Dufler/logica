package it.ltc.logica.amministrazione.gui.fatturazione.wizards.uscite;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.amministrazione.gui.fatturazione.parts.FatturazionePart;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class FatturazioneUsciteWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Uscite";
	
	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	public FatturazioneUsciteWizard(FatturazionePart fp) {
		super(title, false);
		selezionePage = new SelezioneDatiFatturazioneWizardPage();
		esitoPage = new EsitoFatturazioneWizardPage();
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(esitoPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (selezionePage.equals(page)) {
			nextPage = esitoPage;
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

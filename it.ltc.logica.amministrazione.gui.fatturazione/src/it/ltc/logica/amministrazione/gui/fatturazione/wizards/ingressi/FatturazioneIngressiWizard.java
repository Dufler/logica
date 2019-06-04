package it.ltc.logica.amministrazione.gui.fatturazione.wizards.ingressi;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.amministrazione.gui.fatturazione.parts.FatturazionePart;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class FatturazioneIngressiWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Ingressi";
		
	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	public FatturazioneIngressiWizard(FatturazionePart fp) {
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

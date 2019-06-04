package it.ltc.logica.trasporti.gui.fatturazione.wizards.giacenze;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.SincronizzazioneDatiTrasportiController;
import it.ltc.logica.trasporti.gui.fatturazione.parts.FatturazionePart;

public class FatturazioneGiacenzeWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Giacenze";

	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	public FatturazioneGiacenzeWizard(FatturazionePart fp) {
		super(title, false);
		SincronizzazioneDatiTrasportiController.getInstance().sincronizzaDati();
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

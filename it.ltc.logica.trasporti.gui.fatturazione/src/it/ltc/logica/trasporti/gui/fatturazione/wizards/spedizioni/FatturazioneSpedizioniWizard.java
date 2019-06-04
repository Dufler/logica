package it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.SincronizzazioneDatiTrasportiController;
import it.ltc.logica.trasporti.gui.fatturazione.parts.FatturazionePart;

public class FatturazioneSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Spedizioni";
	
	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final SpedizioniNonFatturabiliWizardPage nonFatturabiliPage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	public FatturazioneSpedizioniWizard(FatturazionePart fp) {
		super(title, false);
		SincronizzazioneDatiTrasportiController.getInstance().sincronizzaDati();
		selezionePage = new SelezioneDatiFatturazioneWizardPage();
		esitoPage = new EsitoFatturazioneWizardPage();
		nonFatturabiliPage = new SpedizioniNonFatturabiliWizardPage();
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(nonFatturabiliPage);
		addPage(esitoPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (selezionePage.equals(page)) {
			nextPage = nonFatturabiliPage;
		} else if (nonFatturabiliPage.equals(page)) {
			nextPage = esitoPage;
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

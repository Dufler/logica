package it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.SincronizzazioneDatiTrasportiController;
import it.ltc.logica.trasporti.gui.fatturazione.parts.FatturazionePart;

public class FatturazioneSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Spedizioni";
	
//	private final FatturazionePart fp;
	
	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final SpedizioniNonFatturabiliWizardPage nonFatturabiliPage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	private final LinkedList<PaginaWizardRisultati> risultati;
	
	public FatturazioneSpedizioniWizard(FatturazionePart fp) {
		super(title, false);
		SincronizzazioneDatiTrasportiController.getInstance().sincronizzaDati();
		selezionePage = new SelezioneDatiFatturazioneWizardPage();
		esitoPage = new EsitoFatturazioneWizardPage();
		nonFatturabiliPage = new SpedizioniNonFatturabiliWizardPage();
		risultati = new LinkedList<PaginaWizardRisultati>();
		risultati.add(nonFatturabiliPage);
		risultati.add(esitoPage);
//		this.fp = fp;
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
//		fp.aggiornaTabellaDocumentiSpedizioni();
		return true;
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return risultati;
	}

}

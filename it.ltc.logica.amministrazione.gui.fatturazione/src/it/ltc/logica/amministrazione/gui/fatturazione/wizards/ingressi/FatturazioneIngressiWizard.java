package it.ltc.logica.amministrazione.gui.fatturazione.wizards.ingressi;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.amministrazione.gui.fatturazione.parts.FatturazionePart;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class FatturazioneIngressiWizard extends WizardConRisultati {
	
	private static final String title = "Fatturazione Ingressi";
	
//	private final FatturazionePart fp;
	
	private final SelezioneDatiFatturazioneWizardPage selezionePage;
	private final EsitoFatturazioneWizardPage esitoPage;
	
	private final LinkedList<PaginaWizardRisultati> risultati;
	
	public FatturazioneIngressiWizard(FatturazionePart fp) {
		super(title, false);
		selezionePage = new SelezioneDatiFatturazioneWizardPage();
		esitoPage = new EsitoFatturazioneWizardPage();
		risultati = new LinkedList<PaginaWizardRisultati>();
		risultati.add(esitoPage);
//		this.fp = fp;
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
//		fp.aggiornaTabellaDocumenti();
		return true;
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return risultati;
	}

}
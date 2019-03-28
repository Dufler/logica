package it.ltc.logica.trasporti.gui.preventivi.wizards.esistenti;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoEsistentiController;

public class PreventivoSpedizioniEsistentiWizard extends WizardConRisultati {
	
	private static final String title = "Preventivo di costo per spedizioni esistenti";
	
	private final LinkedList<PaginaWizardRisultati> fine;
	
	private PreventivoSpedizioneEsistentiSelezioneDatiWizardPage selezionePage;
	private PreventivoSpedizioniEsistentiFiltroSpedizioni filtroPage;
	private PreventivoSpedizioniEsistentiSelezioneSpedizioniWizardPage spedizioniPage;
	private PreventivoSpedizioniEsistentiRisultatoWizardPage risultatoPage;

	public PreventivoSpedizioniEsistentiWizard() {
		super(title, true);
		PreventivoEsistentiController.resetInstance();
		selezionePage = new PreventivoSpedizioneEsistentiSelezioneDatiWizardPage();
		filtroPage = new PreventivoSpedizioniEsistentiFiltroSpedizioni();
		spedizioniPage = new PreventivoSpedizioniEsistentiSelezioneSpedizioniWizardPage();
		risultatoPage = new PreventivoSpedizioniEsistentiRisultatoWizardPage();
		fine = new LinkedList<PaginaWizardRisultati>();
		fine.add(spedizioniPage);
		fine.add(risultatoPage);
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(filtroPage);
		addPage(spedizioniPage);
		addPage(risultatoPage);
	}

	@Override
	public boolean finisci() {
		return true;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		IWizardPage nextPage = null;
		if (currentPage.equals(selezionePage)) {
			nextPage = filtroPage;
		} else if (currentPage.equals(filtroPage)) {
			nextPage = spedizioniPage;
		} else if (currentPage.equals(spedizioniPage)) {
			nextPage = risultatoPage;
		}
		return nextPage;
	}
	
	@Override
	public boolean canFinish() {
		boolean finish = (getContainer().getCurrentPage() == risultatoPage);
		return finish;
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return fine;
	}

}

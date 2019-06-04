package it.ltc.logica.trasporti.gui.preventivi.wizards.importate;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoEsistentiController;

public class PreventivoSpedizioniImportateWizard extends WizardConRisultati {
	
	private static final String title = "Preventivo di costo per spedizioni importate";
	
	private final PreventivoSpedizioneImportateSelezioneDatiWizardPage selezionePage;
	private final PreventivoSpedizioniImportateSelezioneDocumentoWizardPage documentoPage;
	private final PreventivoSpedizioniImportateFiltroSpedizioni filtroPage;
	private final PreventivoSpedizioniImportateSelezioneSpedizioniWizardPage spedizioniPage;
	private final PreventivoSpedizioniImportateRisultatoWizardPage risultatoPage;

	public PreventivoSpedizioniImportateWizard() {
		super(title, true);
		PreventivoEsistentiController.resetInstance();
		selezionePage = new PreventivoSpedizioneImportateSelezioneDatiWizardPage();
		documentoPage = new PreventivoSpedizioniImportateSelezioneDocumentoWizardPage();
		filtroPage = new PreventivoSpedizioniImportateFiltroSpedizioni();
		spedizioniPage = new PreventivoSpedizioniImportateSelezioneSpedizioniWizardPage();
		risultatoPage = new PreventivoSpedizioniImportateRisultatoWizardPage();
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(documentoPage);
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
			nextPage = documentoPage;
		} else if (currentPage.equals(documentoPage)) {
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

}

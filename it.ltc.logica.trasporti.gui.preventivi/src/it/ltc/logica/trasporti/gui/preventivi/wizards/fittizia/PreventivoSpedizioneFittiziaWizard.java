package it.ltc.logica.trasporti.gui.preventivi.wizards.fittizia;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.WizardConRisultati;

public class PreventivoSpedizioneFittiziaWizard extends WizardConRisultati {
	
	private static final String title = "Preventivo di costo per una spedizione fittizia";

	private PreventivoSpedizioneFittiziaSelezioneListiniWizardPage selezioneListiniPage;
	private PreventivoSpedizioneFittiziaDatiWizardPage datiPage;
	private PreventivoSpedizioneFittiziaRisultatoWizardPage risultatoPage;

	public PreventivoSpedizioneFittiziaWizard() {
		super(title, true);
		selezioneListiniPage = new PreventivoSpedizioneFittiziaSelezioneListiniWizardPage();
		datiPage = new PreventivoSpedizioneFittiziaDatiWizardPage();
		risultatoPage = new PreventivoSpedizioneFittiziaRisultatoWizardPage();
	}

	@Override
	public void addPages() {
		addPage(datiPage);
		addPage(selezioneListiniPage);
		addPage(risultatoPage);
	}

	@Override
	public boolean finisci() {
		return true;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		IWizardPage nextPage = null;
		if (currentPage.equals(datiPage)) {
			nextPage = selezioneListiniPage;
		} else if (currentPage.equals(selezioneListiniPage)) {
			nextPage = risultatoPage;
		}
		return nextPage;
	}

}

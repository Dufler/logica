package it.ltc.logica.trasporti.gui.preventivi.wizards.fittiziadue;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class PreventivoSpedizioneFittiziaDueWizard extends WizardConRisultati {
	
	private static final String title = "Preventivo di costo per una spedizione fittizia";
	
	private final LinkedList<PaginaWizardRisultati> fine;
	
	private PreventivoSpedizioneFittiziaSelezioneListiniWizardPage selezioneListiniPage;
	private PreventivoSpedizioneFittiziaDatiWizardPage datiPage;
	private PreventivoSpedizioneFittiziaRisultatoWizardPage risultatoPage;

	public PreventivoSpedizioneFittiziaDueWizard() {
		super(title, true);
		selezioneListiniPage = new PreventivoSpedizioneFittiziaSelezioneListiniWizardPage();
		datiPage = new PreventivoSpedizioneFittiziaDatiWizardPage();
		risultatoPage = new PreventivoSpedizioneFittiziaRisultatoWizardPage();
		fine = new LinkedList<PaginaWizardRisultati>();
		fine.add(risultatoPage);
	}

	@Override
	public void addPages() {
		addPage(datiPage);
		addPage(selezioneListiniPage);
		addPage(risultatoPage);
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

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return fine;
	}
	
	@Override
	public boolean finisci() {
		return true;
	}

}

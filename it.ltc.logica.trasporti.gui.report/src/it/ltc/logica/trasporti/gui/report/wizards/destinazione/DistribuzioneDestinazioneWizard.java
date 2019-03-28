package it.ltc.logica.trasporti.gui.report.wizards.destinazione;

import java.util.LinkedList;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class DistribuzioneDestinazioneWizard extends WizardConRisultati {
	
	private static final String titolo = "Distribuzione delle spedizioni per destinazione";
	
	private final LinkedList<PaginaWizardRisultati> fine;
	
	private SelezionaInputWizardPage selezionePage;
	private RisultatiWizardPage risultatiPage;

	public DistribuzioneDestinazioneWizard() {
		super(titolo, true);
		selezionePage = new SelezionaInputWizardPage();
		risultatiPage = new RisultatiWizardPage();
		fine = new LinkedList<PaginaWizardRisultati>();
		fine.add(risultatiPage);
	}

	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(risultatiPage);
	}
	
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (selezionePage.equals(page)) {
			nextPage = risultatiPage;
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		return true;
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return fine;
	}

}
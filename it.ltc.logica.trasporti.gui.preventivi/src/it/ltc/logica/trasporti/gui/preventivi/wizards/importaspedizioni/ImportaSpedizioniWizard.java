package it.ltc.logica.trasporti.gui.preventivi.wizards.importaspedizioni;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.wizard.WizardConRisultati;
import it.ltc.logica.trasporti.controller.importazione.FileSpedizioni;
import it.ltc.logica.trasporti.controller.importazione.ImportazioneFileSpedizioniController;

public class ImportaSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Importa Dati Spedizioni";
	
	private final FileSpedizioni file;
	private final List<SpedizioneSimulazione> spedizioni;
	private final ImportazioneFileSpedizioniController controllerImportazione;
	
	private final ImportaSpedizioniSelezioneFileWizardPage selezionePage;
	private final ImportaSpedizioniVerificaInformazioniWizardPage spedizioniPage;
	
	public ImportaSpedizioniWizard() {
		super(title, false);
		file = new FileSpedizioni();
		spedizioni = new LinkedList<>();
		controllerImportazione = ImportazioneFileSpedizioniController.getInstance();
		selezionePage = new ImportaSpedizioniSelezioneFileWizardPage(file);
		spedizioniPage = new ImportaSpedizioniVerificaInformazioniWizardPage(file, spedizioni);
	}

	@Override
	public boolean finisci() {
		return controllerImportazione.importaSpedizioni(spedizioni, file);
	}
	
	@Override
	public void addPages() {
		addPage(selezionePage);
		addPage(spedizioniPage);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		IWizardPage nextPage = null;
		if (currentPage.equals(selezionePage)) {
			nextPage = spedizioniPage;
		}
		return nextPage;
	}

}

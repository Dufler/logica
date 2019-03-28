package it.ltc.logica.amministrazione.gui.listini.wizards;

import java.util.LinkedList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class NuovaVoceListinoCommessaWizard extends WizardConRisultati {
	
	private static final String title = "Nuova Voce di Listino";
	
	private final ControllerListiniClienti controller;
	
	private final VoceDiListino voce;
	
	private final LinkedList<PaginaWizardRisultati> result;
	private final NuovaVoceListinoCommessaProprietaWizardPage paginaProprieta;
	private final NuovaVoceListinoCommessaFissoWizardPage paginaFisso;
	private final NuovaVoceListinoCommessaProporzionaleWizardPage paginaProporzionale;
	private final NuovaVoceListinoCommessaPercentualeWizardPage paginaPercentuale;
	private final NuovaVoceListinoCommessaScaglioniWizardPage paginaScaglioni;
	private final NuovaVoceListinoCommessaScaglioniRipetutiWizardPage paginaScaglioniRipetuti;
	private final NuovaVoceListinoCommessaRiepilogoWizardPage paginaRiepilogo;

	private IWizardPage algoritmoSelezionato;

	public NuovaVoceListinoCommessaWizard(ListinoCommessa listinoSelezionato) {
		super(title, true);
		controller = ControllerListiniClienti.getInstance();
		voce = new VoceDiListino();
		paginaProprieta = new NuovaVoceListinoCommessaProprietaWizardPage(listinoSelezionato, voce);
		paginaFisso = new NuovaVoceListinoCommessaFissoWizardPage(voce);
		paginaProporzionale = new NuovaVoceListinoCommessaProporzionaleWizardPage(voce);
		paginaPercentuale = new NuovaVoceListinoCommessaPercentualeWizardPage(voce);
		paginaScaglioni = new NuovaVoceListinoCommessaScaglioniWizardPage(voce);
		paginaScaglioniRipetuti = new NuovaVoceListinoCommessaScaglioniRipetutiWizardPage(voce);
		paginaRiepilogo = new NuovaVoceListinoCommessaRiepilogoWizardPage(voce);
		result = new LinkedList<PaginaWizardRisultati>();
		result.add(paginaRiepilogo);
	}

	@Override
	public void addPages() {
		addPage(paginaProprieta);
		addPage(paginaFisso);
		addPage(paginaProporzionale);
		addPage(paginaPercentuale);
		addPage(paginaScaglioni);
		addPage(paginaScaglioniRipetuti);
		addPage(paginaRiepilogo);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		IWizardPage nextPage = null;
		if (currentPage.equals(paginaProprieta)) {
			switch (paginaProprieta.getTipoAlgoritmoSelezionato()) {
				case FISSO : {nextPage = paginaFisso; break;}
				case PROPORZIONALE : {nextPage = paginaProporzionale; break;}
				case SCAGLIONI : {nextPage = paginaScaglioni; break;}
				case SCAGLIONI_RIPETUTI : {nextPage = paginaScaglioniRipetuti; break;}
				case PERCENTUALE : {nextPage = paginaPercentuale; break;}
				default : break;
			}
			algoritmoSelezionato = nextPage;
		} else if (currentPage.equals(algoritmoSelezionato)) {
			nextPage = paginaRiepilogo;
			paginaRiepilogo.setTipoAlgoritmo(paginaProprieta.getTipoAlgoritmoSelezionato());
		}
		return nextPage;
	}

	@Override
	public boolean finisci() {
		boolean finish = controller.inserisciVoce(voce);
		if (!finish) {
			MessageDialog.openError(null, "Errore", "Ci sono stati problemi con l'inserimento.");
		}
		return finish;
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return result;
	}

}

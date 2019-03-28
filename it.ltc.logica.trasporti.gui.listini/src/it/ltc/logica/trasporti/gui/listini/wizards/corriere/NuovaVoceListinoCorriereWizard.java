package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import java.util.LinkedList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class NuovaVoceListinoCorriereWizard extends WizardConRisultati {
	
	private static final String title = "Nuova voce di listino";
	
	private final ControllerListiniCorrieri controller;
	private final VoceDiListinoCorriere voce;
	
	private final NuovaVoceListinoCorriereProprietaWizardPage paginaIniziale;
	private final NuovaVoceListinoCorriereFissoWizardPage paginaFisso;
	private final NuovaVoceListinoCorriereProporzionaleWizardPage paginaProporzionale;
	private final NuovaVoceListinoCorriereScaglioniWizardPage paginaScaglioni;
	private final NuovaVoceListinoCorriereScaglioniRipetutiWizardPage paginaScaglioniRipetuti;
	private final NuovaVoceListinoCorrierePercentualeWizardPage paginaPercentuale;
	private final NuovaVoceListinoCorriereRiepilogoWizardPage paginaRiepilogo;
	
	private final LinkedList<PaginaWizardRisultati> result;
	
	private IWizardPage algoritmoSelezionato;

	public NuovaVoceListinoCorriereWizard(ListinoCorriere listino) {
		super(title, false);
		controller = ControllerListiniCorrieri.getInstance();
		voce = new VoceDiListinoCorriere();
		paginaIniziale = new NuovaVoceListinoCorriereProprietaWizardPage(listino, voce);
		paginaFisso = new NuovaVoceListinoCorriereFissoWizardPage(voce);
		paginaProporzionale = new NuovaVoceListinoCorriereProporzionaleWizardPage(voce);
		paginaScaglioni = new NuovaVoceListinoCorriereScaglioniWizardPage(voce);
		paginaScaglioniRipetuti = new NuovaVoceListinoCorriereScaglioniRipetutiWizardPage(voce);
		paginaPercentuale = new NuovaVoceListinoCorrierePercentualeWizardPage(voce);
		paginaRiepilogo = new NuovaVoceListinoCorriereRiepilogoWizardPage(voce);
		result = new LinkedList<PaginaWizardRisultati>();
		result.add(paginaRiepilogo);
	}

	@Override
	public void addPages() {
		addPage(paginaIniziale);
		addPage(paginaFisso);
		addPage(paginaProporzionale);
		addPage(paginaScaglioni);
		addPage(paginaScaglioniRipetuti);
		addPage(paginaPercentuale);
		addPage(paginaRiepilogo);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		IWizardPage nextPage = null;
		if (currentPage.equals(paginaIniziale)) {
			switch (paginaIniziale.getTipoAlgoritmoSelezionato()) {
				case FISSO : {nextPage = paginaFisso; break;}
				case PROPORZIONALE : {nextPage = paginaProporzionale; break;}
				case SCAGLIONI : {nextPage = paginaScaglioni; break;}
				case SCAGLIONI_RIPETUTI : {nextPage = paginaScaglioniRipetuti; break;}
				case PERCENTUALE : {nextPage = paginaPercentuale; break;}
				default : break;
			}
			algoritmoSelezionato = nextPage;
		} else if (currentPage.equals(algoritmoSelezionato)) {
			paginaRiepilogo.setTipoAlgoritmo(paginaIniziale.getTipoAlgoritmoSelezionato());
			nextPage = paginaRiepilogo;
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

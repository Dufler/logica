package it.ltc.logica.trasporti.gui.listini.wizards.simulazione.voce;

import org.eclipse.jface.wizard.IWizardPage;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class NuovaVoceListinoSimulazioneWizard extends WizardConRisultati {
	
	private static final String title = "Nuova Voce di Listino";
	
	private ListiniSimulazioneController controller;
	
	private final ListinoSimulazioneVoce voce;
	
	private NuovaVoceListinoSimulazioneProprietaWizardPage paginaProprieta;
	private NuovaVoceListinoSimulazioneFissaWizardPage paginaFisso;
	private NuovaVoceListinoSimulazioneProporzionaleWizardPage paginaProporzionale;
	private NuovaVoceListinoSimulazionePercentualeWizardPage paginaPercentuale;
	private NuovaVoceListinoSimulazioneScaglioniWizardPage paginaScaglioni;
	private NuovaVoceListinoSimulazioneScaglioniRipetutiWizardPage paginaScaglioniRipetuti;
	private NuovaVoceListinoSimulazioneRiepilogoWizardPage paginaRiepilogo;

	private IWizardPage algoritmoSelezionato;

	public NuovaVoceListinoSimulazioneWizard(ListinoSimulazione listinoSelezionato) {
		super(title, true);
		
		controller = ListiniSimulazioneController.getInstance();
		
		voce = new ListinoSimulazioneVoce();
		
		paginaProprieta = new NuovaVoceListinoSimulazioneProprietaWizardPage(listinoSelezionato, voce);
		paginaFisso = new NuovaVoceListinoSimulazioneFissaWizardPage(voce);
		paginaProporzionale = new NuovaVoceListinoSimulazioneProporzionaleWizardPage(voce);
		paginaPercentuale = new NuovaVoceListinoSimulazionePercentualeWizardPage(voce);
		paginaScaglioni = new NuovaVoceListinoSimulazioneScaglioniWizardPage(voce);
		paginaScaglioniRipetuti = new NuovaVoceListinoSimulazioneScaglioniRipetutiWizardPage(voce);
		paginaRiepilogo = new NuovaVoceListinoSimulazioneRiepilogoWizardPage(voce);
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
			TipoAlgoritmo tipoAlgoritmo = paginaProprieta.getTipoAlgoritmoSelezionato();
			switch (tipoAlgoritmo) {
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
			DialogMessaggio.openError("Errore", "Ci sono stati problemi con l'inserimento.");
		}
		return finish;
	}

}

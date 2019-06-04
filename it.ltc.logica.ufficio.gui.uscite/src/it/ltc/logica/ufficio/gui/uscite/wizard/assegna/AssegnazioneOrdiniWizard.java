package it.ltc.logica.ufficio.gui.uscite.wizard.assegna;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class AssegnazioneOrdiniWizard extends WizardConRisultati {
	
	private static final String title = "Assegnazione Ordini";
	
	private final SelezioneOrdiniWizardPage paginaSelezione;
	private final RisultatoAssegnazioneWizardPage paginaRisultato;
	
	public AssegnazioneOrdiniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaAssegnare = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniWizardPage(commessa, ordiniDaAssegnare);
		paginaRisultato = new RisultatoAssegnazioneWizardPage(commessa, ordiniDaAssegnare);
	}
	
	@Override
	public void addPages() {
		addPage(paginaSelezione);
		addPage(paginaRisultato);
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

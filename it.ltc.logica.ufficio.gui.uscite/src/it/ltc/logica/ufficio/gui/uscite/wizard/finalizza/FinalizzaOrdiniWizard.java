package it.ltc.logica.ufficio.gui.uscite.wizard.finalizza;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class FinalizzaOrdiniWizard extends WizardConRisultati {
	
	private static final String title = "Finalizzazione Ordini";

	private final SelezioneOrdiniWizardPage paginaSelezione;
	private final RisultatoFinalizzazioneWizardPage paginaRisultato;
	
	public FinalizzaOrdiniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaFinalizzare = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniWizardPage(commessa, ordiniDaFinalizzare);
		paginaRisultato = new RisultatoFinalizzazioneWizardPage(commessa, ordiniDaFinalizzare);
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

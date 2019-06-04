package it.ltc.logica.ufficio.gui.uscite.wizard.generamovimenti;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class GeneraMovimentiOrdiniWizard extends WizardConRisultati {
	
	private static final String title = "Generazione Movimenti Di Uscita Ordini";

	private final SelezioneOrdiniWizardPage paginaSelezione;
	private final RisultatoGenerazioneOrdiniWizardPage paginaRisultato;
	
	public GeneraMovimentiOrdiniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaFinalizzare = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniWizardPage(commessa, ordiniDaFinalizzare);
		paginaRisultato = new RisultatoGenerazioneOrdiniWizardPage(commessa, ordiniDaFinalizzare);
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

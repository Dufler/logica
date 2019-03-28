package it.ltc.logica.ufficio.gui.uscite.wizard.generamovimenti;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class GeneraMovimentiOrdiniWizard extends WizardConRisultati {
	
	private static final String title = "Generazione Movimenti Di Uscita Ordini";

	private final SelezioneOrdiniWizardPage paginaSelezione;
	private final RisultatoGenerazioneOrdiniWizardPage paginaRisultato;
	
	private final LinkedList<PaginaWizardRisultati> risultati;
	
	public GeneraMovimentiOrdiniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaFinalizzare = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniWizardPage(commessa, ordiniDaFinalizzare);
		paginaRisultato = new RisultatoGenerazioneOrdiniWizardPage(commessa, ordiniDaFinalizzare);
		risultati = new LinkedList<>();
		risultati.add(paginaRisultato);
	}
	
	@Override
	public void addPages() {
		addPage(paginaSelezione);
		addPage(paginaRisultato);
	}

	@Override
	public LinkedList<PaginaWizardRisultati> getPaginaRisultati() {
		return risultati;
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

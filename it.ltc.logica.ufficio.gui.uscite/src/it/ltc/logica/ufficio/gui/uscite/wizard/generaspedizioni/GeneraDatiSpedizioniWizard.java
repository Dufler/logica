package it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class GeneraDatiSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Generazione Dati Spedizioni";

	private final SelezioneOrdiniDaSpedireWizardPage paginaSelezione;
	private final RisultatoGenerazioneDatiSpedizioniWizardPage paginaRisultato;
	
	private final LinkedList<PaginaWizardRisultati> risultati;
	
	public GeneraDatiSpedizioniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaFinalizzare = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniDaSpedireWizardPage(commessa, ordiniDaFinalizzare);
		paginaRisultato = new RisultatoGenerazioneDatiSpedizioniWizardPage(commessa, ordiniDaFinalizzare);
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

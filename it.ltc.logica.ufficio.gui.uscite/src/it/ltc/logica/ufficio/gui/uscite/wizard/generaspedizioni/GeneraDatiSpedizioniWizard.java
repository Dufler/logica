package it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class GeneraDatiSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Generazione Dati Spedizioni";

	private final SelezioneOrdiniDaSpedireWizardPage paginaSelezione;
	private final TrovaDatiSpedizioniWizardPage paginaRicerca;
	private final RisultatiGenerazioneDatiSpedizioniWizardPage paginaRisultato;
	
	public GeneraDatiSpedizioniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaFinalizzare = new LinkedList<>();
		List<DatiSpedizione> dati = new LinkedList<>();
		paginaSelezione = new SelezioneOrdiniDaSpedireWizardPage(commessa, ordiniDaFinalizzare);
		paginaRicerca = new TrovaDatiSpedizioniWizardPage(commessa, ordiniDaFinalizzare, dati);
		paginaRisultato = new RisultatiGenerazioneDatiSpedizioniWizardPage(commessa, dati);
	}
	
	@Override
	public void addPages() {
		addPage(paginaSelezione);
		addPage(paginaRicerca);
		addPage(paginaRisultato);
	}

	@Override
	public boolean finisci() {
		return true;
	}

}

package it.ltc.logica.ufficio.gui.uscite.wizard.raggruppaspedizioni;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.WizardConRisultati;

public class RaggruppaSpedizioniWizard extends WizardConRisultati {
	
	private static final String title = "Raggruppamento Spedizioni";

	private final SelezioneOrdiniDaRaggruppareWizardPage paginaSelezione;
	private final ConfermaDatiSpedizioneRaggruppataWizardPage paginaRicerca;
	private final RisultatiRaggruppamentoSpedizioniWizardPage paginaRisultato;
	
	public RaggruppaSpedizioniWizard(Commessa commessa) {
		super(title, false);
		List<OrdineTestata> ordiniDaRaggruppare = new LinkedList<>();
		DatiSpedizione datiRaggruppamento = new DatiSpedizione();
		paginaSelezione = new SelezioneOrdiniDaRaggruppareWizardPage(commessa, ordiniDaRaggruppare);
		paginaRicerca = new ConfermaDatiSpedizioneRaggruppataWizardPage(commessa, ordiniDaRaggruppare, datiRaggruppamento);
		paginaRisultato = new RisultatiRaggruppamentoSpedizioniWizardPage(commessa, datiRaggruppamento);
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

package it.ltc.logica.ufficio.gui.uscite.wizard.generamovimenti;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.RisultatoGenerazioneMovimenti;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.generamovimenti.TabellaProblemiGenerazioneOrdine;

public class RisultatoGenerazioneOrdiniWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Generazione Movimenti Ordini";
	private final static String description = "Risultato della generazione.";
	
	private TabellaProblemiGenerazioneOrdine tabella;
	
	private final List<OrdineTestata> ordiniDaFinalizzare;
	private final ControllerOrdini controller;

	protected RisultatoGenerazioneOrdiniWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaFinalizzare) {
		super(title, description, true);
		this.ordiniDaFinalizzare = ordiniDaFinalizzare;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		List<RisultatoGenerazioneMovimenti> risultati = new LinkedList<>();
		for (OrdineTestata ordine : ordiniDaFinalizzare) {
			RisultatoGenerazioneMovimenti risultato = controller.generaMovimenti(ordine);
			risultati.add(risultato);
		}
		tabella.setElementi(risultati);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaProblemiGenerazioneOrdine(container);
	}

}

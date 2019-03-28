package it.ltc.logica.ufficio.gui.uscite.wizard.assegna;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.assegnazione.CompositeControlliAssegnazione;
import it.ltc.logica.ufficio.gui.uscite.elements.assegnazione.TabellaRisultatoAssegnazione;

public class RisultatoAssegnazioneWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Assegnazione Ordini";
	private final static String description = "Risultato dell'assegnazione.";
	
	private TabellaRisultatoAssegnazione tabella;
	
	private final Commessa commessa;
	private final ControllerOrdini controller;
	private final List<OrdineTestata> ordiniDaAssegnare;
	
	private CompositeControlliAssegnazione compositeControlli;
	
	private boolean risultatoVisualizzato;

	protected RisultatoAssegnazioneWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaFinalizzare) {
		super(title, description, true);
		this.commessa = commessa;
		this.ordiniDaAssegnare = ordiniDaFinalizzare;
		this.controller = new ControllerOrdini(commessa);
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		return risultatoVisualizzato;
	}

	@Override
	public void mostraRisultato() {
		//Assegnazione e recupero dei risultati
		List<RisultatoAssegnazioneOrdine> risultati = new LinkedList<>();
		for (OrdineTestata ordine : ordiniDaAssegnare) {
			RisultatoAssegnazioneOrdine risultato = controller.assegna(ordine);
			risultati.add(risultato);		
		}
		//Visualizzazione del risultato in tabella e controlli
		tabella.setElementi(risultati);
		compositeControlli.setRisultati(risultati);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaRisultatoAssegnazione(container);
		
		compositeControlli = new CompositeControlliAssegnazione(commessa, this, container);
	}

}

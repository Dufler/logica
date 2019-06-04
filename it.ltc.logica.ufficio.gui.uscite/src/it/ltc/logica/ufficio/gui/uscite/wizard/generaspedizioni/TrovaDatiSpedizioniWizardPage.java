package it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni;

import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.TabellaDatiSpedizioneSemplice;

public class TrovaDatiSpedizioniWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Generazione Dati Spedizioni";
	private final static String description = "Verifica i dati sulle spedizioni.";
	
	private TabellaDatiSpedizioneSemplice tabella;
	
	private final Commessa commessa;
	private final List<OrdineTestata> ordiniDaSpedire;
	private final List<DatiSpedizione> dati;
	private final ControllerOrdini controller;

	protected TrovaDatiSpedizioniWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaSpedire, List<DatiSpedizione> dati) {
		super(title, description, false);
		this.commessa = commessa;
		this.ordiniDaSpedire = ordiniDaSpedire;
		this.dati = dati;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		for (OrdineTestata ordine : ordiniDaSpedire) {
			DatiSpedizione datiSpedizione = controller.trovaDatiSpedizione(ordine);
			dati.add(datiSpedizione);
		}
		tabella.setElementi(dati);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaDatiSpedizioneSemplice(container, commessa);
	}

}

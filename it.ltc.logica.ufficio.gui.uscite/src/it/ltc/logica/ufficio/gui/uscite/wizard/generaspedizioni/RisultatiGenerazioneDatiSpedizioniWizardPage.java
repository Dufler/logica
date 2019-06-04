package it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato.RisultatoSalvataggioDatiSpedizione;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato.TabellaRisultatoDatiSpedizione;

public class RisultatiGenerazioneDatiSpedizioniWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Generazione Dati Spedizioni";
	private final static String description = "Risultato della generazione spedizioni.";
	
	private TabellaRisultatoDatiSpedizione tabella;
	
	private final List<DatiSpedizione> datiSpedizioni;
	private final ControllerOrdini controller;

	protected RisultatiGenerazioneDatiSpedizioniWizardPage(Commessa commessa, List<DatiSpedizione> datiSpedizioni) {
		super(title, description, true);
		this.datiSpedizioni = datiSpedizioni;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		List<RisultatoSalvataggioDatiSpedizione> risultati = new LinkedList<>();
		for (DatiSpedizione dati : datiSpedizioni) {
			RisultatoSalvataggioDatiSpedizione risultato = new RisultatoSalvataggioDatiSpedizione();
			risultato.setRiferimento(dati.getRiferimento());
			String ordine = (dati.getOrdini() != null && !dati.getOrdini().isEmpty()) ? dati.getOrdini().get(0).getNumeroLista() : "N/A";
			risultato.setOrdine(ordine);
			DatiSpedizione datiSpedizione = controller.salvaDatiSpedizione(dati);
			risultato.setRisultato(datiSpedizione != null);
			risultati.add(risultato);
		}
		tabella.setElementi(risultati);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaRisultatoDatiSpedizione(container);
	}

}

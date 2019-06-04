package it.ltc.logica.ufficio.gui.uscite.wizard.raggruppaspedizioni;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato.RisultatoSalvataggioDatiSpedizione;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato.TabellaRisultatoDatiSpedizione;

public class RisultatiRaggruppamentoSpedizioniWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Generazione Dati Spedizioni";
	private final static String description = "Risultato della generazione spedizioni.";
	
	private TabellaRisultatoDatiSpedizione tabella;
	
	private final DatiSpedizione datiSpedizioni;
	private final ControllerOrdini controller;

	protected RisultatiRaggruppamentoSpedizioniWizardPage(Commessa commessa, DatiSpedizione datiSpedizioni) {
		super(title, description, true);
		this.datiSpedizioni = datiSpedizioni;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		RisultatoSalvataggioDatiSpedizione risultato = new RisultatoSalvataggioDatiSpedizione();
		risultato.setRiferimento(datiSpedizioni.getRiferimento());
		StringBuilder riferimentoOrdini = new StringBuilder();
		if (datiSpedizioni.getOrdini() != null) for (OrdineTestata ordine : datiSpedizioni.getOrdini()) {
			riferimentoOrdini.append(ordine.getNumeroLista());
			riferimentoOrdini.append("\r\n");
		} else {
			riferimentoOrdini.append("N/A");
		}
		risultato.setOrdine(riferimentoOrdini.toString());
		DatiSpedizione datiSpedizione = controller.salvaDatiSpedizione(datiSpedizioni);
		risultato.setRisultato(datiSpedizione != null);
		RisultatoSalvataggioDatiSpedizione[] array = new RisultatoSalvataggioDatiSpedizione[1];
		array[0] = risultato;
		tabella.setElementi(array);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaRisultatoDatiSpedizione(container);
	}

}

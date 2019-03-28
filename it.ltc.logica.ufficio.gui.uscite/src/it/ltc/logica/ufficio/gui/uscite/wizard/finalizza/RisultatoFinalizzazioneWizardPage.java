package it.ltc.logica.ufficio.gui.uscite.wizard.finalizza;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.RisultatoFinalizzazioneOrdine;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.ufficio.gui.uscite.elements.finalizzazione.TabellaProblemiFinalizzazioneOrdine;

public class RisultatoFinalizzazioneWizardPage extends PaginaWizardRisultati {
	
	private final static String title = "Finalizzazione Ordini";
	private final static String description = "Risultato della finalizzazione.";
	
	private TabellaProblemiFinalizzazioneOrdine tabella;
	
	//private final Commessa commessa;
	private final List<OrdineTestata> ordiniDaFinalizzare;
	private final ControllerOrdini controller;

	protected RisultatoFinalizzazioneWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaFinalizzare) {
		super(title, description, true);
		//this.commessa = commessa;
		this.ordiniDaFinalizzare = ordiniDaFinalizzare;
		this.controller = new ControllerOrdini(commessa);
	}

	@Override
	public void mostraRisultato() {
		List<RisultatoFinalizzazioneOrdine> risultati = new LinkedList<>();
		for (OrdineTestata ordine : ordiniDaFinalizzare) {
			RisultatoFinalizzazioneOrdine risultato = controller.finalizza(ordine);
			risultati.add(risultato);
		}
		tabella.setElementi(risultati);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaProblemiFinalizzazioneOrdine(container);
	}
	
//	private void showSamples() {
//		List<RisultatoFinalizzazioneOrdine> input = new LinkedList<>();
//		//Risultato 1, ok
//		RisultatoFinalizzazioneOrdine risultato1 = new RisultatoFinalizzazioneOrdine();
//		OrdineTestata ordine1 = new OrdineTestata();
//		ordine1.setNumeroLista("1710864500000000");
//		ordine1.setRiferimento("108645");
//		List<ProblemaFinalizzazioneOrdine> senzaProblemi = new LinkedList<>();
//		risultato1.setOrdine(ordine1);
//		risultato1.setProblemi(senzaProblemi);
//		input.add(risultato1);
//		//Risultato 2, con errori
//		RisultatoFinalizzazioneOrdine risultato2 = new RisultatoFinalizzazioneOrdine();
//		OrdineTestata ordine2 = new OrdineTestata();
//		ordine2.setNumeroLista("1711476100000000");
//		ordine2.setRiferimento("1456845");
//		risultato2.setOrdine(ordine2);
//		List<ProblemaFinalizzazioneOrdine> problemi = new LinkedList<>();
//		ProblemaFinalizzazioneOrdine problema1 = new ProblemaFinalizzazioneOrdine();
//		problema1.setIdOrdine(1);
//		problema1.setIdProdotto(1);
//		problema1.setIdRiga(1);
//		problema1.setQuantitaDisponibile(2);
//		problema1.setQuantitaRichiesta(5);
//		problemi.add(problema1);
//		ProblemaFinalizzazioneOrdine problema2 = new ProblemaFinalizzazioneOrdine();
//		problema2.setIdOrdine(1);
//		problema2.setIdProdotto(2);
//		problema2.setIdRiga(2);
//		problema2.setQuantitaDisponibile(0);
//		problema2.setQuantitaRichiesta(3);
//		problemi.add(problema2);
//		risultato2.setProblemi(problemi);
//		input.add(risultato2);
//		tabella.setElementi(input);
//	}

}

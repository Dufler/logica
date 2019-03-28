package it.ltc.logica.ufficio.gui.elements.ordineoperatori;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.ordini.OperatoreOrdine;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaOrdineOperatori extends Tabella<OperatoreOrdine> {
	
	private final ControllerOrdini controller;
	private final OrdineTestata ordine;

	public TabellaOrdineOperatori(Composite parent, ControllerOrdini controller, OrdineTestata ordine) {
		super(parent);
		this.controller = controller;
		this.ordine = ordine;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Operatore", 100, 0);
		aggiungiColonna("Lavoro", 100, 1);
		aggiungiColonna("Quantit\u00E0", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		if (controller != null && ordine != null)
			setElementi(controller.trovaOperatori(ordine.getId()));
	}

	@Override
	protected Ordinatore<OperatoreOrdine> creaOrdinatore() {
		return new OrdinatoreOrdineOperatori();
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING!
	}

	@Override
	protected Etichettatore<OperatoreOrdine> creaEtichettatore() {
		return new EtichettatoreOrdineOperatori();
	}

	@Override
	protected ModificatoreValoriCelle<OperatoreOrdine> creaModificatore() {
		return null;
	}

}

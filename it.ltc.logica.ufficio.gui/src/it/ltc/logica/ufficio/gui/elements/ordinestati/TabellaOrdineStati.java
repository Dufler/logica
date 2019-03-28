package it.ltc.logica.ufficio.gui.elements.ordinestati;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.ordini.OrdineStato;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaOrdineStati extends Tabella<OrdineStato> {
	
	private final ControllerOrdini controller;
	private final OrdineTestata ordine;

	public TabellaOrdineStati(Composite parent, ControllerOrdini controller, OrdineTestata ordine) {
		super(parent);
		this.controller = controller;
		this.ordine = ordine;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Stato", 100, 1);
		aggiungiColonna("Data", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		if (controller != null && ordine != null)
			setElementi(controller.trovaStati(ordine.getId()));	
	}

	@Override
	protected Ordinatore<OrdineStato> creaOrdinatore() {
		return new OrdinatoreOrdineStati();
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
	protected Etichettatore<OrdineStato> creaEtichettatore() {
		return new EtichettatoreOrdineStati();
	}

	@Override
	protected ModificatoreValoriCelle<OrdineStato> creaModificatore() {
		return null;
	}

}

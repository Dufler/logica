package it.ltc.logica.ufficio.gui.elements.caricostati;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.ingressi.ControllerCarichi;
import it.ltc.logica.database.model.centrale.ingressi.CaricoStato;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaCaricoStati extends Tabella<CaricoStato> {
	
	private final ControllerCarichi controllerCarichi;
	private final CaricoTestata carico;

	public TabellaCaricoStati(Composite parent, ControllerCarichi controllerCarichi, CaricoTestata carico) {
		super(parent);
		this.controllerCarichi = controllerCarichi;
		this.carico = carico;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Stato", 100, 1);
		aggiungiColonna("Data", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		if (controllerCarichi != null && carico != null)
			setElementi(controllerCarichi.trovaStati(carico.getId()));	
	}

	@Override
	protected Ordinatore<CaricoStato> creaOrdinatore() {
		return new OrdinatoreCaricoStati();
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
	protected Etichettatore<CaricoStato> creaEtichettatore() {
		return new EtichettatoreCaricoStati();
	}

	@Override
	protected ModificatoreValoriCelle<CaricoStato> creaModificatore() {
		return null;
	}

}

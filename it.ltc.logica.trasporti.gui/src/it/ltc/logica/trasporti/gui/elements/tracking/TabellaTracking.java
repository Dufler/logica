package it.ltc.logica.trasporti.gui.elements.tracking;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.database.model.centrale.Tracking;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaTracking extends Tabella<Tracking> {

	public TabellaTracking(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 0);
		aggiungiColonna("Stato", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}

	@Override
	protected void aggiungiListener() {
		//Non e' stato richiesto nessun evento specifico
		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//Non e' stato richiesto nessun menu' specifico
		
	}

	@Override
	protected Ordinatore<Tracking> creaOrdinatore() {
		return new OrdinatoreTracking();
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING, gestisto esternamente.		
	}

	@Override
	protected Etichettatore<Tracking> creaEtichettatore() {
		return new EtichettatoreTracking();
	}

	@Override
	protected ModificatoreValoriCelle<Tracking> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.trasporti.gui.preventivi.elements;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.trasporti.controller.preventivi.TotaleListino;

public class TabellaTotali extends Tabella<TotaleListino> {

	public TabellaTotali(Composite parent, int style) {
		super(parent, style);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Tipo", 100, 1);
		aggiungiColonna("Totale", 100, 2);
		aggiungiColonna("Nolo", 100, 3);
		aggiungiColonna("Contrassegno", 100, 4);
		aggiungiColonna("Extra", 100, 5);
	}
	
	@Override
	protected void aggiungiListener() {
		//DO NOTHING! - Non sono necessari altri listener.
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING! - Non sono necessari altri elementi di menu.
	}

	@Override
	protected Ordinatore<TotaleListino> creaOrdinatore() {
		return new OrdinatoreTotali();
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING!
		
	}

	@Override
	protected Etichettatore<TotaleListino> creaEtichettatore() {
		return new EtichettatoreTotali();
	}

	@Override
	protected ModificatoreValoriCelle<TotaleListino> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.amministrazione.gui.controllodigestione.elements;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaCDGSpedizioni extends Tabella<Spedizione> {

	public TabellaCDGSpedizioni(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Riferimento", 200, 0);
		aggiungiColonna("Data", 100, 1);
		aggiungiColonna("Commessa", 100, 2);
		aggiungiColonna("Corriere", 150, 3);
		aggiungiColonna("Costo", 100, 4);
		aggiungiColonna("Ricavo", 100, 5);
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Ordinatore<Spedizione> creaOrdinatore() {
		return new OrdinatoreCDGSpedizioni();
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING!		
	}

	@Override
	protected Etichettatore<Spedizione> creaEtichettatore() {
		return new EtichettatoreCDGSpedizioni();
	}

	@Override
	protected ModificatoreValoriCelle<Spedizione> creaModificatore() {
		return null;
	}

}

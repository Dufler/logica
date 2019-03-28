package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.prodotti.ProdottoConQuantita;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaProdottiConQuantita extends Tabella<ProdottoConQuantita> {

	public TabellaProdottiConQuantita(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Articolo", 100, 0);
		aggiungiColonna("Taglia", 100, 1);
		aggiungiColonna("Quantit\u00E0", 100, 2);
		
		mappaColonne.get(2).setEditingSupport(new ProdottiConQuantitaEditingSupport(this));
	}

	@Override
	protected void aggiungiListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Ordinatore<ProdottoConQuantita> creaOrdinatore() {
		return new OrdinatoreProdottiConQuantita();
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<ProdottoConQuantita> creaEtichettatore() {
		return new EtichettatoreProdottiConQuantita();
	}

	@Override
	protected ModificatoreValoriCelle<ProdottoConQuantita> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.amministrazione.gui.fatturazione.elements;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.amministrazione.calcolo.algoritmi.LogisticaModel;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaFatturazione extends Tabella<LogisticaModel> {

	public TabellaFatturazione(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Riferimento", 200, 0);
		aggiungiColonna("Data", 100, 1);
		aggiungiColonna("Tipo", 100, 2);
		aggiungiColonna("Totale", 100, 3);
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
	protected Ordinatore<LogisticaModel> creaOrdinatore() {
		return new OrdinatoreFatturazione();
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<LogisticaModel> creaEtichettatore() {
		return new EtichettatoreFatturazione();
	}

	@Override
	protected ModificatoreValoriCelle<LogisticaModel> creaModificatore() {
		return null;
	}

}

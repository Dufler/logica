package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.database.model.centrale.ingressi.IngressoColloDettaglio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaColloConProdotti extends Tabella<IngressoColloDettaglio> {

	public TabellaColloConProdotti(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		// TODO Auto-generated method stub
		
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
	protected Ordinatore<IngressoColloDettaglio> creaOrdinatore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<IngressoColloDettaglio> creaEtichettatore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModificatoreValoriCelle<IngressoColloDettaglio> creaModificatore() {
		// TODO Auto-generated method stub
		return null;
	}

}

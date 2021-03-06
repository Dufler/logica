package it.ltc.logica.trasporti.gui.preventivi.elements.listini;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.Listino;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaCheckboxListini extends TabellaCheckBox<Listino> {

	public TabellaCheckboxListini(Composite parent) {
		super(parent, STILE_SEMPLICE);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Listino", 100, 0);
		aggiungiColonna("Descrizione", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		//Viene gestito esternamente.		
	}

	@Override
	protected Ordinatore<Listino> creaOrdinatore() {
		return new OrdinatoreListini();
	}

	@Override
	protected Etichettatore<Listino> creaEtichettatore() {
		return new EtichettatoreListini();
	}

	@Override
	protected ModificatoreValoriCelle<Listino> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.gui.common.tables.nazioni;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaNazioni extends TabellaCheckBox<Nazione> {

	public TabellaNazioni(Composite parent) {
		super(parent);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Codice ISO", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerNazioni.getInstance().getNazioni());	
	}

	@Override
	protected Ordinatore<Nazione> creaOrdinatore() {
		return new OrdinatoreNazioni();
	}

	@Override
	protected Etichettatore<Nazione> creaEtichettatore() {
		return new EtichettatoreNazioni();
	}

	@Override
	protected ModificatoreValoriCelle<Nazione> creaModificatore() {
		return null;
	}

}

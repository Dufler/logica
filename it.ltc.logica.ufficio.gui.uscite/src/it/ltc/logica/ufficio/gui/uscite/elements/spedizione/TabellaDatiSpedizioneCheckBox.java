package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaDatiSpedizioneCheckBox extends TabellaCheckBox<DatiSpedizione> {

	public TabellaDatiSpedizioneCheckBox(Composite parent) {
		super(parent);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Riferimento", 100, 0);
		aggiungiColonna("Numero lista", 100, 1);
		aggiungiColonna("Colli", 100, 2);
		aggiungiColonna("Peso", 100, 3);
		aggiungiColonna("Volume", 100, 4);
		aggiungiColonna("Corriere", 100, 5);
		aggiungiColonna("Servizio", 100, 6);
		aggiungiColonna("Contrassegno", 100, 7);
	}

	@Override
	public void aggiornaContenuto() {
		//Gestito esternamente		
	}

	@Override
	protected Ordinatore<DatiSpedizione> creaOrdinatore() {
		return new OrdinatoreDatiSpedizione();
	}

	@Override
	protected Etichettatore<DatiSpedizione> creaEtichettatore() {
		return new EtichettatoreDatiSpedizione();
	}

	@Override
	protected ModificatoreValoriCelle<DatiSpedizione> creaModificatore() {
		return null;
	}

}

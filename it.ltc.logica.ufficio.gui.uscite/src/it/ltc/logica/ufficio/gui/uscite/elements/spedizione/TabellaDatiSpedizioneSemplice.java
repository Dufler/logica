package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRU;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaDatiSpedizioneSemplice extends TabellaCRU<DatiSpedizione> {

	private final Commessa commessa;
	
	public TabellaDatiSpedizioneSemplice(Composite parent, Commessa commessa) {
		super(parent);
		this.commessa = commessa;
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
		// DO NOTHING! (Gestito esternamente)	
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

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}

	@Override
	protected DialogApribile creaDialog(DatiSpedizione elemento) {
		DialogDatiSpedizione dialog = new DialogDatiSpedizione(commessa, elemento);
		return dialog;
	}

}

package it.ltc.logica.amministrazione.gui.commesse.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.commesse.dialogs.DialogCommessa;
import it.ltc.logica.common.controller.sistema.ControllerCommesseCentrale;
import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCommesse extends TabellaCRUDConFiltro<CommessaCentrale, CriteriFiltraggioSoloTesto> {

	public TabellaCommesse(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);	
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Commessa", 120, 0);
		aggiungiColonna("Sede", 80, 1);
		aggiungiColonna("Descrizione", 200, 2);
	}

	@Override
	protected Ordinatore<CommessaCentrale> creaOrdinatore() {
		return new OrdinatoreCommesse();
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}

	@Override
	protected DialogCommessa creaDialog(CommessaCentrale elemento) {
		DialogCommessa dialog = new DialogCommessa(elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCommesseCentrale.getInstance().getTutteCommesse());		
	}

	@Override
	protected FiltroTabella<CommessaCentrale, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroCommesse();
	}

	@Override
	protected boolean eliminaElemento(CommessaCentrale elemento) {
		return false;
	}

	@Override
	protected Etichettatore<CommessaCentrale> creaEtichettatore() {
		return new EtichettatoreCommesse();
	}

	@Override
	protected ModificatoreValoriCelle<CommessaCentrale> creaModificatore() {
		return null;
	}

}

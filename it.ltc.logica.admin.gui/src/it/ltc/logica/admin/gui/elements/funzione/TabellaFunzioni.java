package it.ltc.logica.admin.gui.elements.funzione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerFunzioni;
import it.ltc.logica.database.model.centrale.Funzione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaFunzioni extends TabellaCRUDConFiltro<Funzione, CriteriFiltraggioFunzione> {

	public TabellaFunzioni(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN.getID();
	}

	@Override
	protected DialogApribile creaDialog(Funzione elemento) {
		DialogApribile dialog = new FunzioneDialog(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Part ID", 100, 1);
		aggiungiColonna("Permesso", 100, 2);
		aggiungiColonna("Feature", 100, 3);
		aggiungiColonna("Icona", 100, 4);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerFunzioni.getInstance().getFunzioni());		
	}

	@Override
	protected Ordinatore<Funzione> creaOrdinatore() {
		return new OrdinatoreFunzioni();
	}

	@Override
	protected FiltroTabella<Funzione, CriteriFiltraggioFunzione> creaFiltro() {
		return new FiltroFunzioni();
	}

	@Override
	protected boolean eliminaElemento(Funzione elemento) {
		//Non voglio dare la possibilità di eliminare elementi tramite la UI.
		return false;
	}

	@Override
	protected Etichettatore<Funzione> creaEtichettatore() {
		return new EtichettatoreFunzioni();
	}

	@Override
	protected ModificatoreValoriCelle<Funzione> creaModificatore() {
		return null;
	}

}

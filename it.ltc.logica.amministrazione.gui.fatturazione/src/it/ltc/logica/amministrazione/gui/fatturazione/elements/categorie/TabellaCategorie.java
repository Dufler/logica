package it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.fatturazione.dialogs.DialogCategorie;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCategorie extends TabellaCRUDConFiltro<CategoriaMerceologica, CriteriFiltroCategoria> {
	
	private final ControllerCategorieMerceologiche controller;
	
	protected Commessa commessa;

	public TabellaCategorie(Composite parent) {
		super(parent);
		controller = ControllerCategorieMerceologiche.getInstance();
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected FiltroTabella<CategoriaMerceologica, CriteriFiltroCategoria> creaFiltro() {
		return new FiltroTabellaCategorie();
	}

	@Override
	protected boolean eliminaElemento(CategoriaMerceologica elemento) {
		boolean delete = controller.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CategoriaMerceologica elemento) {
		DialogCategorie dialog = new DialogCategorie(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Codice", 100, 0);
		aggiungiColonna("Stato", 100, 4);
		aggiungiColonna("Descrizione", 100, 1);
//		aggiungiColonna("Commessa", 100, 2);
//		aggiungiColonna("Brand", 100, 3);		
	}

	@Override
	public void aggiornaContenuto() {
		if (controller != null)
			setElementi(controller.getCategorie());		
	}

	@Override
	protected Ordinatore<CategoriaMerceologica> creaOrdinatore() {
		return new OrdinatoreCategorie();
	}

	@Override
	protected Etichettatore<CategoriaMerceologica> creaEtichettatore() {
		return new EtichettatoreCategorie();
	}

	@Override
	protected ModificatoreValoriCelle<CategoriaMerceologica> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.ufficio.gui.elements.cassa;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerCasse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCasse extends TabellaCRUDConFiltro<Cassa, CriteriFiltroCassa> {
	
	private Commessa commessa;
	private ControllerCasse controller;

	public TabellaCasse(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		this.controller = new ControllerCasse(commessa);
		annullaFiltro();
	}

	@Override
	protected boolean eliminaElemento(Cassa elemento) {
		boolean delete = controller.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_PRODOTTI_ELIMINA.getID();
	}

	@Override
	protected DialogApribile creaDialog(Cassa elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			dialog = new DialogCassa(elemento, commessa);
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Cassa", 100, 0);
		aggiungiColonna("Modello", 100, 1);
		aggiungiColonna("Tipo", 100, 2);
		aggiungiColonna("Pezzi", 100, 3);
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && controller != null) {
			setElementi(controller.getCasse());
		}		
	}

	@Override
	protected Ordinatore<Cassa> creaOrdinatore() {
		return new OrdinatoreCasse();
	}

	@Override
	protected Etichettatore<Cassa> creaEtichettatore() {
		return new EtichettatoreCasse();
	}

	@Override
	protected ModificatoreValoriCelle<Cassa> creaModificatore() {
		return null;
	}

	@Override
	protected FiltroTabella<Cassa, CriteriFiltroCassa> creaFiltro() {
		return new FiltroCasse();
	}

}

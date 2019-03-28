package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.MovimentoProdotto;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.ufficio.gui.elements.prodotto.saldo.TabellaSaldoProdotto;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaMovimentiProdotto extends TabellaCRUDConFiltro<MovimentoProdotto, CriteriFiltraggioMovimentiProdotto> {
	
	protected final Commessa commessa;
	protected final Prodotto prodotto;
	protected final ControllerProdotti controller;
	
	protected TabellaSaldoProdotto tabellaSaldi;

	public TabellaMovimentiProdotto(Composite parent, Commessa commessa, Prodotto prodotto, ControllerProdotti controller) {
		super(parent);
		this.commessa = commessa;
		this.prodotto = prodotto;
		this.controller = controller;
	}
	
	public void setTabellaSaldoProdotto(TabellaSaldoProdotto tabella) {
		this.tabellaSaldi = tabella;
	}

	@Override
	protected boolean eliminaElemento(MovimentoProdotto elemento) {
		boolean delete = controller.eliminaMovimento(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}

	@Override
	protected DialogApribile creaDialog(MovimentoProdotto elemento) {
		DialogMovimentiProdotto dialog = new DialogMovimentiProdotto(elemento, commessa, prodotto, controller);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Causale", 100, 1);
		aggiungiColonna("Magazzino", 100, 2);
		aggiungiColonna("Quantit\u00E0", 100, 3);
		aggiungiColonna("Documento", 100, 4);
		aggiungiColonna("Data", 100, 5);
	}

	@Override
	public void aggiornaContenuto() {
		if (prodotto != null && controller != null)
			setElementi(controller.trovaMovimenti(prodotto));
		if (tabellaSaldi != null)
			tabellaSaldi.aggiornaContenuto();
	}

	@Override
	protected Ordinatore<MovimentoProdotto> creaOrdinatore() {
		return new OrdinatoreMovimentiProdotto();
	}

	@Override
	protected FiltroTabella<MovimentoProdotto, CriteriFiltraggioMovimentiProdotto> creaFiltro() {
		return new FiltroMovimentiProdotto();
	}

	@Override
	protected Etichettatore<MovimentoProdotto> creaEtichettatore() {
		return new EtichettatoreMovimentiProdotto();
	}

	@Override
	protected ModificatoreValoriCelle<MovimentoProdotto> creaModificatore() {
		return null;
	}

}

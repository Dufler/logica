package it.ltc.logica.ufficio.gui.elements.prodotto.saldo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.database.model.prodotto.SaldoProdotto;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaSaldoProdotto extends Tabella<SaldoProdotto> {
	
	protected Prodotto prodotto;
	protected ControllerProdotti controller;

	public TabellaSaldoProdotto(Composite parent) {
		super(parent);
	}
	
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	public void setController(ControllerProdotti controller) {
		this.controller = controller;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Magazzino", 100, 1);
		aggiungiColonna("Esistenza", 100, 2);
		aggiungiColonna("Disponibilit\u00E0", 100, 3);
		aggiungiColonna("Impegnato", 100, 4);
		aggiungiColonna("Tot. Entrati", 100, 5);
		aggiungiColonna("Tot. Usciti", 100, 6);
	}

	@Override
	public void aggiornaContenuto() {
		if (controller != null && prodotto != null)
			setElementi(controller.trovaSaldi(prodotto));
	}

	@Override
	protected Ordinatore<SaldoProdotto> creaOrdinatore() {
		return new OrdinatoreSaldoProdotto();
	}

	@Override
	protected void aggiungiListener() {
		//Non necessario		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//Non necessario		
	}

	@Override
	protected Etichettatore<SaldoProdotto> creaEtichettatore() {
		return new EtichettatoreSaldoProdotto();
	}

	@Override
	protected ModificatoreValoriCelle<SaldoProdotto> creaModificatore() {
		return null;
	}

}

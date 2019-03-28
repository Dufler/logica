package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaProdotti extends TabellaCRUDConFiltro<Prodotto, CriteriFiltraggioProdotto> {
	
	private ControllerProdotti controller;
	private CriteriFiltraggioProdotto criteri;
	private Commessa commessa;

	public TabellaProdotti(Composite parent, boolean apriConDoppioClick) {
		super(parent, STILE_SELEZIONE_SINGOLA, true, apriConDoppioClick);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		this.controller = new ControllerProdotti(commessa);
		annullaFiltro();
	}

	@Override
	protected FiltroTabella<Prodotto, CriteriFiltraggioProdotto> creaFiltro() {
		//In realtà non serve.
		return null;
	}

	@Override
	protected boolean eliminaElemento(Prodotto elemento) {
		boolean delete;
		if (commessa != null) {
			delete = controller.elimina(elemento);
		} else {
			delete = false;
		}
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
	protected DialogApribile creaDialog(Prodotto elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			dialog = new DialogProdotto(commessa, elemento, controller);
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Modello", 100, 1);
		aggiungiColonna("SKU", 100, 2);
		aggiungiColonna("Taglia", 100, 3);
		aggiungiColonna("Colore", 100, 4);
		aggiungiColonna("Categoria", 100, 5);
		aggiungiColonna("Descrizione", 100, 6);
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && criteri != null) {
			Prodotto filtro = new Prodotto();
			filtro.setCommessa(commessa.getId());
			filtro.setBarcode(criteri.getSkuModelloBarcode());
			filtro.setCodiceModello(criteri.getSkuModelloBarcode());
			filtro.setChiaveCliente(criteri.getSkuModelloBarcode());
			filtro.setStagione(criteri.getSkuModelloBarcode());
			setElementi(controller.cerca(filtro));
		}
		
	}

	@Override
	protected Ordinatore<Prodotto> creaOrdinatore() {
		return new OrdinatoreProdotti();
	}
	
	public void filtra(CriteriFiltraggioProdotto criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<Prodotto> creaEtichettatore() {
		return new EtichettatoreProdotto();
	}

	@Override
	protected ModificatoreValoriCelle<Prodotto> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.ufficio.gui.elements.prodotto;

import java.util.LinkedList;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaModelli extends TabellaCRUDConFiltro<Modello, CriteriFiltraggioModello> {
	
	private ControllerProdotti controller;
	private CriteriFiltraggioModello criteri;
	private Commessa commessa;

	public TabellaModelli(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, false);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		this.controller = new ControllerProdotti(commessa);
		annullaFiltro();
	}

	@Override
	protected FiltroTabella<Modello, CriteriFiltraggioModello> creaFiltro() {
		//In realtà non serve.
		return null;
	}

	@Override
	protected boolean eliminaElemento(Modello elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return -1;
	}

	@Override
	protected DialogApribile creaDialog(Modello elemento) {
		DialogApribile dialog = DialogMessaggio.getWarning("Non \u00E8 possibile modificare modelli", "Non \u00E8 possibile modificare modelli.\r\nSe \u00E8 necessario effettuare modifiche farle sulle anagrafiche prodotti.");
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Modello", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && criteri != null) {
			Modello filtro = new Modello();
			filtro.setCommessa(commessa.getId());
			filtro.setModello(criteri.getModello());
			setElementi(controller.trovaModelli(filtro));
		} else {
			setElementi(new LinkedList<>());
		}
	}

	@Override
	protected Ordinatore<Modello> creaOrdinatore() {
		return new OrdinatoreModelli();
	}
	
	public void filtra(CriteriFiltraggioModello criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<Modello> creaEtichettatore() {
		return new EtichettatoreModelli();
	}

	@Override
	protected ModificatoreValoriCelle<Modello> creaModificatore() {
		return null;
	}
	
}

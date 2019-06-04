package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.LinkedList;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaDatiSpedizioni extends TabellaCRUDConFiltro<DatiSpedizione, CriteriFiltroDatiSpedizione> {
	
	protected CriteriFiltroDatiSpedizione criteri;
	protected Commessa commessa;

	public TabellaDatiSpedizioni(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}

	@Override
	protected FiltroTabella<DatiSpedizione, CriteriFiltroDatiSpedizione> creaFiltro() {
		//In realtà non serve.
		return null;
	}

	@Override
	protected boolean eliminaElemento(DatiSpedizione elemento) {
		boolean delete = false;
		if (commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			delete = controller.eliminaSpedizione(elemento);
		}
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_USCITE_ELIMINA.getID();
	}

	@Override
	protected DialogApribile creaDialog(DatiSpedizione elemento) {
		DialogDatiSpedizione dialog = new DialogDatiSpedizione(commessa, elemento);
		return dialog;
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
		aggiungiColonna("Stato", 100, 8);
	}
	
	@Override
	public void filtra(CriteriFiltroDatiSpedizione criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			setElementi(controller.trovaSpedizioni(criteri.getRiferimento(), null, criteri.getStato(), criteri.getDa(), criteri.getA()));
		} else {
			//Imposto una lista vuota se non posso andare a filtrare per qualche ragione.
			setElementi(new LinkedList<>());
		}		
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

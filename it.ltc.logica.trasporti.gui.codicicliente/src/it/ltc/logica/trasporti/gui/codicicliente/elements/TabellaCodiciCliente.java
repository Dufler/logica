package it.ltc.logica.trasporti.gui.codicicliente.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.codicicliente.dialogs.DialogCodiceCliente;
import it.ltc.logica.trasporti.gui.codicicliente.wizard.NuovoCodiceClienteWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCodiciCliente extends TabellaCRUDConFiltro<CodiceClienteCorriere, CriteriFiltraggioCodiciCliente> {

	public TabellaCodiciCliente(Composite parent) {
		super(parent, STILE_SEMPLICE, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Codice", 100, 0);
		aggiungiColonna("Stato", 100, 1);
		aggiungiColonna("Corriere", 100, 2);
		aggiungiColonna("Cliente", 100, 4);
		aggiungiColonna("Descrizione", 100, 6);
	}

	@Override
	protected Ordinatore<CodiceClienteCorriere> creaOrdinatore() {
		return new OrdinatoreCodiciCliente();
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCodiciClienteCorriere.getInstance().getCodiciCliente());		
	}

	@Override
	protected FiltroTabella<CodiceClienteCorriere, CriteriFiltraggioCodiciCliente> creaFiltro() {
		return new FiltroCodiciCliente();
	}

	@Override
	protected boolean eliminaElemento(CodiceClienteCorriere elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_CODICI_CLIENTE_GESTIONE.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.TRASPORTI_CODICI_CLIENTE_GESTIONE.getID();
	}

	@Override
	protected DialogApribile creaDialog(CodiceClienteCorriere elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovoCodiceClienteWizard(), DialogWizard.WIZARD_LISTINO);
		} else {
			dialog = new DialogCodiceCliente(elemento);
		}
		return dialog;
	}

	@Override
	protected Etichettatore<CodiceClienteCorriere> creaEtichettatore() {
		return new EtichettatoreCodiciCliente();
	}

	@Override
	protected ModificatoreValoriCelle<CodiceClienteCorriere> creaModificatore() {
		return null;
	}

}

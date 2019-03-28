package it.ltc.logica.amministrazione.gui.commesse.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.commesse.dialogs.DialogCliente;
import it.ltc.logica.common.controller.sistema.ControllerClienti;
import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaClienti extends TabellaCRUDConFiltro<Cliente, CriteriFiltraggioSoloTesto> {

	public TabellaClienti(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ragione sociale", 200, 0);
		aggiungiColonna("P.IVA / C.F.", 150, 1);
	}

	@Override
	protected Ordinatore<Cliente> creaOrdinatore() {
		return new OrdinatoreClienti();
	}

	@Override
	protected FiltroTabella<Cliente, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroClienti();
	}

	@Override
	protected boolean eliminaElemento(Cliente elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}

	@Override
	protected DialogCliente creaDialog(Cliente elemento) {
		DialogCliente dialog = new DialogCliente(elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerClienti.getInstance().getClienti());		
	}

	@Override
	protected Etichettatore<Cliente> creaEtichettatore() {
		return new EtichettatoreClienti();
	}

	@Override
	protected ModificatoreValoriCelle<Cliente> creaModificatore() {
		return null;
	}
	
}

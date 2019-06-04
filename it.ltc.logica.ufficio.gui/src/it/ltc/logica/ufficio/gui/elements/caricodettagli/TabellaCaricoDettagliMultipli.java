package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCaricoDettagliMultipli extends TabellaCRUD<CaricoDettaglio> {

	//private final Commessa commessa;
	private final ControllerProdotti controllerProdotti;
	
	private final List<CaricoDettaglio> dettagli;
	
	public TabellaCaricoDettagliMultipli(Composite parent, Commessa commessa, List<CaricoDettaglio> dettagli) {
		super(parent, STILE_SEMPLICE, false, false);
		
		//this.commessa = commessa;
		this.dettagli = dettagli;
		
		//Creo il controller prodotti e lo passo agli etichettatori che lo condivideranno.
		this.controllerProdotti = new ControllerProdotti(commessa);
		aggiungiColonna("Prodotto", 100, 2);
		aggiungiColonna("Taglia", 100, 5);
		aggiungiColonna("Dichiarato", 100, 7);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected DialogApribile creaDialog(CaricoDettaglio elemento) {
		return null;
	}

	@Override
	protected void aggiungiColonne() {
		// DO NOTHING! - Lo faccio nel construttore per passargli il controller prodotti.
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(dettagli);	
	}

	@Override
	protected Ordinatore<CaricoDettaglio> creaOrdinatore() {
		return new OrdinatoreCaricoDettagli();
	}

	@Override
	protected Etichettatore<CaricoDettaglio> creaEtichettatore() {
		return new EtichettatoreCaricoDettagli(controllerProdotti);
	}

	@Override
	protected ModificatoreValoriCelle<CaricoDettaglio> creaModificatore() {
		return new ModificatoreCaricoDettagliMultipli(this);
	}

	@Override
	protected boolean eliminaElemento(CaricoDettaglio elemento) {
		return false;
	}
	
}

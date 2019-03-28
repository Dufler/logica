package it.ltc.logica.ufficio.gui.elements.fornitore;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.ingressi.ControllerFornitori;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaFornitori extends TabellaCRUDConFiltro<Fornitore, CriteriFiltraggioSoloTesto> {
	
	private FiltroFornitori filtro;
	
	protected final Commessa commessa;
	protected final ControllerFornitori controllerFornitori;

	public TabellaFornitori(Composite parent, Commessa commessa, boolean aperturaDoppioClick) {
		super(parent, STILE_SELEZIONE_SINGOLA, true, aperturaDoppioClick);
		this.commessa = commessa;
		this.controllerFornitori = new ControllerFornitori(commessa);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ragione sociale", 100, 0);
		aggiungiColonna("Codice", 200, 1);
		aggiungiColonna("Note", 200, 2);
	}

	@Override
	protected Ordinatore<Fornitore> creaOrdinatore() {
		return new OrdinatoreFornitori();
	}

	@Override
	protected FiltroTabella<Fornitore, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroFornitori();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(Fornitore elemento) {
		boolean delete = controllerFornitori.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID(); //FIXME - Aggiungere il permesso giusto e modificare.
	}

	@Override
	protected DialogFornitore creaDialog(Fornitore elemento) {
		DialogFornitore dialog = new DialogFornitore(commessa, elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && controllerFornitori != null)
			setElementi(controllerFornitori.getFornitori());
	}

	@Override
	protected Etichettatore<Fornitore> creaEtichettatore() {
		return new EtichettatoreFornitori();
	}

	@Override
	protected ModificatoreValoriCelle<Fornitore> creaModificatore() {
		return null;
	}

}

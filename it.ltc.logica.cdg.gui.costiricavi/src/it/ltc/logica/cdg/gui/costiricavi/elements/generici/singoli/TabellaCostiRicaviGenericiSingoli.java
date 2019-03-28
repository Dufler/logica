package it.ltc.logica.cdg.gui.costiricavi.elements.generici.singoli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogCostiRicaviGenericiDateValore;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiDateValore;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCostiRicaviGenericiSingoli extends TabellaCRUD<CdgCostiRicaviGenericiDateValore> {

	private final CdgCostiRicaviGenerici raggruppamento;
	
	protected MenuItem copiaValori;
	
	public TabellaCostiRicaviGenericiSingoli(Composite parent, CdgCostiRicaviGenerici raggruppamento, boolean primoInserimento) {
		super(parent);
		this.raggruppamento = raggruppamento;
	}

	@Override
	protected boolean eliminaElemento(CdgCostiRicaviGenericiDateValore elemento) {
		boolean remove = ControllerCdgCostiRicaviGenericiDateValore.getInstance().elimina(elemento);
		return remove;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogCostiRicaviGenericiDateValore creaDialog(CdgCostiRicaviGenericiDateValore elemento) {
		DialogCostiRicaviGenericiDateValore dialog = new DialogCostiRicaviGenericiDateValore(elemento, raggruppamento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data Effettiva", 100, 0);
		aggiungiColonna("Inizio", 100, 1);
		aggiungiColonna("Fine", 100, 2);
		aggiungiColonna("valore", 100, 3);
		aggiungiColonna("Sede", 100, 4);
		aggiungiColonna("Descrizione", 100, 5);
	}

	@Override
	public void aggiornaContenuto() {
		if (raggruppamento != null)
			setElementi(ControllerCdgCostiRicaviGenericiDateValore.getInstance().getDettagliPerGenerico(raggruppamento.getId()));
	}

	@Override
	protected Ordinatore<CdgCostiRicaviGenericiDateValore> creaOrdinatore() {
		return new OrdinatoreCostiRicaviGenericiSingoli();
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		boolean permessiCRU = isPermesso();
	    if (permessiCRU) {
	    	copiaValori = new MenuItem(menuPopup, SWT.PUSH);
	    	copiaValori.setText("Crea nuova copia");
	    	copiaValori.addListener(SWT.Selection, new Listener() {
		    	public void handleEvent(Event event) {
		    		CdgCostiRicaviGenericiDateValore elemento = getRigaSelezionata();
		    		if (elemento != null) {
		    			DialogCostiRicaviGenericiDateValore dialog = new DialogCostiRicaviGenericiDateValore(null, raggruppamento);
		    			dialog.copiaValoriDaEsistente(elemento);
		    			dialog.apri();
		    		}
		    	}
		    });
	    }
	}

	@Override
	protected Etichettatore<CdgCostiRicaviGenericiDateValore> creaEtichettatore() {
		return new EtichettatoreCostiRicaviGenericiSingoli();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCostiRicaviGenericiDateValore> creaModificatore() {
		return null;
	}

}

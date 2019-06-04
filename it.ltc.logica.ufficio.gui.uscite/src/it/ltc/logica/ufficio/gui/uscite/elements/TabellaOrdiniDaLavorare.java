package it.ltc.logica.ufficio.gui.uscite.elements;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;
import it.ltc.logica.ufficio.gui.elements.ordinedettagli.DialogDettagliOrdine;

public class TabellaOrdiniDaLavorare extends TabellaCheckBox<OrdineTestata> {
	
	public enum TipoOrdiniLavorabili { FINALIZZARE, ASSEGNARE, GENERARE, SPEDIRE, SPEDIRE_SELEZIONE_SINGOLA }
	
	protected MenuItem dettagli;
	
	protected final TipoOrdiniLavorabili tipo;
	protected final Commessa commessa;
	protected final ControllerOrdini controller;

	public TabellaOrdiniDaLavorare(Composite parent, Commessa commessa, TipoOrdiniLavorabili tipo) {
		super(parent, tipo == TipoOrdiniLavorabili.SPEDIRE_SELEZIONE_SINGOLA ? STILE_SELEZIONE_SINGOLA : STILE_SEMPLICE);
		this.commessa = commessa;
		this.tipo = tipo;
		this.controller = new ControllerOrdini(commessa);
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		
		dettagli = new MenuItem(menuPopup, SWT.PUSH);
		dettagli.setText("Dettagli");
		dettagli.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		visualizzaDettagli();
	    	}
	    });
	}
	
	@Override
	public void aggiornaContenuto() {
		if (commessa != null) {
			List<OrdineTestata> ordiniDaMostrare;
			switch (tipo) {
				case ASSEGNARE : ordiniDaMostrare = trovaOrdiniAssegnabili(); break;
				case FINALIZZARE : ordiniDaMostrare = trovaOrdiniFinalizzabili(); break;
				case GENERARE : ordiniDaMostrare = trovaOrdiniDaGenerare(); break;
				case SPEDIRE : ordiniDaMostrare = trovaOrdiniDaSpedire(); break;
				default : ordiniDaMostrare = null;
			}
			setElementi(ordiniDaMostrare);
		}
	}
	
	protected List<OrdineTestata> trovaOrdiniDaSpedire() {
		List<OrdineTestata> ordiniFinalizzabili = new LinkedList<>();
		ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.ELAB, null, null, null));
		//ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.INSP, null, null, null));
		return ordiniFinalizzabili;
	}
	
	protected List<OrdineTestata> trovaOrdiniDaGenerare() {
		List<OrdineTestata> ordiniFinalizzabili = new LinkedList<>();
		ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.DIIB, null, null, null));
		ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.COIB, null, null, null));
		return ordiniFinalizzabili;
	}
	
	protected List<OrdineTestata> trovaOrdiniFinalizzabili() {
		List<OrdineTestata> ordiniFinalizzabili = new LinkedList<>();
		ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.INSE, null, null, null));
		ordiniFinalizzabili.addAll(controller.trovaOrdini(null, StatiOrdine.ERRO, null, null, null));
		return ordiniFinalizzabili;
	}
	
	protected List<OrdineTestata> trovaOrdiniAssegnabili() {
		List<OrdineTestata> ordiniAssegnabili = new LinkedList<>();
		ordiniAssegnabili.addAll(controller.trovaOrdini(null, StatiOrdine.IMPO, null, null, null));
		return ordiniAssegnabili;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 1);
		aggiungiColonna("Riferimento", 100, 2);
		aggiungiColonna("Destinatario", 100, 3);
		aggiungiColonna("Ordinati", 100, 4);
		aggiungiColonna("Assegnati", 100, 5);
		aggiungiColonna("Tipo", 100, 7);
		aggiungiColonna("Stato", 100, 8);
	}

	@Override
	protected Ordinatore<OrdineTestata> creaOrdinatore() {
		return new OrdinatoreOrdineTestata();
	}
	
	private void visualizzaDettagli() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			DialogDettagliOrdine dialog = new DialogDettagliOrdine(commessa, elemento);
			int code = dialog.open();
			if (code == Window.OK) {
				aggiornaContenuto();
			}
		}
	}

	@Override
	protected Etichettatore<OrdineTestata> creaEtichettatore() {
		return new EtichettatoreOrdineTestata();
	}

	@Override
	protected ModificatoreValoriCelle<OrdineTestata> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.trasporti.gui.listini.elements.cliente;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaListinoCommessa;
import it.ltc.logica.trasporti.gui.listini.elements.cliente.voci.TabellaVociListinoClienti;
import it.ltc.logica.trasporti.gui.listini.wizards.cliente.NuovoListinoCommessaWizard;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaListiniClienti extends TabellaCRUDConFiltro<ListinoCommessa, CriteriFiltraggioSoloTesto> {
	
	private final static int idPermesso = Permessi.TRASPORTI_LISTINI_CLIENTI_CUD.getID();

	private final ControllerListiniClienti controller;
	
	private TabellaVociListinoClienti tabellaVoci;
	
	private FiltroListiniClienti filtro;
	
	public TabellaListiniClienti(Composite parent) {
		super(parent, Tabella.STILE_SELEZIONE_SINGOLA);
		
		copy.dispose();
		
		controller = ControllerListiniClienti.getInstance();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Listino", 100, 0);
		aggiungiColonna("Tipo", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		if (modify != null)	modify.setText("Propriet\u00E0");
	    
	    MenuItem export = new MenuItem(menu, SWT.PUSH);
	    export.setText("Esporta");
	    export.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogEsporta();
	    	}
	    });
	}
	
	public void setTabellaVoci(TabellaVociListinoClienti tabella) {
		tabellaVoci = tabella;
	}
	
	private void apriDialogEsporta() {
		ListinoCommessa listinoSelezionato = getRigaSelezionata();
		if (listinoSelezionato != null) {
			DialogSelezioneCartella dialog = new DialogSelezioneCartella();
			String path = dialog.open();
			if (path != null && !path.isEmpty())
				controller.esportaListino(listinoSelezionato, path);
		}
	}

	@Override
	protected Ordinatore<ListinoCommessa> creaOrdinatore() {
		return new OrdinatoreListiniClienti();
	}

	@Override
	protected FiltroTabella<ListinoCommessa, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroListiniClienti();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ListinoCommessa elemento) {
		boolean eliminazione = ControllerListiniClienti.getInstance().eliminaListino(elemento);
		if (eliminazione && tabellaVoci != null) {
			tabellaVoci.setListino(null);
		}
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_CLIENTI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(ListinoCommessa elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovoListinoCommessaWizard(), DialogWizard.WIZARD_LISTINO);
		} else {
			boolean permessoGestione = ControllerVariabiliGlobali.getInstance().haPermesso(idPermesso);
			dialog = new ProprietaListinoCommessa(elemento, permessoGestione);
		}
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		List<ListinoCommessa> listini = ControllerListiniClienti.getInstance().getListiniClientiPerTrasporti();
		setElementi(listini);
	}

	@Override
	protected Etichettatore<ListinoCommessa> creaEtichettatore() {
		return new EtichettatoreListiniClienti();
	}

	@Override
	protected ModificatoreValoriCelle<ListinoCommessa> creaModificatore() {
		return null;
	}

}

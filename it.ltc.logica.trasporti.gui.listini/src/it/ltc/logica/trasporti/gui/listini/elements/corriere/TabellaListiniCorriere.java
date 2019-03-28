package it.ltc.logica.trasporti.gui.listini.elements.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaListinoCorriere;
import it.ltc.logica.trasporti.gui.listini.elements.corriere.voci.TabellaVociListinoCorrieri;
import it.ltc.logica.trasporti.gui.listini.wizards.corriere.NuovoListinoCorriereWizard;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaListiniCorriere extends TabellaCRUDConFiltro<ListinoCorriere, CriteriFiltraggioSoloTesto> {
	
	private static final int idPermesso = Permessi.TRASPORTI_LISTINI_CORRIERI_CUD.getID();

	private final ControllerListiniCorrieri controller;
	
	private TabellaVociListinoCorrieri tabellaVoci;
	private FiltroListiniCorriere filtro;

	public TabellaListiniCorriere(Composite parent) {
		super(parent);
		
		copy.dispose();
		
		controller = ControllerListiniCorrieri.getInstance();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Tipo", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	public void setTabellaVoci(TabellaVociListinoCorrieri tabella) {
		tabellaVoci = tabella;
	}

	@Override
	protected void aggiungiMenu(Menu menu) {	    
	    MenuItem export = new MenuItem(menu, SWT.PUSH);
	    export.setText("Esporta");
	    export.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogEsporta();
	    	}
	    });
	}
	
	private void apriDialogEsporta() {
		ListinoCorriere listinoSelezionato = getRigaSelezionata();
		if (listinoSelezionato != null) {
			DialogSelezioneCartella dialog = new DialogSelezioneCartella();
			String path = dialog.open();
			if (path != null && !path.isEmpty())
				controller.esportaListino(listinoSelezionato, path);
		}
	}

	@Override
	protected Ordinatore<ListinoCorriere> creaOrdinatore() {
		return new OrdinatoreListiniCorrieri();
	}

	@Override
	protected FiltroTabella<ListinoCorriere, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroListiniCorriere();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ListinoCorriere elemento) {
		boolean eliminazione = ControllerListiniCorrieri.getInstance().eliminaListino(elemento);
		if (eliminazione && tabellaVoci != null) {
			tabellaVoci.setListino(null);
		}
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_CORRIERI_CUD.getID();
	}
	
	@Override
	protected DialogApribile creaDialog(ListinoCorriere elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovoListinoCorriereWizard(), DialogWizard.WIZARD_LISTINO);
		} else {
			boolean permessoGestione = ControllerVariabiliGlobali.getInstance().haPermesso(idPermesso);
			dialog = new ProprietaListinoCorriere(elemento, permessoGestione);
		}
		return dialog;
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerListiniCorrieri.getInstance().getListini());
	}

	@Override
	protected Etichettatore<ListinoCorriere> creaEtichettatore() {
		return new EtichettatoreListiniCorrieri();
	}

	@Override
	protected ModificatoreValoriCelle<ListinoCorriere> creaModificatore() {
		return null;
	}

}

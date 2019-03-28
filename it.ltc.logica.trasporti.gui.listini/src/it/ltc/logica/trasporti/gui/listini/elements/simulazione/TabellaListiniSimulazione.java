package it.ltc.logica.trasporti.gui.listini.elements.simulazione;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
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
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaListinoSimulazione;
import it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci.TabellaVociListinoSimulazione;
import it.ltc.logica.trasporti.gui.listini.wizards.simulazione.NuovoListinoSimulazioneWizard;
import it.ltc.logica.trasporti.gui.listini.wizards.simulazione.rincaracliente.RincaraListinoClienteWizard;
import it.ltc.logica.trasporti.gui.listini.wizards.simulazione.rincaracorriere.RincaraListinoCorriereWizard;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaListiniSimulazione extends TabellaCRUDConFiltro<ListinoSimulazione, CriteriFiltraggioSoloTesto> {
	
	private static final int idPermesso = Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID();

	private FiltroListiniSimulazione filtro;
	
	private final ListiniSimulazioneController controller;
	private TabellaVociListinoSimulazione tabellaVoci;
	
	protected MenuItem export;
	protected MenuItem rincaraDaCliente;
	protected MenuItem rincaraDaCorriere;
	
	public TabellaListiniSimulazione(Composite parent) {
		super(parent, Tabella.STILE_SELEZIONE_SINGOLA);
		
		copy.dispose();
		
		controller = ListiniSimulazioneController.getInstance();
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
	    
	    export = new MenuItem(menu, SWT.PUSH);
	    export.setText("Esporta Listino");
	    export.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		esportaListino();
	    	}
	    });
	    
	    rincaraDaCliente = new MenuItem(menu, SWT.PUSH);
	    rincaraDaCliente.setText("Rincara Listino Cliente");
	    rincaraDaCliente.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		rincaraListinoCliente();
	    	}
	    });
	    
	    rincaraDaCorriere = new MenuItem(menu, SWT.PUSH);
	    rincaraDaCorriere.setText("Rincara Listino Corriere");
	    rincaraDaCorriere.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		rincaraListinoCorriere();
	    	}
	    });
	}
	
	public void esportaListino() {
		ListinoSimulazione listinoSelezionato = getRigaSelezionata();
		if (listinoSelezionato != null) {
			DialogSelezioneCartella dialog = new DialogSelezioneCartella();
			String path = dialog.open();
			if (path != null && !path.isEmpty())
				controller.esportaListino(listinoSelezionato, path);
		}
	}

	@Override
	protected Ordinatore<ListinoSimulazione> creaOrdinatore() {
		return new OrdinatoreListiniSimulazione();
	}

	@Override
	protected FiltroTabella<ListinoSimulazione, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroListiniSimulazione();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ListinoSimulazione elemento) {
		boolean eliminazione = ListiniSimulazioneController.getInstance().eliminaListino(elemento);
		if (eliminazione && tabellaVoci != null) {
			tabellaVoci.setListino(null);
		}
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID();
	}
	
	@Override
	protected DialogApribile creaDialog(ListinoSimulazione elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovoListinoSimulazioneWizard(), DialogWizard.WIZARD_LISTINO);
		} else {
			boolean permessoGestione = ControllerVariabiliGlobali.getInstance().haPermesso(idPermesso);
			dialog = new ProprietaListinoSimulazione(elemento, permessoGestione);
		}
		return dialog;
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ListiniSimulazioneController.getInstance().getListiniDiSimulazione());
	}
	
	public void rincaraListinoCliente() {
		DialogWizard wizard = new DialogWizard(new RincaraListinoClienteWizard(), DialogWizard.WIZARD_LISTINO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			aggiornaContenuto();
		}
	}
	
	public void rincaraListinoCorriere() {
		DialogWizard wizard = new DialogWizard(new RincaraListinoCorriereWizard(), DialogWizard.WIZARD_LISTINO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			aggiornaContenuto();
		}
	}

	public void setTabella(TabellaVociListinoSimulazione tabella) {
		tabellaVoci = tabella;
	}

	@Override
	protected Etichettatore<ListinoSimulazione> creaEtichettatore() {
		return new EtichettatoreListiniSimulazione();
	}

	@Override
	protected ModificatoreValoriCelle<ListinoSimulazione> creaModificatore() {
		return null;
	}

}

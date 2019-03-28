package it.ltc.logica.trasporti.gui.elements.fatturazione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.DialogSpedizioneModel;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.EtichettatoreSpedizioniModel;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.OrdinatoreSpedizioniModel;

public abstract class TabellaFatturazione extends TabellaCRUDConFiltro<SpedizioneModel, CriteriFiltraggioSpedizioniFatturazione> {
	
	protected final ControllerListiniClienti controller;
	
	protected MenuItem modify;
	
	protected Menu colorLabelMenu;
	protected MenuItem labelCascade;
	protected MenuItem labelGreen;
	protected MenuItem labelRed;
	protected MenuItem labelYellow;
	protected MenuItem labelRemoveColor;
	
	protected FiltroSpedizioniFatturazione filtro;
	
	protected List<SpedizioneModel> elementiDaFatturare;

	public TabellaFatturazione(Composite parent, ListinoCommessa listino, boolean modify) {
		super(parent);
		
		controller = ControllerListiniClienti.getInstance();
		copy.dispose();
	}
	
	public void setElementiDaFatturare(List<SpedizioneModel> elementi) {
		elementiDaFatturare = elementi;
		setElementi(elementiDaFatturare);
	}
	
	@Override
	protected void copiaSelezione() {
		//DO NOTHING! - Non voglio che sia copiabile.
	}
	
	@Override
	protected void copiaSelezioneConRichText() {
		//DO NOTHING! - Non voglio che sia copiabile.
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
	    
	    labelCascade = new MenuItem(menu, SWT.CASCADE);
	    colorLabelMenu = new Menu(labelCascade);
	    
	    labelCascade.setText("Colora");
	    labelCascade.setMenu(colorLabelMenu);
	    
	    labelGreen = new MenuItem(colorLabelMenu, SWT.PUSH);
	    labelGreen.setText("Verde");
	    labelGreen.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		coloraRigaSelezionata(SWT.COLOR_DARK_GREEN);
	    	}
	    });
	    
	    labelRed = new MenuItem(colorLabelMenu, SWT.PUSH);
	    labelRed.setText("Rosso");
	    labelRed.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		coloraRigaSelezionata(SWT.COLOR_RED);
	    	}
	    });
	    
	    labelYellow = new MenuItem(colorLabelMenu, SWT.PUSH);
	    labelYellow.setText("Blu");
	    labelYellow.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		coloraRigaSelezionata(SWT.COLOR_DARK_BLUE);
	    	}
	    });
	    
	    labelRemoveColor = new MenuItem(colorLabelMenu, SWT.PUSH);
	    labelRemoveColor.setText("Rimuovi colore");
	    labelRemoveColor.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		coloraRigaSelezionata(SWT.COLOR_BLACK);
	    	}
	    });
	}
	
	protected void coloraRigaSelezionata(int SWTColorCode) {
		SpedizioneModel model = getRigaSelezionata();
		model.setColoreScrittaTabella(SWTColorCode);
		refresh();
	}
	
	@Override
	protected Ordinatore<SpedizioneModel> creaOrdinatore() {
		return new OrdinatoreSpedizioniModel();
	}
	
	@Override
	protected FiltroTabella<SpedizioneModel, CriteriFiltraggioSpedizioniFatturazione> creaFiltro() {
		filtro = new FiltroSpedizioniFatturazione();
		return filtro;
	}
	
	@Override
	protected boolean eliminaElemento(SpedizioneModel elemento) {
		boolean elimina = elementiDaFatturare.remove(elemento);
		if (elimina) {
			elemento.setFatturazioneAnnullata(true);
			refresh();
		}
		return elimina;
	}
	
	@Override
	protected DialogSpedizioneModel creaDialog(SpedizioneModel elemento) {
		DialogSpedizioneModel dialog = new DialogSpedizioneModel(elemento, true /*modifica*/); //FIXME - è stato fissato a true perchè non sono riuscito a passare il valore correttamente.
		return dialog;
	}
	
	@Override
	public void aggiornaContenuto() {
		refresh();
	}
	
	@Override
	protected Etichettatore<SpedizioneModel> creaEtichettatore() {
		return new EtichettatoreSpedizioniModel();
	}

	@Override
	protected ModificatoreValoriCelle<SpedizioneModel> creaModificatore() {
		return null;
	}

}

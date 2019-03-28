package it.ltc.logica.trasporti.gui.listini.elements.simulazione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarListiniSimulazione extends ToolbarCRUDConFiltro<TabellaListiniSimulazione, ListinoSimulazione, CriteriFiltraggioSoloTesto> {

	protected ToolItem esporta;
	
	public ToolbarListiniSimulazione(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void setupFiltri(Composite composite) {
		aggiungiCampoDiTestoGenericoPerFiltro(composite);		
	}

	@Override
	protected CriteriFiltraggioSoloTesto getCriteriDiFiltraggio() {
		CriteriFiltraggioSoloTesto criteri = new CriteriFiltraggioSoloTesto(filterText.getText());
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		filterText.setText("");
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
		
		esporta = new ToolItem(toolbar, SWT.NONE);
		esporta.setImage(Immagine.LISTINO_16X16.getImage());
		esporta.setToolTipText("Esporta");
		esporta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabella.esportaListino();
			}
		});
		
		Menu menuRincara = new Menu(this);
		
		MenuItem itemRincaraListinoCliente = new MenuItem(menuRincara, SWT.PUSH);
		itemRincaraListinoCliente.setText("Listino Cliente");
		itemRincaraListinoCliente.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		tabella.rincaraListinoCliente();
	    	}
	    });
		
		MenuItem itemRincaraListinoCorriere = new MenuItem(menuRincara, SWT.PUSH);
		itemRincaraListinoCorriere.setText("Listino Corriere");
		itemRincaraListinoCorriere.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		tabella.rincaraListinoCorriere();
	    	}
	    });
		
		aggiungiTastoDropDown("Rincara da ...", menuRincara);
	}

}

package it.ltc.logica.amministrazione.gui.commesse.elements.preferenze;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.ToolbarCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarPreferenzeFatturazione extends ToolbarCRUD<TabellaPreferenzeFatturazione, PreferenzeFatturazione> {

	public ToolbarPreferenzeFatturazione(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}
	
	@Override
	public void setTabella(TabellaPreferenzeFatturazione tabella) {
		super.setTabella(tabella);
		Menu menu = getMenuPreferenze();
		aggiungiTastoDropDown(menu);
	}
	
	protected void aggiungiTastoDropDown(Menu menu) {
		dropDown = new ToolItem(toolbar, SWT.DROP_DOWN, 0);
		dropDown.setImage(Immagine.CROCEVERDE_16X16.getImage());
		dropDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Rectangle rect = dropDown.getBounds ();
				Point pt = new Point (rect.x, rect.y + rect.height);
				pt = toolbar.toDisplay (pt);
				menu.setLocation (pt.x, pt.y);
				menu.setVisible (true);
			}
		});
	}

	private Menu getMenuPreferenze() {
		Menu menuAmbiti = new Menu(this);
		List<AmbitoFattura> ambitiDisponibili = tabella.getPreferenzeDisponibili();
		for (AmbitoFattura ambito : ambitiDisponibili) {
			MenuItem item = new MenuItem(menuAmbiti, SWT.PUSH);
			item.setText(ambito.getNome());
			item.addListener(SWT.Selection, new Listener() {
		    	public void handleEvent(Event event) {
		    		tabella.setAmbitoSelezionato(ambito.getId());
		    		DialogApribile dialog = tabella.creaDialog(null);
		    		int result = dialog.open();
		    		if (result == Dialog.OK) {
		    			tabella.aggiornaContenuto();
		    			item.dispose();
		    		}
		    	}
		    });
		}
		return menuAmbiti;
	}

}

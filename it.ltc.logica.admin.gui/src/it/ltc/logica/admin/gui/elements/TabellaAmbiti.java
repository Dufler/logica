package it.ltc.logica.admin.gui.elements;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;

import it.ltc.logica.admin.gui.dialogs.DialogAmbito;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaAmbiti extends Tabella<AmbitoFattura> {

	public TabellaAmbiti(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("ID", 100, 0);
		aggiungiColonna("Categoria", 100, 1);
		aggiungiColonna("Nome", 100, 2);
		aggiungiColonna("Descrizione", 100, 3);
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		MenuItem nuovo = new MenuItem(menu, SWT.PUSH);
		nuovo.setText("Nuovo");
	    nuovo.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogInserimento();
	    	}
	    });
		MenuItem modifica = new MenuItem(menu, SWT.PUSH);
	    modifica.setText("Modifica");
	    modifica.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogModifica();
	    	}
	    });	
	}
	
	private void apriDialogInserimento() {
		DialogAmbito dialog = new DialogAmbito(null);
		int result = dialog.open();
		if (result == Dialog.OK) {
			refresh();
		}
	}
	
	private void apriDialogModifica() {
		AmbitoFattura ambito = getAmbitoSelezionato();
		if (ambito != null) {
			DialogAmbito dialog = new DialogAmbito(ambito);
			int result = dialog.open();
			if (result == Dialog.OK) {
				refresh();
			}
		} else {
			DialogMessaggio.openError("Nessun Ambito selezionato", "Bisogna prima selezionare un ambito per poterlo modificare.");
		}
	}
	
	public AmbitoFattura getAmbitoSelezionato() {
		AmbitoFattura ambito;
		int selectionIndex = table.getSelectionIndex();
		if (selectionIndex != -1) {
			TableItem selectedItem = table.getItem(selectionIndex);
			ambito = (AmbitoFattura) selectedItem.getData();
		} else {
			ambito = null;
		}
		return ambito;
	}
	
	private static class EtichettatoreAmbiti extends Etichettatore<AmbitoFattura> {

		@Override
		public String getTesto(AmbitoFattura oggetto, int colonna) {
			String testo;
			switch (colonna) {
				case 0 : testo = oggetto.getId().toString(); break;
				case 1 : testo = oggetto.getCategoria(); break;
				case 2 : testo = oggetto.getNome(); break;
				case 3 : testo = oggetto.getDescrizione() != null ? oggetto.getDescrizione() : ""; break;
				default : testo = "";
			}
			return testo;
		}

		@Override
		public String getTestoTooltip(AmbitoFattura oggetto, int colonna) {
			return getTesto(oggetto, colonna);
		}

		@Override
		public Image getIcona(AmbitoFattura oggetto, int colonna) {
			return null;
		}
		
	}
	
	private static class OrdinatoreAmbiti extends Ordinatore<AmbitoFattura> {

		@Override
		protected int compare(AmbitoFattura t1, AmbitoFattura t2, int property) {
			int compare;
			switch (property) {
				case 0 : compare = t1.getId().compareTo(t2.getId()); break;
				case 1 : compare = t1.getCategoria().compareTo(t2.getCategoria()); break;
				case 2 : compare = t1.getNome().compareTo(t2.getNome()); break;
				default : compare = 0;
			}
			return compare;
		}
		
	}

	@Override
	protected Ordinatore<AmbitoFattura> creaOrdinatore() {
		return new OrdinatoreAmbiti();
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<AmbitoFattura> creaEtichettatore() {
		return new EtichettatoreAmbiti();
	}

	@Override
	protected ModificatoreValoriCelle<AmbitoFattura> creaModificatore() {
		return null;
	}

}
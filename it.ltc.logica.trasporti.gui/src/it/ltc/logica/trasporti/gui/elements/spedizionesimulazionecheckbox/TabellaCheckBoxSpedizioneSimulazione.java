package it.ltc.logica.trasporti.gui.elements.spedizionesimulazionecheckbox;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;

import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaCheckBoxSpedizioneSimulazione extends TabellaCheckBox<SpedizioneSimulazione> {
	
	/**
	 * Contiene tutti i campi possibili da visualizzare
	 */
	public static final int TIPO_COMPLETO = 0;
	
	/**
	 * Contiene solo i campi: Data, Destinatario e Riferimento
	 */
	public static final int TIPO_SEMPLICE = 1;
	
	/**
	 * Costruisce una tabella atta a visualizzare entities <code>Spedizione</code>
	 * Vanno specificati il <code>Composite</code> genitore e la tipologia di dati da visualizzare.
	 * I possibili tipi di tabella sono: completo e semplice.
	 * @param parent Il composite che conterra' la tabella.
	 * @param type Il tipo di tabella.
	 */
	public TabellaCheckBoxSpedizioneSimulazione(Composite parent, int type) {
		super(parent);
		
		//Colonne fondamentali, sono presenti in tutti i tipi
		aggiungiColonna("Data", 120, 0);
		aggiungiColonna("Destinatario", 180, 1);
				
		//Versione completa
		if (type == TIPO_COMPLETO) {
			aggiungiColonna("Colli", 40, 2);
			aggiungiColonna("Pezzi", 40, 3);
			aggiungiColonna("Contrassegno", 85, 4);
			aggiungiColonna("Giacenza", 80, 5);
		}
	}

	private void apriDialogModifica() {
		int selectionIndex = table.getSelectionIndex();
		if (selectionIndex != -1) {
			TableItem selectedItem = table.getItem(selectionIndex);
			SpedizioneSimulazione spedizione = (SpedizioneSimulazione) selectedItem.getData();
			SpedizioneSimulazioneCheckBoxDialog dialog = new SpedizioneSimulazioneCheckBoxDialog(spedizione);
			int esito = dialog.open();
			if (esito == Dialog.OK) {
				refresh();
			}
		}
	}
	
	public void mostraSpedizioni(List<SpedizioneSimulazione> input) {
		setInput(input);
	}

	@Override
	protected void aggiungiListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				apriDialogModifica();
			}
		});
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		MenuItem modify = new MenuItem(menu, SWT.PUSH);
	    modify.setText("Modifica");
	    modify.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogModifica();
	    	}
	    });
	}

	@Override
	protected void aggiungiColonne() {
		//Vengono create nel costruttore.		
	}

	@Override
	public void aggiornaContenuto() {
		//Viene gestito diversamente.		
	}

	@Override
	protected Ordinatore<SpedizioneSimulazione> creaOrdinatore() {
		return new OrdinatoreCheckBoxSpedizioneSimulazione();
	}

	@Override
	protected Etichettatore<SpedizioneSimulazione> creaEtichettatore() {
		return new EtichettatoreCheckBoxSpedizioneSimulazione();
	}

	@Override
	protected ModificatoreValoriCelle<SpedizioneSimulazione> creaModificatore() {
		return null;
	}

}

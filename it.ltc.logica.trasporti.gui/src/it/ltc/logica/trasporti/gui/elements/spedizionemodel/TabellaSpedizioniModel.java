package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

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

import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class TabellaSpedizioniModel extends Tabella<SpedizioneModel> {

	private final boolean modifica;
	
	public TabellaSpedizioniModel(Composite parent, List<ListinoCommessa> listiniClienti, List<ListinoCorriere> listiniCorrieri, List<ListinoSimulazione> listiniSimulazione, boolean modify) {
		super(parent, Tabella.STILE_TABELLE_GRANDI);
		
		aggiungiColonnaVuota();
		
		aggiungiColonna("Data", 100, 0, SWT.CENTER);
		aggiungiColonna("Destinatario", 100, 1);
		aggiungiColonna("Localit\u00E0", 100, 2);
		aggiungiColonna("Riferimento", 100, 3);
		
		aggiungiColonna("Colli", 100, 7);
		aggiungiColonna("Peso", 100, 8);
		aggiungiColonna("Volume", 100, 17);
		
		//Aggiungo la colonna con il costo originale della spedizione.
		aggiungiColonna("Costo reale", 100, 16);
		
		//aggiungo una colonna vuota come separatore
		aggiungiColonnaSeparazione();
		
		//Aggiunto tutti i listini di ricavo
		if (listiniClienti != null)	for (ListinoCommessa listino : listiniClienti) {
			aggiungiColonna(listino.getNome(), 100, new EtichettatoreSpedizioniModel(listino.getId()), 4, SWT.CENTER);
		}
		if (listiniSimulazione != null)	for (ListinoSimulazione listino : listiniSimulazione) {
			if (listino.getTipo() == AmbitoFattura.ID_SIMULAZIONI_TRASPORTI)
				aggiungiColonna( "(Sim.) " + listino.getNome(), 100, new EtichettatoreSpedizioniModel(listino.getId()), 4, SWT.CENTER);
		}
		
		//aggiungo una colonna vuota come separatore ma solo se c'è almeno un listino cliente
		if (listiniClienti != null && !listiniClienti.isEmpty())
			aggiungiColonnaSeparazione();
		
		//Aggiungo tutti i listini di costo
		if (listiniCorrieri != null)
		for (ListinoCorriere listino : listiniCorrieri) {
			aggiungiColonna(listino.getNome(), 100, new EtichettatoreSpedizioniModel(listino.getId()), 5, SWT.CENTER);
		}
		if (listiniSimulazione != null)
		for (ListinoSimulazione listino : listiniSimulazione) {
			if (listino.getTipo() == AmbitoFattura.ID_SIMULAZIONI_CORRIERI)
				aggiungiColonna("(Sim.) " + listino.getNome(), 100, new EtichettatoreSpedizioniModel(listino.getId()), 5, SWT.CENTER);
		}
		
		modifica = modify;
	}
	
	@Override
	protected void aggiungiColonne() {
		//Le colonne vengono aggiunte dinamicamente in base ai listini passati al costruttore.
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
	
	private void apriDialogModifica() {
		int selectedIndex = table.getSelectionIndex();
		if (selectedIndex != -1) {
			TableItem selectedItem = table.getItem(selectedIndex);
			SpedizioneModel selezione = (SpedizioneModel) selectedItem.getData();
			DialogSpedizioneModel dialog = new DialogSpedizioneModel(selezione, modifica);
			int esito = dialog.open();
			if (esito == Dialog.OK) {
				refresh();
			}
		}
	}

	@Override
	protected Ordinatore<SpedizioneModel> creaOrdinatore() {
		return new OrdinatoreSpedizioniModel();
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING! Viene immesso dall'esterno.
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

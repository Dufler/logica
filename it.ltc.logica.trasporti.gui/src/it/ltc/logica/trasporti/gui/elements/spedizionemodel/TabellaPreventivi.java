package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class TabellaPreventivi extends Tabella<Calcolo> {
	
	private final SpedizioneModel spedizione;

	public TabellaPreventivi(Composite parent, SpedizioneModel model) {
		super(parent);
	
		spedizione = model;
//		int pezzi = model.getSpedizione().getPezzi();
//		int colli = model.getSpedizione().getColli();
//		aggiungiColonna("Listino", 100, new EtichettatorePreventivi(pezzi, colli), 0);
//		aggiungiColonna("Totale", 100, new EtichettatorePreventivi(pezzi, colli), 1);
//		aggiungiColonna("Incidenza a Pezzo", 100, new EtichettatorePreventivi(pezzi, colli), 2);
//		aggiungiColonna("Incidenza a Collo", 100, new EtichettatorePreventivi(pezzi, colli), 3);
		
		aggiungiColonna("Listino", 100, 0);
		aggiungiColonna("Totale", 100, 1);
		aggiungiColonna("Incidenza a Pezzo", 100, 2);
		aggiungiColonna("Incidenza a Collo", 100, 3);
	}
	
	@Override
	protected void aggiungiColonne() {
		//Viene fatto quando ho le informazioni sul model.
	}

	@Override
	protected void aggiungiListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				apriDettaglio();
			}
		});
		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		MenuItem modify = new MenuItem(menu, SWT.PUSH);
	    modify.setText("Modifica");
	    modify.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDettaglio();
	    	}
	    });
	}
	
	private void apriDettaglio() {
		int selectedIndex = table.getSelectionIndex();
		if (selectedIndex != -1) {
			DialogSpedizioneModel dialog = new DialogSpedizioneModel(spedizione, false);
			dialog.open();
		}
	}

	@Override
	protected Ordinatore<Calcolo> creaOrdinatore() {
		return new OrdinatorePreventivi();
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<Calcolo> creaEtichettatore() {
		int pezzi = spedizione.getSpedizione().getPezzi();
		int colli = spedizione.getSpedizione().getColli();
		return new EtichettatorePreventivi(pezzi, colli);
	}

	@Override
	protected ModificatoreValoriCelle<Calcolo> creaModificatore() {
		// TODO Auto-generated method stub
		return null;
	}

}

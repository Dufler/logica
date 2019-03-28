package it.ltc.logica.cdg.gui.elements;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.cdg.gui.dialogs.DialogSpacchettamento;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaSpacchettamento extends Tabella<CdgPezzoEvento> {
	
	protected MenuItem insert;
	protected MenuItem modify;
	
	private CdgPezzo pezzo;
	 
	private final List<CdgPezzoEvento> spacchettamenti;

	public TabellaSpacchettamento(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
		spacchettamenti = new LinkedList<CdgPezzoEvento>();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Evento", 150, 0);
		aggiungiColonna("Fase", 100, 1);
		aggiungiColonna("Percentuale di Costo", 100, 2);
		aggiungiColonna("Percentuale di Ricavo", 100, 3);
	}
	
	public void setPezzo(CdgPezzo pezzo) {
		this.pezzo = pezzo;
	}
	
	public void setSpacchettamenti(List<CdgPezzoEvento> elementi) {
		spacchettamenti.clear();
		spacchettamenti.addAll(elementi);
		aggiornaContenuto();
	}
	
	public List<CdgPezzoEvento> getSpacchettamenti() {
		return spacchettamenti;
	}

	@Override
	protected void aggiungiListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				CdgPezzoEvento abbinamento = getRigaSelezionata();
	    		if (abbinamento != null) {
	    			apriDialog(abbinamento);
	    		}
			}
		});
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		modify = new MenuItem(menuPopup, SWT.PUSH);
		modify.setText("Modifica");
		modify.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CdgPezzoEvento abbinamento = getRigaSelezionata();
	    		if (abbinamento != null) {
	    			apriDialog(abbinamento);
	    		}
	    	}
	    });
		
		insert = new MenuItem(menuPopup, SWT.PUSH);
		insert.setText("Nuovo");
		insert.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		DialogSpacchettamento dialog = new DialogSpacchettamento(pezzo, null);
	    		CdgPezzoEvento result = dialog.apri();
	    		if (result != null) {
	    			spacchettamenti.add(result);
	    			aggiornaContenuto();
	    			dirty = true;
	    		}
	    	}
	    });
	}
	
	//@Override
	public void aggiornaContenuto() {
		setElementi(spacchettamenti);
	}
	
	protected void apriDialog(CdgPezzoEvento abbinamento) {
		DialogSpacchettamento dialog = new DialogSpacchettamento(pezzo, abbinamento);
		CdgPezzoEvento result = dialog.apri();
		if (result != null) {
			aggiornaContenuto();
			dirty = true;
		}
	}

	@Override
	protected Ordinatore<CdgPezzoEvento> creaOrdinatore() {
		return new OrdinatoreSpacchettamento();
	}

	@Override
	protected Etichettatore<CdgPezzoEvento> creaEtichettatore() {
		return new EtichettatoreSpacchettamento();
	}

	@Override
	protected ModificatoreValoriCelle<CdgPezzoEvento> creaModificatore() {
		return null;
	}

}

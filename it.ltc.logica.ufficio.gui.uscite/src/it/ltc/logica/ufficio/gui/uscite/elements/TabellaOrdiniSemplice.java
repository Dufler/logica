package it.ltc.logica.ufficio.gui.uscite.elements;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.ufficio.gui.elements.ordinedettagli.DialogDettagliOrdine;

public class TabellaOrdiniSemplice extends Tabella<OrdineTestata> {
	
	protected MenuItem dettagli;
	protected MenuItem aggiungi;
	protected MenuItem rimuovi;
	
	private Commessa commessa;
	
	private final List<OrdineTestata> ordini;

	public TabellaOrdiniSemplice(Composite parent) {
		super(parent);
		this.ordini = new LinkedList<>();
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}
	
	public void setOrdini(List<OrdineTestata> ordini) {
		this.ordini.clear();
		this.ordini.addAll(ordini);
		setElementi(ordini);
	}
	
	public List<OrdineTestata> getOrdini() {
		return ordini;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 1);
		aggiungiColonna("Riferimento", 100, 2);
		aggiungiColonna("Destinatario", 100, 3);		
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ordini);		
	}

	@Override
	protected Ordinatore<OrdineTestata> creaOrdinatore() {
		return new OrdinatoreOrdineTestata();
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		dettagli = new MenuItem(menuPopup, SWT.PUSH);
		dettagli.setText("Dettagli");
		dettagli.setImage(Immagine.LENTE_16X16.getImage());
		dettagli.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		visualizzaDettagli();
	    	}
	    });
		
		aggiungi = new MenuItem(menuPopup, SWT.PUSH);
		aggiungi.setText("Aggiungi");
		aggiungi.setImage(Immagine.CROCEVERDE_16X16.getImage());
		aggiungi.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		aggiungiOrdine();
	    	}
	    });
		
		rimuovi = new MenuItem(menuPopup, SWT.PUSH);
		rimuovi.setText("Rimuovi");
		rimuovi.setImage(Immagine.CESTINO_16X16.getImage());
		rimuovi.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		rimuoviOrdine();
	    	}
	    });
		
	}
	
	private void visualizzaDettagli() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			DialogDettagliOrdine dialog = new DialogDettagliOrdine(commessa, elemento);
			int code = dialog.open();
			if (code == Window.OK) {
				aggiornaContenuto();
			}
		}
	}
	
	protected void aggiungiOrdine() {
		DialogSelezioneOrdine dialog = new DialogSelezioneOrdine(commessa);
		OrdineTestata selezione = dialog.apri();
		if (selezione != null) {
			ordini.add(selezione);
			aggiornaContenuto();
		}
	}
	
	protected void rimuoviOrdine() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null) {
			boolean scelta = DialogMessaggio.openQuestion("Rimuovere ordine", "Vuoi veramente rimuovere l'ordine selezionato?");
			if (scelta) {
				ordini.remove(elemento);
				aggiornaContenuto();
			}			
		}
	}

	@Override
	protected Etichettatore<OrdineTestata> creaEtichettatore() {
		return new EtichettatoreOrdineTestata();
	}

	@Override
	protected ModificatoreValoriCelle<OrdineTestata> creaModificatore() {
		return null;
	}

}

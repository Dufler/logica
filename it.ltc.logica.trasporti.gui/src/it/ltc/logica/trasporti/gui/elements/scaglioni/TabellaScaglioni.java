package it.ltc.logica.trasporti.gui.elements.scaglioni;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaScaglioni extends Tabella<Scaglione> {
	
	public enum TipoScaglioni { INTERI, DECIMALI }
	
	protected MenuItem elimina;
	
	private ArrayList<Scaglione> listaScaglioni;
	
	private final TipoScaglioni tipo;

	public TabellaScaglioni(Composite parent, TipoScaglioni tipo) {
		super(parent);
		this.tipo = tipo;
		
		aggiungiColonna("Da", 100, 1);
		aggiungiColonna("A", 100, 2);
		aggiungiColonna("Valore", 100, 3);
	}

	@Override
	protected void aggiungiColonne() {
//		aggiungiColonna("Da", 100, 1);
//		aggiungiColonna("A", 100, 2);
//		aggiungiColonna("Valore", 100, 3);
	}
	
	public void aggiungiScaglione(Scaglione scaglione) {
		listaScaglioni.add(scaglione);
		aggiornaContenuto();
		dirty = true;
	}
	
	public void setScaglioni(ArrayList<Scaglione> listaScaglioni) {
		this.listaScaglioni = listaScaglioni;
		aggiornaContenuto();
	}
	
	public ArrayList<Scaglione> getScaglioni() {
		return listaScaglioni;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(listaScaglioni);		
	}

	@Override
	protected Ordinatore<Scaglione> creaOrdinatore() {
		return new OrdinatoreScaglioni();
	}
	
	@Override
	protected ModificatoreValoriCelle<Scaglione> creaModificatore() {
		return new ModificatoreScaglioni(this);
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		elimina = new MenuItem(menu, SWT.PUSH);
		elimina.setText("Elimina");
		elimina.setImage(Immagine.CROCEROSSA_16X16.getImage());
		elimina.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		int selectionIndex = table.getSelectionIndex();
	    		if (selectionIndex != -1) {
	    			listaScaglioni.remove(selectionIndex);
	    			aggiornaContenuto();
	    			dirty = true;
	    		}
	    	}
	    });	
	}

	@Override
	protected Etichettatore<Scaglione> creaEtichettatore() {
		return new EtichettatoreScaglioni(tipo);
	}

}

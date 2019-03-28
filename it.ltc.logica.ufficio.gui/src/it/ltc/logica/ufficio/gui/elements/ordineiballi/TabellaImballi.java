package it.ltc.logica.ufficio.gui.elements.ordineiballi;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaImballi extends Tabella<ImballoCollo> {
	
	private final Commessa commessa;
	private final OrdineTestata ordine;
	private final List<ImballoCollo> imballi;

	public TabellaImballi(Composite parent, Commessa commessa, OrdineTestata ordine) {
		super(parent);
		this.commessa = commessa;
		this.ordine = ordine;
		this.imballi = new LinkedList<>();
	}
	
	public List<ImballoCollo> getImballi() {
		return imballi;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Collo", 100, 1);
		aggiungiColonna("Peso", 100, 2);
		aggiungiColonna("Volume", 100, 3);
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && ordine != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			imballi.clear();
			imballi.addAll(controller.trovaImballi(ordine.getId()));
			setElementi(imballi);
		}		
	}

	@Override
	protected Ordinatore<ImballoCollo> creaOrdinatore() {
		return new OrdinatoreImballi();
	}

	@Override
	protected Etichettatore<ImballoCollo> creaEtichettatore() {
		return new EtichettatoreImballi();
	}

	@Override
	protected ModificatoreValoriCelle<ImballoCollo> creaModificatore() {
		return null;
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!
		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING!		
	}
	
	public void elaboraReportImballi() {
		//TODO
	}

}
package it.ltc.logica.cdg.gui.eventi.elements.fasi;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaFasi extends Tabella<CdgFase> {

	public TabellaFasi(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Descrizione", 200, 1);
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING!		
	}
	
	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCdgFasi.getInstance().getFasi());
	}

	@Override
	protected Ordinatore<CdgFase> creaOrdinatore() {
		return new OrdinatoreFasi();
	}

	@Override
	protected Etichettatore<CdgFase> creaEtichettatore() {
		return new EtichettatoreFasi();
	}

	@Override
	protected ModificatoreValoriCelle<CdgFase> creaModificatore() {
		return null;
	}

}

package it.ltc.logica.container.element.proprieta;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.database.dao.locali.ProprietaLogicaDao;
import it.ltc.logica.database.model.locale.ProprietaLogica;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaProprieta extends Tabella<ProprietaLogica> {
	
	protected final ProprietaLogicaDao managerProprieta;

	public TabellaProprieta(Composite parent) {
		super(parent);
		managerProprieta = new ProprietaLogicaDao();
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Chiave", 100, 0);
		aggiungiColonna("Valore", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		if (managerProprieta != null)
			setElementi(managerProprieta.trovaTutte());
	}

	@Override
	protected Ordinatore<ProprietaLogica> creaOrdinatore() {
		return new OrdinatoreProprieta();
	}

	@Override
	protected Etichettatore<ProprietaLogica> creaEtichettatore() {
		return new EtichettatoreProprieta();
	}

	@Override
	protected ModificatoreValoriCelle<ProprietaLogica> creaModificatore() {
		return new ModificatoreValoriProprieta(this);
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!		
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		// TODO - Qui potrebbe essere carino mettere un tasto del tipo "ripristina a condizioni iniziali"		
	}

}

package it.ltc.logica.ufficio.gui.uscite.elements.spedizione.risultato;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaRisultatoDatiSpedizione extends Tabella<RisultatoSalvataggioDatiSpedizione> {

	public TabellaRisultatoDatiSpedizione(Composite parent) {
		super(parent);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Riferimento", 100, 0);
		aggiungiColonna("Ordine", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING!		
	}

	@Override
	protected Ordinatore<RisultatoSalvataggioDatiSpedizione> creaOrdinatore() {
		return new OrdinatoreRisultatoDatiSpedizione();
	}

	@Override
	protected Etichettatore<RisultatoSalvataggioDatiSpedizione> creaEtichettatore() {
		return new EtichettatoreRisultatiDatiSpedizione();
	}

	@Override
	protected ModificatoreValoriCelle<RisultatoSalvataggioDatiSpedizione> creaModificatore() {
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

}

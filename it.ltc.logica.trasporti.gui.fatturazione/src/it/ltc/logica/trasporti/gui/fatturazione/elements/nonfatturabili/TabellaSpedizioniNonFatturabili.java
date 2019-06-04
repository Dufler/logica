package it.ltc.logica.trasporti.gui.fatturazione.elements.nonfatturabili;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaSpedizioniNonFatturabili extends TabellaCheckBox<Spedizione> {

	public TabellaSpedizioniNonFatturabili(Composite parent) {
		super(parent, TabellaCheckBox.STILE_SEMPLICE);		
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 120, 0);
		aggiungiColonna("Destinatario", 180, 1);
		aggiungiColonna("Riferimento", 180, 2);
		aggiungiColonna("Stato", 180, 3);
	}

	@Override
	public void aggiornaContenuto() {
		//Vengono determinate dall'esterno.		
	}

	@Override
	protected Ordinatore<Spedizione> creaOrdinatore() {
		return new OrdinatoreCheckBoxSpedizione();
	}

	@Override
	protected Etichettatore<Spedizione> creaEtichettatore() {
		return new EtichettatoreCheckBoxSpedizione();
	}

	@Override
	protected ModificatoreValoriCelle<Spedizione> creaModificatore() {
		return null;
	}

}

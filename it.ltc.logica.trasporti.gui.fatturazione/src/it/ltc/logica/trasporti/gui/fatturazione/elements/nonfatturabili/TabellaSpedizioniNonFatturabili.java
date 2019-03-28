package it.ltc.logica.trasporti.gui.fatturazione.elements.nonfatturabili;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaSpedizioniNonFatturabili extends TabellaCheckBox<Spedizione> {

	public TabellaSpedizioniNonFatturabili(Composite parent) {
		super(parent, TabellaCheckBox.STILE_SEMPLICE);		
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 120, new EtichettatoreCheckBoxSpedizione(), 0);
		aggiungiColonna("Destinatario", 180, new EtichettatoreCheckBoxSpedizione(), 1);
		aggiungiColonna("Riferimento", 180, new EtichettatoreCheckBoxSpedizione(), 2);
		aggiungiColonna("Stato", 180, new EtichettatoreCheckBoxSpedizione(), 3);
	}

	@Override
	public void aggiornaContenuto() {
		//Vengono determinate dall'esterno.		
	}

	@Override
	protected Ordinatore<Spedizione> creaOrdinatore() {
		return new OrdinatoreCheckBoxSpedizione();
	}

}

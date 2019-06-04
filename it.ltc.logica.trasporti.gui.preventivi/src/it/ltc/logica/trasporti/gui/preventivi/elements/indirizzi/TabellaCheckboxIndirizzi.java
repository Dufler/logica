package it.ltc.logica.trasporti.gui.preventivi.elements.indirizzi;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;
import it.ltc.logica.trasporti.gui.elements.indirizzosimulazione.EtichettatoreIndirizziSimulazione;
import it.ltc.logica.trasporti.gui.elements.indirizzosimulazione.OrdinatoreIndirizziSimulazione;

public class TabellaCheckboxIndirizzi extends TabellaCheckBox<IndirizzoSimulazione> {

	public TabellaCheckboxIndirizzi(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ragione Sociale", 200, 0);
		aggiungiColonna("Indirizzo", 300, 1);		
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerIndirizziSimulazione.getInstance().trovaTuttiInserimentoManuale());		
	}

	@Override
	protected Ordinatore<IndirizzoSimulazione> creaOrdinatore() {
		return new OrdinatoreIndirizziSimulazione();
	}

	@Override
	protected Etichettatore<IndirizzoSimulazione> creaEtichettatore() {
		return new EtichettatoreIndirizziSimulazione();
	}

	@Override
	protected ModificatoreValoriCelle<IndirizzoSimulazione> creaModificatore() {
		return null;
	}

}

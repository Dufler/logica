package it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.fatturazione.dialogs.DialogCoordinateBancarie;
import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;
import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCoordinate extends TabellaCRUD<CoordinateBancarie> {

	public TabellaCoordinate(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 150, 0);
		aggiungiColonna("Ente", 150, 1);
		aggiungiColonna("Coordinate", 150, 2);
	}

	@Override
	protected Ordinatore<CoordinateBancarie> creaOrdinatore() {
		return new OrdinatoreCoordinate();
	}

	@Override
	protected boolean eliminaElemento(CoordinateBancarie elemento) {
		boolean elimina = ControllerCoordinateBancarie.getInstance().elimina(elemento);
		return elimina;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CoordinateBancarie elemento) {
		DialogCoordinateBancarie dialog = new DialogCoordinateBancarie(elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCoordinateBancarie.getInstance().getCoordinate());		
	}

	@Override
	protected Etichettatore<CoordinateBancarie> creaEtichettatore() {
		return new EtichettatoreCoordinate();
	}

	@Override
	protected ModificatoreValoriCelle<CoordinateBancarie> creaModificatore() {
		return null;
	}

}

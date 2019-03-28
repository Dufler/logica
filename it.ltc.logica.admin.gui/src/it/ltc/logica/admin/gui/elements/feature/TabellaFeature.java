package it.ltc.logica.admin.gui.elements.feature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerFeature;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRU;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaFeature extends TabellaCRU<Feature> {

	public TabellaFeature(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN.getID();
	}

	@Override
	protected DialogApribile creaDialog(Feature elemento) {
		DialogApribile dialog = new FeatureDialog(elemento);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Nome", 100, 0);
		aggiungiColonna("Versione", 100, 1);
		aggiungiColonna("Feature ID", 100, 2);
		aggiungiColonna("Perspective ID", 100, 3);
		aggiungiColonna("Permesso", 100, 4);
		aggiungiColonna("Icona", 100, 5, SWT.CENTER);
		aggiungiColonna("Colore", 100, 6);
		aggiungiColonna("Posizione", 100, 7);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerFeature.getInstance().getFeatures());		
	}

	@Override
	protected Ordinatore<Feature> creaOrdinatore() {
		return new OrdinatoreFeature();
	}

	@Override
	protected Etichettatore<Feature> creaEtichettatore() {
		return new EtichettatoreFeature();
	}

	@Override
	protected ModificatoreValoriCelle<Feature> creaModificatore() {
		return null;
	}

}

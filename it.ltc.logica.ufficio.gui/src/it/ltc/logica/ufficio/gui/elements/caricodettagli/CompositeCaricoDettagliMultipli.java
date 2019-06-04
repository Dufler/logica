package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForModello;

public class CompositeCaricoDettagliMultipli extends Gruppo {
	
	public static final String title = "Righe di Carico";

	private TextForInteger textRiga;
	private TextForModello textModello;
	private ComboBox<Magazzino> comboMagazzino;
	private ComboBox<Nazione> comboMadeIn;

	public CompositeCaricoDettagliMultipli(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblRiga = new Label(this, SWT.NONE);
		lblRiga.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiga.setText("Riga: ");
		
		textRiga = new TextForInteger(this);
		textRiga.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblMagazzino = new Label(this, SWT.NONE);
		lblMagazzino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMagazzino.setText("Magazzino: ");
		
		comboMagazzino = new ComboBox<>(this);
		comboMagazzino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblMadeIn = new Label(this, SWT.NONE);
		lblMadeIn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMadeIn.setText("Made In: ");
		
		comboMadeIn = new ComboBox<>(this);
		comboMadeIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboMadeIn.setItems(ControllerNazioni.getInstance().getNazioni());
		comboMadeIn.setRequired(false);

		new SpacerLabel(this);
		
		Label lblModello = new Label(this, SWT.NONE);
		lblModello.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModello.setText("Modello: ");
		
		textModello = new TextForModello(this);
		textModello.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
	}
	
	public int getRiga() {
		return textRiga.getValue();
	}

	public void setRiga(int riga) {
		textRiga.setValue(riga);
	}

	public Modello getModello() {
		return textModello.getValue();
	}

	public void setModello(Modello modello) {
		textModello.setValue(modello);
	}

	public Magazzino getMagazzino() {
		return comboMagazzino.getSelectedValue();
	}

	public void setMagazzino(Magazzino magazzino) {
		comboMagazzino.setSelectedValue(magazzino);
	}

	public Nazione getMadeIn() {
		return comboMadeIn.getSelectedValue();
	}

	public void setMadeIn(Nazione madeIn) {
		comboMadeIn.setSelectedValue(madeIn);
	}

}

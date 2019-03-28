package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForProdotto;

public class CompositeOrdineDettaglio extends Gruppo {

	private static final String title = "Riga";
	
	private TextForProdotto textArticolo;
	private TextForInteger textRiga;
	private TextForInteger textOrdinati;
	private TextForInteger textAssegnati;
	private TextForInteger textImballati;
	private TextForDescription textNote;
	private ComboBox<Magazzino> comboMagazzino;
	
	public CompositeOrdineDettaglio(ParentValidationHandler parentValidator, Commessa commessa, Composite parent) {
		super(parentValidator, parent, title);
		if (commessa != null) {
			textArticolo.setCommessa(commessa);
			comboMagazzino.setItems(ControllerMagazzini.getInstance().getMagazzini(commessa));
		}		
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
		
		Label lblArticolo = new Label(this, SWT.NONE);
		lblArticolo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArticolo.setText("Articolo: ");
		
		textArticolo = new TextForProdotto(this);
		textArticolo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		nonUpdatableElements.add(textArticolo);

		new SpacerLabel(this);
		
		Label lblMagazzino = new Label(this, SWT.NONE);
		lblMagazzino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMagazzino.setText("Magazzino: ");
		
		comboMagazzino = new ComboBox<>(this);
		comboMagazzino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblOrdinati = new Label(this, SWT.NONE);
		lblOrdinati.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrdinati.setText("Ordinati: ");
		
		textOrdinati = new TextForInteger(this);
		textOrdinati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblAssegnati = new Label(this, SWT.NONE);
		lblAssegnati.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAssegnati.setText("Assegnati: ");
		
		textAssegnati = new TextForInteger(this);
		textAssegnati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textAssegnati.setEditable(false);
		addNonUpdatableElement(textAssegnati);

		new SpacerLabel(this);
		
		Label lblImballati = new Label(this, SWT.NONE);
		lblImballati.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImballati.setText("Imballati: ");
		
		textImballati = new TextForInteger(this);
		textImballati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textImballati.setEditable(false);
		addNonUpdatableElement(textImballati);

		new SpacerLabel(this);
		
		Label lblNote = new Label(this, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}

	public Prodotto getArticolo() {
		return textArticolo.getValue();
	}

	public void setArticolo(Prodotto articolo) {
		textArticolo.setValue(articolo);
	}

	public int getRiga() {
		return textRiga.getValue();
	}

	public void setRiga(int riga) {
		textRiga.setValue(riga);
	}

	public int getOrdinati() {
		return textOrdinati.getValue();
	}

	public void setOrdinati(int ordinati) {
		textOrdinati.setValue(ordinati);
	}
	
	public int getAssegnati() {
		return textAssegnati.getValue();
	}

	public void setAssegnati(int assegnati) {
		textAssegnati.setValue(assegnati);
	}

	public int getImballati() {
		return textImballati.getValue();
	}

	public void setImballati(int imballati) {
		textImballati.setValue(imballati);
	}

	public String getNote() {
		return textNote.getValue();
	}

	public void setNote(String note) {
		textNote.setValue(note);
	}

	public Magazzino getMagazzino() {
		return comboMagazzino.getSelectedValue();
	}

	public void setMagazzino(Magazzino magazzino) {
		comboMagazzino.setSelectedValue(magazzino);
	}
	
}

package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForProdotto;

public class CompositeDettaglioCarico extends Gruppo {

	private static final String title = "Riga";
	
	private TextForProdotto textArticolo;
	private TextForInteger textRiga;
	private TextForInteger textDichiarati;
	private TextForInteger textRiscontrati;
	private TextForDescription textNote;
	private ComboBox<Magazzino> comboMagazzino;
	private ComboBox<Nazione> comboMadeIn;
	
	public CompositeDettaglioCarico(ParentValidationHandler parentValidator, Commessa commessa, Composite parent) {
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
		
		Label lblDichiarati = new Label(this, SWT.NONE);
		lblDichiarati.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDichiarati.setText("Dichiarati: ");
		
		textDichiarati = new TextForInteger(this);
		textDichiarati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblRiscontrati = new Label(this, SWT.NONE);
		lblRiscontrati.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiscontrati.setText("Riscontrati: ");
		
		textRiscontrati = new TextForInteger(this);
		textRiscontrati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRiscontrati.setEditable(false);
		addNonUpdatableElement(textRiscontrati);

		new SpacerLabel(this);
		
		Label lblMadeIn = new Label(this, SWT.NONE);
		lblMadeIn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMadeIn.setText("Made In: ");
		
		comboMadeIn = new ComboBox<>(this);
		comboMadeIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboMadeIn.setItems(ControllerNazioni.getInstance().getNazioni());
		comboMadeIn.setRequired(false);

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

	public int getDichiarati() {
		return textDichiarati.getValue();
	}

	public void setDichiarati(int dichiarati) {
		textDichiarati.setValue(dichiarati);
	}

	public int getRiscontrati() {
		return textRiscontrati.getValue();
	}

	public void setRiscontrati(int riscontrati) {
		textRiscontrati.setValue(riscontrati);
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
	
	public Nazione getMadeIn() {
		return comboMadeIn.getSelectedValue();
	}
	
	public void setMadeIn(Nazione madeIn) {
		comboMadeIn.setSelectedValue(madeIn);
	}

}

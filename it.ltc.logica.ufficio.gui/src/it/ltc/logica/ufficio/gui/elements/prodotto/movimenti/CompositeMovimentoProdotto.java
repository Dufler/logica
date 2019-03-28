package it.ltc.logica.ufficio.gui.elements.prodotto.movimenti;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.database.model.centrale.CausaliMovimento;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeMovimentoProdotto extends Gruppo {

	private static final String title = "Dati movimento";
	
	private TextForString textArticolo;
	private TextForInteger textQuantita;
	private TextForString textDocumento;
	private ComboBox<Magazzino> comboMagazzino;
	private ComboBox<CausaliMovimento> comboCausale;
	private DateField dataMovimento;
	
	public CompositeMovimentoProdotto(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblArticolo = new Label(this, SWT.NONE);
		lblArticolo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArticolo.setText("Articolo: ");
		
		textArticolo = new TextForString(this);
		textArticolo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textArticolo.setEnabled(false);
		addNonUpdatableElement(textArticolo);
		
		new SpacerLabel(this);
		
		Label lblMagazzino = new Label(this, SWT.NONE);
		lblMagazzino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMagazzino.setText("Magazzino: ");
		
		comboMagazzino = new ComboBox<>(this);
		comboMagazzino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblCausale = new Label(this, SWT.NONE);
		lblCausale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCausale.setText("Causale: ");
		
		comboCausale = new ComboBox<>(this);
		comboCausale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCausale.setItems(CausaliMovimento.values());
		
		new SpacerLabel(this);
		
		Label lblQuantit = new Label(this, SWT.NONE);
		lblQuantit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantit.setText("Quantit\u00E0: ");
		
		textQuantita = new TextForInteger(this);
		textQuantita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblDocumento = new Label(this, SWT.NONE);
		lblDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDocumento.setText("Documento:");
		
		textDocumento = new TextForString(this);
		textDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblData = new Label(this, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data:");
		
		dataMovimento = new DateField(this);
		
		new SpacerLabel(this);
	}
	
	public void setCommessa(Commessa commessa) {
		comboMagazzino.setItems(ControllerMagazzini.getInstance().getMagazzini(commessa));
	}

	public void setArticolo(String articolo) {
		textArticolo.setText(articolo);
	}

	public int getQuantita() {
		return textQuantita.getValue();
	}

	public void setQuantita(int quantita) {
		textQuantita.setValue(quantita);
	}

	public String getRiferimentoDocumento() {
		return textDocumento.getText();
	}

	public void setRiferimentoDocumento(String riferimentoDocumento) {
		textDocumento.setValue(riferimentoDocumento);
	}

	public Magazzino getMagazzino() {
		return comboMagazzino.getSelectedValue();
	}

	public void setMagazzino(Magazzino magazzino) {
		comboMagazzino.setSelectedValue(magazzino);
	}

	public CausaliMovimento getCausale() {
		return comboCausale.getSelectedValue();
	}

	public void setCausale(CausaliMovimento causale) {
		comboCausale.setSelectedValue(causale);
	}

	public Date getDataMovimento() {
		return dataMovimento.getSimpleStartValue();
	}

	public void setDataMovimento(Date data) {
		dataMovimento.setValue(data);
	}

}

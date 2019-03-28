package it.ltc.logica.trasporti.gui.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeGiacenza extends Gruppo {
	
	public static final String title = "Giacenza";
	
	private DateField dataApertura;
	private DateField dataChiusura;
	private TextForString textLDV;
	private TextForString textLDVOriginale;

	public CompositeGiacenza(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblApertura = new Label(this, SWT.NONE);
		lblApertura.setText("Apertura: ");
		
		dataApertura = new DateField(this);
		
		new SpacerLabel(this);
		
		
		Label lblChiusura = new Label(this, SWT.NONE);
		lblChiusura.setText("Chiusura: ");
		
		dataChiusura = new DateField(this);
		
		new SpacerLabel(this);
		
		Label lblLdv = new Label(this, SWT.NONE);
		lblLdv.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLdv.setText("LDV: ");
		
		textLDV = new TextForString(this);
		textLDV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblLdvOriginale = new Label(this, SWT.NONE);
		lblLdvOriginale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLdvOriginale.setText("LDV Originale: ");
		
		textLDVOriginale = new TextForString(this);
		textLDVOriginale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
	}
	
	public Date getDataApertura() {
		return dataApertura.getValue();
	}
	
	public void setDataApertura(Date value) {
		dataApertura.setValue(value);
	}
	
	public Date getDataChiusura() {
		return dataChiusura.getValue();
	}
	
	public void setDataChiusura(Date value) {
		dataChiusura.setValue(value);
	}
	
	public String getLetteraDiVettura() {
		return textLDV.getText();
	}
	
	public void setLetteraDiVettura(String value) {
		textLDV.setText(value);
	}
	
	public String getLetteraDiVetturaOriginale() {
		return textLDVOriginale.getText();
	}
	
	public void setLetteraDiVetturaOriginale(String value) {
		textLDVOriginale.setText(value);
	}

}

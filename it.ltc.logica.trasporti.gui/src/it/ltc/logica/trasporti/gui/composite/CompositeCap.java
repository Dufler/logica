package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.Provincia;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCap extends Gruppo {
	
	private static final String titolo = "CAP";
	
	private TextForString textCAP;
	private TextForString textLocalita;
	private ComboBox<Provincia> comboProvincia;
	private Bottone buttonOre10;
	private Bottone buttonOre12;
	private Bottone buttonDisagiato;
	private Bottone buttonIsola;
	private Bottone buttonZTL;
	
	public CompositeCap(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCap = new Label(this, SWT.NONE);
		lblCap.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCap.setText("CAP:");
		
		textCAP = new TextForString(this);
		textCAP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblLocalita = new Label(this, SWT.NONE);
		lblLocalita.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLocalita.setText("Localita: ");
		
		textLocalita = new TextForString(this);
		textLocalita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblProvincia = new Label(this, SWT.NONE);
		lblProvincia.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProvincia.setText("Provincia: ");
		
		comboProvincia = new ComboBox<Provincia>(this);
		comboProvincia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboProvincia.setItems(ControllerProvince.getInstance().getProvince());
		
		new SpacerLabel(this);
		
		Label lblOre10 = new Label(this, SWT.NONE);
		lblOre10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOre10.setText("Ore 10: ");
		
		buttonOre10 = new Bottone(this, SWT.CHECK);
		new Label(this, SWT.NONE);
		
		Label lblOre12 = new Label(this, SWT.NONE);
		lblOre12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOre12.setText("Ore 12: ");
		
		buttonOre12 = new Bottone(this, SWT.CHECK);
		new Label(this, SWT.NONE);
		
		Label lblDisagiato = new Label(this, SWT.NONE);
		lblDisagiato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDisagiato.setText("Disagiato: ");
		
		buttonDisagiato = new Bottone(this, SWT.CHECK);
		new Label(this, SWT.NONE);
		
		Label lblIsolaMinore = new Label(this, SWT.NONE);
		lblIsolaMinore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIsolaMinore.setText("Isola Minore: ");
		
		buttonIsola = new Bottone(this, SWT.CHECK);
		new Label(this, SWT.NONE);
		
		Label lblZtl = new Label(this, SWT.NONE);
		lblZtl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblZtl.setText("ZTL: ");
		
		buttonZTL = new Bottone(this, SWT.CHECK);
		new Label(this, SWT.NONE);
	}
	
	public void enableIDFields(boolean enable) {
		textCAP.setEnabled(enable);
		textLocalita.setEnabled(enable);
	}
	
	public String getCAP() {
		return textCAP.getText();
	}
	
	public void setCAP(String value) {
		textCAP.setText(value);
	}
	
	public String getLocalita() {
		String localita = textLocalita.getText();
		localita = localita.toUpperCase();
		return localita;
	}
	
	public void setLocalita(String value) {
		textLocalita.setText(value);
	}
	
	public Provincia getProvincia() {
		return comboProvincia.getSelectedValue();
	}
	
	public void setProvincia(Provincia value) {
		comboProvincia.setSelectedValue(value);
	}
	
	public boolean getOre10() {
		return buttonOre10.getSelection();
	}
	
	public void setOre10(boolean value) {
		buttonOre10.setSelection(value);
	}
	
	public boolean getOre12() {
		return buttonOre12.getSelection();
	}
	
	public void setOre12(boolean value) {
		buttonOre12.setSelection(value);
	}
	
	public boolean getDisagiato() {
		return buttonDisagiato.getSelection();
	}
	
	public void setDisagiato(boolean value) {
		buttonDisagiato.setSelection(value);
	}
	
	public boolean getIsola() {
		return buttonIsola.getSelection();
	}
	
	public void setIsola(boolean value) {
		buttonIsola.setSelection(value);
	}
	
	public boolean getZTL() {
		return buttonZTL.getSelection();
	}
	
	public void setZTL(boolean value) {
		buttonZTL.setSelection(value);
	}

}

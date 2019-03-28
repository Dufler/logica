package it.ltc.logica.gui.common.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.centrale.documenti.Documento.TipoDocumento;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeDocumento extends Gruppo {

	public static final String title = "Documento";
	
	private TextForString textRiferimento;
	private ComboBox<TipoDocumento> comboTipo;
	private DateField data;
	
	public CompositeDocumento(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblRiferimento = new Label(this, SWT.NONE);
		lblRiferimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimento.setText("Riferimento: ");
		
		textRiferimento = new TextForString(this);
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<TipoDocumento>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(TipoDocumento.values());
		
		new SpacerLabel(this);
		
		Label lblData = new Label(this, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data: ");
		
		data = new DateField(this);

		new SpacerLabel(this);
	}
	
	public String getRiferimento() {
		return textRiferimento.getText();
	}

	public void setRiferimento(String riferimento) {
		textRiferimento.setText(riferimento);
	}

	public TipoDocumento getTipo() {
		return comboTipo.getSelectedValue();
	}

	public void setTipo(TipoDocumento tipo) {
		comboTipo.setSelectedValue(tipo);
	}

	public Date getDataDocumento() {
		return data.getValue();
	}

	public void setDataDocumento(Date dataDocumento) {
		data.setValue(dataDocumento);
	}

}

package it.ltc.logica.cdg.gui.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgCostiRicaviGenericiDateValore extends Gruppo {
	
	private final static String title = "Costi e Ricavi Generici: Singolo elemento";
	
	private TextForMoney textValore;
	private DateField dateEffettiva;
	private DateField dateInizio;
	private DateField dateFine;
	private ComboBox<Sede> comboSede;
	private Button btnGenerale;
	private TextForString textDescrizione;

	public CompositeCdgCostiRicaviGenericiDateValore(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblDataEffettiva = new Label(this, SWT.NONE);
		lblDataEffettiva.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataEffettiva.setText("Data Effettiva: ");
		
		dateEffettiva = new DateField(this);
		addChild(dateEffettiva);
		
		new SpacerLabel(this);
		
		Label lblInizioDilazione = new Label(this, SWT.NONE);
		lblInizioDilazione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInizioDilazione.setText("Inizio dilazione: ");
		
		dateInizio = new DateField(this);
		addChild(dateInizio);
		
		new SpacerLabel(this);
		
		Label lblFineDilazione = new Label(this, SWT.NONE);
		lblFineDilazione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFineDilazione.setText("Fine dilazione: ");
		
		dateFine = new DateField(this);
		addChild(dateFine);
		
		new SpacerLabel(this);
		
		Label lblSede = new Label(this, SWT.NONE);
		lblSede.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSede.setText("Sede: ");
		
		comboSede = new ComboBox<>(this);
		comboSede.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboSede.setItems(ControllerSedi.getInstance().getSedi());
		
		btnGenerale = new Button(this, SWT.CHECK);
		btnGenerale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboSede.setEnabled(!btnGenerale.getSelection());
				comboSede.setRequired(!btnGenerale.getSelection());
			}
		});
		btnGenerale.setText("(Tutte)");
		btnGenerale.setSelection(false);
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textValore);
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForString(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
	}

	public Double getValore() {
		return textValore.getValue();
	}

	public void setValore(Double valore) {
		textValore.setValue(valore);
	}

	public Date getDateEffettiva() {
		return dateEffettiva.getSimpleStartValue();
	}

	public void setDateEffettiva(Date date) {
		dateEffettiva.setValue(date);
	}

	public Date getDateInizio() {
		return dateInizio.getSimpleStartValue();
	}

	public void setDateInizio(Date date) {
		dateInizio.setValue(date);
	}

	public Date getDateFine() {
		return dateFine.getSimpleEndValue();
	}

	public void setDateFine(Date date) {
		dateFine.setValue(date);
	}
	
	public Sede getSede() {
		Sede sede = btnGenerale.getSelection() ? null : comboSede.getSelectedValue(); 
		return sede;
	}

	public void setSede(Integer idSede) {
		if (idSede != null) {
			Sede sede = ControllerSedi.getInstance().getSede(idSede);
			comboSede.setSelectedValue(sede);
		} else {
			btnGenerale.setSelection(true);
			comboSede.setEnabled(false);
			comboSede.setRequired(false);
		}
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String value) {
		textDescrizione.setText(value);
	}

}

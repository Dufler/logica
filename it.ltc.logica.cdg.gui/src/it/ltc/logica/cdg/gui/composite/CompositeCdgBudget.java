package it.ltc.logica.cdg.gui.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgBudget extends Gruppo {
	
	public static final String title = "Budget";
	
	private ComboBox<Commessa> comboCommessa;
	private TextForMoney textCosto;
	private TextForMoney textRicavo;
	private TextForDescription textDescrizione;
	private DateField dateInizio;
	private DateField dateFine;

	public CompositeCdgBudget(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCommessa = new Label(this, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<>(this);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		
		new SpacerLabel(this);
		
		Label lblCosto = new Label(this, SWT.NONE);
		lblCosto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCosto.setText("Costo: ");
		
		textCosto = new TextForMoney(this);
		textCosto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblRicavo = new Label(this, SWT.NONE);
		lblRicavo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRicavo.setText("Ricavo: ");
		
		textRicavo = new TextForMoney(this);
		textRicavo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblDataInizio = new Label(this, SWT.NONE);
		lblDataInizio.setText("Data inizio: ");
		
		dateInizio = new DateField(this);

		new SpacerLabel(this);
		
		Label lblDataFine = new Label(this, SWT.NONE);
		lblDataFine.setText("Data fine: ");
		
		dateFine = new DateField(this);

		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}

	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}

	public void setCommessa(Commessa commessa) {
		comboCommessa.setSelectedValue(commessa);;
	}

	public Double getCosto() {
		return textCosto.getValue();
	}

	public void setCosto(Double costo) {
		textCosto.setValue(costo);
	}

	public Double getRicavo() {
		return textRicavo.getValue();
	}

	public void setRicavo(Double ricavo) {
		textRicavo.setValue(ricavo);
	}

	public String getDescrizione() {
		return textDescrizione.getValue();
	}

	public void setDescrizione(String descrizione) {
		textDescrizione.setValue(descrizione);
	}

	public Date getInizio() {
		return dateInizio.getSimpleStartValue();
	}

	public void setDateInizio(Date inizio) {
		dateInizio.setValue(inizio);
	}

	public Date getFine() {
		return dateFine.getSimpleEndValue();
	}

	public void setFine(Date fine) {
		dateFine.setValue(fine);
	}

}

package it.ltc.logica.trasporti.gui.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerServizioSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.ServizioSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeDatiSpedizione extends Gruppo {
	
	private static final String titolo = "Dati Spedizione";
	
	private int commessa;
	
	private TextForInteger textColli;
	private TextForDouble textPeso;
	private TextForDouble textVolume;
	private TextForString textLetteraDiVettura;
	private DateField dateTime;
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<CodiceClienteCorriere> comboCodice;
	private ComboBox<ServizioSpedizione> comboServizio;
	private ComboBox<Fatturazione> comboFatturazione;

	public CompositeDatiSpedizione(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	public void setCommessa(int commessa) {
		this.commessa = commessa;
		setCodiciDisponibili();
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lbCorriere = new Label(this, SWT.NONE);
		lbCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<Corriere>(this);
		comboCorriere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCodiciDisponibili();
			}
		});
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		
		new SpacerLabel(this);
		
		Label lbCodice = new Label(this, SWT.NONE);
		lbCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbCodice.setText("Codice: ");
		
		comboCodice = new ComboBox<CodiceClienteCorriere>(this);
		comboCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		new SpacerLabel(this);
		
		Label lblServizio = new Label(this, SWT.NONE);
		lblServizio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblServizio.setText("Servizio: ");
		
		comboServizio = new ComboBox<ServizioSpedizione>(this);
		comboServizio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboServizio.setItems(ControllerServizioSpedizione.getInstance().getServizi());
		
		new SpacerLabel(this);
		
		Label lblLetteraDiVettura = new Label(this, SWT.NONE);
		lblLetteraDiVettura.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLetteraDiVettura.setText("Lettera di vettura: ");
		
		textLetteraDiVettura = new TextForString(this);
		textLetteraDiVettura.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblColli = new Label(this, SWT.NONE);
		lblColli.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColli.setText("Colli: ");
		
		textColli = new TextForInteger(this);
		textColli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblPeso = new Label(this, SWT.NONE);
		lblPeso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPeso.setText("Peso: ");
		
		textPeso = new TextForDouble(this);
		textPeso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblVolume = new Label(this, SWT.NONE);
		lblVolume.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVolume.setText("Volume: ");
		
		textVolume = new TextForDouble(this);
		textVolume.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblData = new Label(this, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data:");
		
		dateTime = new DateField(this);
		
		new SpacerLabel(this);
		
		Label lblFatturazione = new Label(this, SWT.NONE);
		lblFatturazione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFatturazione.setText("Fatturazione: ");
		
		comboFatturazione = new ComboBox<Fatturazione>(this);
		comboFatturazione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFatturazione.setItems(Fatturazione.values());
		
		new SpacerLabel(this);
	}
	
	public Corriere getCorriere() {
		return comboCorriere.getSelectedValue();
	}
	
	public void setCorriere(Corriere value) {
		comboCorriere.setSelectedValue(value);
	}
	
	public CodiceClienteCorriere getCodice() {
		return comboCodice.getSelectedValue();
	}
	
	public void setCodice(CodiceClienteCorriere value) {
		comboCodice.setSelectedValue(value);
	}
	
	public ServizioSpedizione getServizio() {
		return comboServizio.getSelectedValue();
	}
	
	public void setServizio(ServizioSpedizione value) {
		comboServizio.setSelectedValue(value);
	}
	
	public String getLetteraDiVettura() {
		return textLetteraDiVettura.getText();
	}
	
	public void setLetteraDiVettura(String value) {
		textLetteraDiVettura.setText(value);
	}
	
	public Integer getColli() {
		return textColli.getValue();
	}
	
	public void setColli(Integer value) {
		textColli.setValue(value);
	}
	
	public Double getPeso() {
		return textPeso.getValue();
	}
	
	public void setPeso(Double value) {
		textPeso.setValue(value);
	}
	
	public Double getVolume() {
		return textVolume.getValue();
	}
	
	public void setVolume(Double value) {
		textVolume.setValue(value);
	}
	
	public Date getDataSpedizione() {
		return dateTime.getValue();
	}
	
	public void setDataSpedizione(Date value) {
		if (value != null)
			dateTime.setValue(value);
	}
	
	public Fatturazione getFatturazione() {
		return comboFatturazione.getSelectedValue();
	}
	
	public void setFatturazione(Fatturazione value) {
		comboFatturazione.setSelectedValue(value);
	}
	
	public void setCodiciDisponibili() {
		Corriere corriere = comboCorriere.getSelectedValue();
		if (corriere != null) {
			comboCodice.setItems(ControllerCodiciClienteCorriere.getInstance().getCodiciPerClienteECorriere(commessa, corriere.getId()));
			comboCodice.setEnabled(true);
		} else {
			comboCodice.setEnabled(false);
		}
	}
	
	/**
	 * Setta il valore della combo a Fatturabile e la disabilita, usato in fatturazione.
	 */
	public void lockFatturabile() {
		comboFatturazione.setSelectedValue(Fatturazione.FATTURABILE);
		comboFatturazione.setEnabled(false);
	}

}

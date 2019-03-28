package it.ltc.logica.cdg.gui.composite;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa.TipoBilancioCdg;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgCostiRicaviCommessa extends Gruppo {
	
	private final static String title = "Costi e Ricavi per Commessa";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<TipoBilancioCdg> comboTipo;
	private ComboBox<CdgFase> comboFase;
	
	private DateField dataEmissione;
	
	private TextForMoney textValore;
	private TextForString textDescrizione;

	public CompositeCdgCostiRicaviCommessa(ParentValidationHandler parentValidator, Composite parent) {
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
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(TipoBilancioCdg.values());
		
		new SpacerLabel(this);
		
		Label lblFase = new Label(this, SWT.NONE);
		lblFase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFase.setText("Fase: ");
		
		comboFase = new ComboBox<>(this);
		comboFase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFase.setItems(ControllerCdgFasi.getInstance().getFasi());
		
		new SpacerLabel(this);
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblData = new Label(this, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data: ");
		
		dataEmissione = new DateField(this);
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForString(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(this);
	}

	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}

	public void setCommessa(Commessa value) {
		comboCommessa.setSelectedValue(value);
	}

	public TipoBilancioCdg getTipo() {
		return comboTipo.getSelectedValue();
	}

	public void setTipo(TipoBilancioCdg tipo) {
		comboTipo.setSelectedValue(tipo);
	}

	public CdgFase getFase() {
		return comboFase.getSelectedValue();
	}

	public void setFase(CdgFase fase) {
		comboFase.setSelectedValue(fase);
	}

	public Date getDataEmissione() {
		return dataEmissione.getSimpleStartValue();
	}

	public void setDataEmissione(Date data) {
		dataEmissione.setValue(data);
	}

	public Double getValore() {
		return textValore.getValue();
	}

	public void setValore(Double valore) {
		textValore.setValue(valore);
	}

	public String getDescrizione() {
		return textDescrizione.getValue();
	}

	public void setDescrizione(String descrizione) {
		textDescrizione.setValue(descrizione);
	}
	
	

}

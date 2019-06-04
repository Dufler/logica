package it.ltc.logica.gui.common.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerValute;
import it.ltc.logica.common.controller.trasporti.ControllerTipoContrassegno;
import it.ltc.logica.database.model.centrale.Valuta;
import it.ltc.logica.database.model.centrale.trasporti.TipoContrassegno;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeContrassegno extends Gruppo {
	
	private static final String titolo = "Contrassegno";
	
	private static final String TOOLTIP_ANNULLATO = "Indica se è stato richiesto un annullamento del contrassegno.";
	
	private TextForMoney textValore;
	private ComboBox<Valuta> comboValuta;
	private ComboBox<TipoContrassegno> comboTipo;
	private Bottone buttonAnnullato;

	public CompositeContrassegno(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, titolo);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblValore = new Label(this, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		textValore = new TextForMoney(this);
		textValore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblValuta = new Label(this, SWT.NONE);
		lblValuta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValuta.setText("Valuta: ");
		
		comboValuta = new ComboBox<Valuta>(this);
		comboValuta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboValuta.setItems(ControllerValute.getInstance().getValute());
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<TipoContrassegno>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(ControllerTipoContrassegno.getInstance().getTipiContrassegno());
		
		new SpacerLabel(this);
		
		Label lblAnnullato = new Label(this, SWT.NONE);
		lblAnnullato.setText("Annullato: ");
		lblAnnullato.setToolTipText(TOOLTIP_ANNULLATO);
		
		buttonAnnullato = new Bottone(this, SWT.CHECK);
		buttonAnnullato.setToolTipText(TOOLTIP_ANNULLATO);
		
		new SpacerLabel(this);
	}
	
	public Double getValore() {
		return textValore.getValue();
	}
	
	public void setValore(Double valore) {
		textValore.setValue(valore);
	}
	
	public Valuta getValuta() {
		return comboValuta.getSelectedValue();
	}
	
	public void setValuta(Valuta valuta) {
		comboValuta.setSelectedValue(valuta);
	}
	
	public TipoContrassegno getTipoContrassegno() {
		return comboTipo.getSelectedValue();
	}
	
	public void setTipoContrassegno(TipoContrassegno tipo) {
		comboTipo.setSelectedValue(tipo);
	}
	
	public boolean getAnnullato() {
		return buttonAnnullato.getSelection();
	}
	
	public void setAnnullato(boolean value) {
		buttonAnnullato.setSelection(value);
	}

}

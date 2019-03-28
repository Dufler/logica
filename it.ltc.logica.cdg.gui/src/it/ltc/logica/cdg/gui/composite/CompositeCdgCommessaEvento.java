package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgCommessaEvento extends Gruppo {

	private final static String title = "Durata per abbinamento Commessa-Evento";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<CdgEvento> comboEvento;
	private TextForInteger textDurata;
	
	public CompositeCdgCommessaEvento(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCommessa = new Label(this, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<Commessa>(this);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		
		new SpacerLabel(this);
		
		Label lblEvento = new Label(this, SWT.NONE);
		lblEvento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEvento.setText("Evento: ");
		
		comboEvento = new ComboBox<CdgEvento>(this);
		comboEvento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboEvento.setItems(ControllerCdgEventi.getInstance().getEventi());

		new SpacerLabel(this);
		
		Label lblDurata = new Label(this, SWT.NONE);
		lblDurata.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurata.setText("Durata: ");
		
		textDurata = new TextForInteger(this);
		textDurata.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
	}

	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}

	public void setCommessa(Commessa value) {
		comboCommessa.setSelectedValue(value);
	}

	public CdgEvento getEvento() {
		return comboEvento.getSelectedValue();
	}

	public void setEvento(CdgEvento value) {
		comboEvento.setSelectedValue(value);
	}

	public int getDurata() {
		return textDurata.getValue();
	}

	public void setDurata(int value) {
		textDurata.setValue(value);
	}

}

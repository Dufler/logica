package it.ltc.logica.cdg.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCdgPezziCostiRicavi extends Gruppo {
	
	private final static String title = "Costi e Ricavi per Pezzo";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<CategoriaMerceologica> comboCategoria;
	private TextForMoney textCosto;
	private TextForMoney textRicavo;

	public CompositeCdgPezziCostiRicavi(ParentValidationHandler parentValidator, Composite parent) {
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
		
		Label lblCategoriaMerceologica = new Label(this, SWT.NONE);
		lblCategoriaMerceologica.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategoriaMerceologica.setText("Categoria Merceologica: ");
		
		comboCategoria = new ComboBox<CategoriaMerceologica>(this);
		comboCategoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCategoria.setItems(ControllerCategorieMerceologiche.getInstance().getCategorie());

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
	}

	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}

	public void setCommessa(Commessa value) {
		comboCommessa.setSelectedValue(value);
	}

	public CategoriaMerceologica getCategoria() {
		return comboCategoria.getSelectedValue();
	}

	public void setCategoria(CategoriaMerceologica value) {
		comboCategoria.setSelectedValue(value);
	}

	public Double getCosto() {
		return textCosto.getValue();
	}

	public void setCosto(Double value) {
		textCosto.setValue(value);
	}

	public Double getRicavo() {
		return textRicavo.getValue();
	}

	public void setRicavo(Double value) {
		textRicavo.setValue(value);
	}

}

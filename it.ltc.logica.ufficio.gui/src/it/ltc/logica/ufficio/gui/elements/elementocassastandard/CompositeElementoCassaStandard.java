package it.ltc.logica.ufficio.gui.elements.elementocassastandard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeElementoCassaStandard extends Gruppo {
	
	public static final String title = "Elemento Cassa Standard";
	
	private TextForString textTaglia;
	private TextForInteger textQuantita;

	public CompositeElementoCassaStandard(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblTaglia = new Label(this, SWT.NONE);
		lblTaglia.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTaglia.setText("Taglia: ");
		
		textTaglia = new TextForString(this);
		textTaglia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblQuantit = new Label(this, SWT.NONE);
		lblQuantit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantit.setText("Quantit\u00E0:");
		
		textQuantita = new TextForInteger(this);
		textQuantita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}

	public String getTaglia() {
		return textTaglia.getValue();
	}

	public void setTaglia(String taglia) {
		textTaglia.setValue(taglia);
	}

	public int getQuantita() {
		return textQuantita.getValue();
	}

	public void setQuantita(int quantita) {
		textQuantita.setValue(quantita);
	}

}

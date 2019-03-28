package it.ltc.logica.ufficio.gui.elements.ordinetestata;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;

public class CompositeInfoImballo extends Gruppo {
	
	private static final String title = "Info Imballo";
	
	private Text textColli;
	private Text textPeso;
	private Text textVolume;

	public CompositeInfoImballo(Composite parent) {
		super(null, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblTotaleColli = new Label(this, SWT.NONE);
		lblTotaleColli.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotaleColli.setText("Colli: ");
		
		textColli = new Text(this, SWT.BORDER);
		textColli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textColli.setEditable(false);

		new SpacerLabel(this);
		
		Label lblPeso = new Label(this, SWT.NONE);
		lblPeso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPeso.setText("Peso: ");
		
		textPeso = new Text(this, SWT.BORDER);
		textPeso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPeso.setEditable(false);

		new SpacerLabel(this);
		
		Label lblVolume = new Label(this, SWT.NONE);
		lblVolume.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVolume.setText("Volume: ");
		
		textVolume = new Text(this, SWT.BORDER);
		textVolume.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textVolume.setEditable(false);

		new SpacerLabel(this);
	}
	
	public void setColli(int colli) {
		textColli.setText(Integer.toString(colli));
	}
	
	public void setPeso(double peso) {
		textPeso.setText(Double.toString(peso) + " Kg");
	}
	
	public void setVolume(double volume) {
		textVolume.setText(Double.toString(volume) + " mc");
	}

}

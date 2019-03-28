package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCoordinateBancarie extends Gruppo {
	
	private final static String title = "Coordinate bancarie";
	
	private TextForString textEnte;
	private TextForString textCoordinate;
	private TextForString textNome;

	public CompositeCoordinateBancarie(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblEnte = new Label(this, SWT.NONE);
		lblEnte.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEnte.setText("Ente: ");
		
		textEnte = new TextForString(this);
		textEnte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textEnte);
		
		new SpacerLabel(this);
		
		Label lblCoordinate = new Label(this, SWT.NONE);
		lblCoordinate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCoordinate.setText("Coordinate: ");
		
		textCoordinate = new TextForString(this);
		textCoordinate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textCoordinate);
		
		new SpacerLabel(this);
	}
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String value) {
		textNome.setText(value);
	}

	public String getEnte() {
		return textEnte.getText();
	}

	public void setEnte(String ente) {
		textEnte.setText(ente);
	}

	public String getCoordinate() {
		return textCoordinate.getText();
	}

	public void setCoordinate(String coordinate) {
		textCoordinate.setText(coordinate);
	}
	
	

}

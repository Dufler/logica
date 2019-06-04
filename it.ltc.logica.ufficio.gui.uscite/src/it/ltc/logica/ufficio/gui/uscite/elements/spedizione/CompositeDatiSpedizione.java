package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeDatiSpedizione extends Gruppo {
	
	public static final String title = "Dati spedizione";
	
	private TextForInteger textPezzi;
	private TextForInteger textColli;
	private TextForDouble textPeso;
	private TextForDouble textVolume;
	private TextForDescription textNote;
	private Label lblDataConsegna;
	private DateField dataConsegna;
//	private TabellaOrdiniSemplice tabellaOrdini;
//	private ToolbarOrdiniSemplice toolbarOrdini;

	public CompositeDatiSpedizione(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblPezzi = new Label(this, SWT.NONE);
		lblPezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezzi.setText("Pezzi: ");
		
		textPezzi = new TextForInteger(this);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		
		lblDataConsegna = new Label(this, SWT.NONE);
		lblDataConsegna.setText("Data Consegna: ");
		
		dataConsegna = new DateField(this);
		dataConsegna.setRequired(false);

		new SpacerLabel(this);
		
		Label lblNote = new Label(this, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
		
//		Label lblOrdini = new Label(this, SWT.NONE);
//		lblOrdini.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblOrdini.setText("Ordini: ");
//		
//		toolbarOrdini = new ToolbarOrdiniSemplice(this);
//		toolbarOrdini.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1));
//		
//		new SpacerLabel(this);
//		
//		tabellaOrdini = new TabellaOrdiniSemplice(this);
//		
//		new SpacerLabel(this);
//		
//		toolbarOrdini.setTabella(tabellaOrdini);		
	}

	public Integer getPezzi() {
		return textPezzi.getValue();
	}

	public void setPezzi(Integer pezzi) {
		textPezzi.setValue(pezzi);
	}

	public Integer getColli() {
		return textColli.getValue();
	}

	public void setColli(Integer colli) {
		textColli.setValue(colli);
	}

	public Double getPeso() {
		return textPeso.getValue();
	}

	public void setPeso(Double peso) {
		textPeso.setValue(peso);
	}

	public Double getVolume() {
		return textVolume.getValue();
	}

	public void setVolume(Double volume) {
		textVolume.setValue(volume);
	}
	
	public Date getDataConsegna() {
		return dataConsegna.getValue();
	}
	
	public void setDataConsegna(Date data) {
		dataConsegna.setValue(data);
	}
	
//	public void setOrdini(List<OrdineTestata> ordini) {
//		tabellaOrdini.setOrdini(ordini);
//	}
//	
//	public List<OrdineTestata> getOrdini() {
//		return tabellaOrdini.getOrdini();
//	}
	
	public void setNote(String note) {
		textNote.setValue(note);
	}
	
	public String getNote() {
		return textNote.getValue();
	}

}

package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class CompositeVoceListino extends CompositeAstrattoVoceListino {
	
	private static final String titolo = "Propriet\u00E0  - Voce di Listino";
	
	public CompositeVoceListino(ParentValidationHandler parentValidator, Composite parent, ETipoListino type) {
		super(parentValidator, parent, titolo);
		setAmbiti(type);
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblAmbito = new Label(this, SWT.NONE);
		lblAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmbito.setText("Ambito: ");
		
		comboAmbito = new ComboBox<SottoAmbitoFattura>(this);
		comboAmbito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezioneAmbito();
			}
		});
		comboAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblValoreAmbito = new Label(this, SWT.NONE);
		lblValoreAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreAmbito.setText("Valore Ambito: ");
		
		textValoreAmbito = new TextForString(this);
		textValoreAmbito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (textValoreAmbito.isEnabled()) {
					avviaInserimentoGuidato();
				}
			}
		});
		textValoreAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		textValoreAmbito.setRequired(false);
		textValoreAmbito.setEditable(false);
		textValoreAmbito.setMessage("Fare doppio click qui per l'inserimento guidato.");
		textValoreAmbito.setToolTipText(tooltipValoreAmbito);
		
		new SpacerLabel(this);
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(this);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lbl = new SpacerLabel(this);
		lbl.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
	}
	
	private void selezioneAmbito() {
		SottoAmbitoFattura ambito = comboAmbito.getSelectedValue();
		boolean valoreAmbitoInseribile = ambito != null && ambito.getValoreAmmesso();
		textValoreAmbito.setRequired(valoreAmbitoInseribile);
	}
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String nome) {
		textNome.setText(nome);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String descrizione) {
		textDescrizione.setText(descrizione);
	}
	
	public SottoAmbitoFattura getAmbito() {
		return comboAmbito.getSelectedValue();
	}
	
	public void setAmbito(SottoAmbitoFattura ambito) {
		comboAmbito.setSelectedValue(ambito);
		selezioneAmbito();
	}
	
	public String getValoreAmbito() {
		return textValoreAmbito.getText();
	}
	
	public void setValoreAmbito(String value) {
		textValoreAmbito.setText(value);
	}
	
	@Override
	public void enableElement(boolean enable) {
		super.enableElement(enable);
		textValoreAmbito.setEditable(false);
	}

}

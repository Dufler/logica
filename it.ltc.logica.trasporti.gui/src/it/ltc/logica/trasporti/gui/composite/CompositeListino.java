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

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public class CompositeListino extends CompositeAstrattoVoceListino {
	
	private ComboBox<TipoAlgoritmo> comboTipo;

	public CompositeListino(ParentValidationHandler parentValidator, Composite parent, ETipoListino type) {
		super(parentValidator, parent, "");
		setTitolo(type);
		setAmbiti(type);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<TipoAlgoritmo>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(TipoAlgoritmo.getValoriVisualizzabili());
		
		new SpacerLabel(this);
		
		Label lblAmbito = new Label(this, SWT.NONE);
		lblAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmbito.setText("Ambito: ");
		
		comboAmbito = new ComboBox<SottoAmbitoFattura>(this);
		comboAmbito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generaNome();
			}
		});
		comboAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblValoreAmbito = new Label(this, SWT.NONE);
		lblValoreAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValoreAmbito.setText("Valore Ambito:");
		lblValoreAmbito.setToolTipText(tooltipValoreAmbito);
		
		textValoreAmbito = new TextForString(this);
		textValoreAmbito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (textValoreAmbito.isEnabled()) {
					avviaInserimentoGuidato();
				}
			}
		});
		textValoreAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValoreAmbito.setToolTipText(tooltipValoreAmbito);
		textValoreAmbito.setEnabled(false);
		textValoreAmbito.setRequired(false);
		textValoreAmbito.setMessage("Fare doppio click qui per l'inserimento guidato.");
		
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
	
	public String getNome() {
		return textNome.getText();
	}
	
	public void setNome(String value) {
		textNome.setText(value);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String value) {
		textDescrizione.setText(value);
	}
	
	public TipoAlgoritmo getAlgoritmo() {
		return comboTipo.getSelectedValue();
	}
	
	public void setAlgoritmo(TipoAlgoritmo value) {
		comboTipo.setSelectedValue(value);
	}
	
	public SottoAmbitoFattura getAmbito() {
		return comboAmbito.getSelectedValue();
	}
	
	public void setAmbito(SottoAmbitoFattura value) {
		comboAmbito.setSelectedValue(value);
	}
	
	public String getValoreAmbito() {
		return textValoreAmbito.getText();
	}
	
	public void setValoreAmbito(String value) {
		textValoreAmbito.setText(value);
	}
	
	private void generaNome() {
		//Controllo sulla necessarietà di un valore per l'ambito
		SottoAmbitoFattura selezione = comboAmbito.getSelectedValue();
		boolean valoreAmbito = selezione != null && selezione.getValoreAmmesso();
		//textValoreAmbito.setEnabled(valoreAmbito);
		textValoreAmbito.setRequired(valoreAmbito);
		//Aggiunta automatica del nome per la voce
		if (textNome.getText().isEmpty() && comboAmbito.getSelectionIndex() !=-1 && comboTipo.getSelectionIndex() != -1) {
			String tipo = comboTipo.getText();
			String ambito = comboAmbito.getText();
			textNome.setText(ambito);
			textDescrizione.setText(ambito + " - " + tipo);
		}		
	}
	
	private void setTitolo(ETipoListino type) {
		setText(type.getTitolo());
	}
	
	@Override
	public void enableElement(boolean enable) {
		super.enableElement(enable);
		textValoreAmbito.setEnabled(false);
	}

}

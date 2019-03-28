package it.ltc.logica.amministrazione.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeListino extends Gruppo {
	
	public enum Tipo {
		
		LOGISTICA(5, "Propriet\u00E0 - Voce di Listino per Ciclo Logistico"),
		EXTRA(6, "Propriet\u00E0 - Voce di Listino per Extra");
		
		private final int idAmbito;
		private final String titolo;
		
		private Tipo(int idAmbito, String titolo) {
			this.idAmbito = idAmbito;
			this.titolo = titolo;
		}

		public int getIdAmbito() {
			return idAmbito;
		}

		public String getTitolo() {
			return titolo;
		}		
		
	}
	
	private static final String tooltipValoreAmbito = "Qui va inserito un valore per l'ambito. Es. il numero di giorni di franchigia per una sosta di giacenza";
	
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private ComboBox<TipoAlgoritmo> comboTipo;
	private ComboBox<SottoAmbitoFattura> comboAmbito;
	private TextForString textValoreAmbito;

	public CompositeListino(ParentValidationHandler parentValidator, Composite parent, Tipo type) {
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
		comboTipo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generaNome();
			}
		});
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
		textValoreAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValoreAmbito.setToolTipText(tooltipValoreAmbito);
		textValoreAmbito.setEnabled(false);
		textValoreAmbito.setRequired(false);
		
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
		textValoreAmbito.setEnabled(valoreAmbito);
		textValoreAmbito.setRequired(valoreAmbito);
		//Aggiunta automatica del nome per la voce
		if (textNome.getText().isEmpty() && comboAmbito.getSelectionIndex() !=-1 && comboTipo.getSelectionIndex() != -1) {
			String tipo = comboTipo.getText();
			String ambito = comboAmbito.getText();
			textNome.setText(ambito);
			textDescrizione.setText(ambito + " - " + tipo);
		}		
	}
	
	private void setAmbiti(Tipo type) {
		comboAmbito.setItems(ControllerAmbitiFatturazione.getInstance().getSottoAmbitiPerAmbito(type.getIdAmbito()));
	}
	
	private void setTitolo(Tipo type) {
		setText(type.getTitolo());
	}

}

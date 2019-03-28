package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura.Categoria;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeProprietaListinoCliente extends Gruppo {
	
	/**
	 * Specifica la tipologia di composite che verrà istanziato:
	 * - Cliente: usato per i listini effettivi di fatturazione per i clienti
	 * - Simulazione: usato per i listini di simulazione.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	public enum Tipo {
		
		CLIENTE("Propriet\u00E0 Listino Cliente", Categoria.TRASPORTI),
		SIMULAZIONE("Propriet\u00E0 Listino di Simulazione", Categoria.SIMULAZIONE);
		
		private final String titolo;
		private final Categoria categoria;
		
		private Tipo(String titolo, Categoria categoria) {
			this.titolo = titolo;
			this.categoria = categoria;
		}

		public String getTitolo() {
			return titolo;
		}

		public Categoria getCategoria() {
			return categoria;
		}
		
	}
	
	private final Tipo type;
	
	private Label lblCommessa;
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<AmbitoFattura> comboTipo;

	public CompositeProprietaListinoCliente(ParentValidationHandler parentValidator, Composite parent, Tipo type) {
		super(parentValidator, parent, type.getTitolo());
		this.type = type;
		setTipo();
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		lblCommessa = new Label(this, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<Commessa>(this);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<AmbitoFattura>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
	
	public void setNome(String nome) {
		textNome.setText(nome);
	}
	
	public String getDescrizione() {
		return textDescrizione.getText();
	}
	
	public void setDescrizione(String descrizione) {
		textDescrizione.setText(descrizione);
	}
	
	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}
	
	public void setCommessa(Commessa commessa) {
		comboCommessa.setSelectedValue(commessa);
	}
	
	public AmbitoFattura getTipo() {
		return comboTipo.getSelectedValue();
	}
	
	public void setTipo(AmbitoFattura tipo) {
		comboTipo.setSelectedValue(tipo);
	}

	private void setTipo() {
		comboTipo.setItems(ControllerAmbitiFatturazione.getInstance().getAmbitiPerCategoria(type.getCategoria()));
		if (type == Tipo.SIMULAZIONE) {
			comboCommessa.setRequired(false);
			comboCommessa.dispose();
			lblCommessa.dispose();
			layout();
		}
	}

}

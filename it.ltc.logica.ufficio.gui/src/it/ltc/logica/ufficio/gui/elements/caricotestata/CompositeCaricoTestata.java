package it.ltc.logica.ufficio.gui.elements.caricotestata;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.common.controller.ingressi.ControllerCarichiTipi;
import it.ltc.logica.common.controller.ingressi.ControllerStagioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTipo;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.database.model.centrale.ingressi.Stagione;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.fornitore.TextFornitore;

public class CompositeCaricoTestata extends Gruppo {

	private static final String title = "Carico";
	
	private TextForString textRiferimento;
	//private TextForString textStagione;
	private ComboBox<Stagione> comboStagione;
	private TextFornitore textFornitore;
	private Text textPezzi;
	private TextForDescription textNote;
	private DateField dataEffettiva;
	private DateField dataPresunta;
	private Bottone btnEccedenze;
	private Bottone btnNonDichiarati;
	private ComboBox<CaricoTipo> comboTipo;
	private ComboBox<StatiCarico> comboStato;
	
	private Commessa commessaSelezionata;
	private ControllerCarichiTipi controllerTipi;	
	
	public CompositeCaricoTestata(ParentValidationHandler parentValidator, Composite parent, Commessa commessa) {
		super(parentValidator, parent, title, commessa);
	}
	
	@Override
	public void setup(Object commessa) {
		commessaSelezionata = (Commessa) commessa;
		controllerTipi = ControllerCarichiTipi.getInstance();
//		if (commessaSelezionata != null) {
//			comboTipo.setItems(controllerTipi.getTipi(commessaSelezionata));
//			textFornitore.setCommessa(commessaSelezionata);
//		}
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblRiferimento = new Label(this, SWT.NONE);
		lblRiferimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimento.setText("Riferimento: ");
		
		textRiferimento = new TextForString(this);
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		nonUpdatableElements.add(textRiferimento);
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<CaricoTipo>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		comboTipo.setItems(controllerTipi.getTipi(commessaSelezionata));

		new SpacerLabel(this);
		
		Label lblStato = new Label(this, SWT.NONE);
		lblStato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStato.setText("Stato: ");
		
		comboStato = new ComboBox<>(this);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setItems(StatiCarico.values());
		comboStato.setEnabled(false);
		nonUpdatableElements.add(comboStato);

		new SpacerLabel(this);
		
		Label lblFornitore = new Label(this, SWT.NONE);
		lblFornitore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFornitore.setText("Fornitore: ");
		
		textFornitore = new TextFornitore(this);
		textFornitore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		textFornitore.setCommessa(commessaSelezionata);

		new SpacerLabel(this);
		
		Label lblStagione = new Label(this, SWT.NONE);
		lblStagione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStagione.setText("Stagione: ");
		
//		textStagione = new TextForString(this);
//		textStagione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		comboStagione = new ComboBox<>(this);
		comboStagione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStagione.setItems(ControllerStagioni.getInstance().getStagioni(commessaSelezionata));

		new SpacerLabel(this);
		
		Label lblArrivoEffettivo = new Label(this, SWT.NONE);
		lblArrivoEffettivo.setText("Arrivo effettivo: ");
		
		dataEffettiva = new DateField(this);

		new SpacerLabel(this);
		
		Label lblArrivoPresunto = new Label(this, SWT.NONE);
		lblArrivoPresunto.setText("Arrivo presunto: ");
		
		dataPresunta = new DateField(this);
		dataPresunta.setRequired(false);

		new SpacerLabel(this);
		
		Label lblAbilitaEccedenze = new Label(this, SWT.NONE);
		lblAbilitaEccedenze.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAbilitaEccedenze.setText("Abilita: ");
		
		btnEccedenze = new Bottone(this, SWT.CHECK);
		btnEccedenze.setText("eccedenze");

		new SpacerLabel(this);

		new SpacerLabel(this);
		
		btnNonDichiarati = new Bottone(this, SWT.CHECK);
		btnNonDichiarati.setText("pezzi non dichiarati");

		new SpacerLabel(this);
		
		Label lblTotalePezzi = new Label(this, SWT.NONE);
		lblTotalePezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotalePezzi.setText("Totale pezzi:");
		
		textPezzi = new Text(this, SWT.BORDER);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPezzi.setEditable(false);

		new SpacerLabel(this);
		
		Label lblNote = new Label(this, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}

	public String getRiferimento() {
		return textRiferimento.getText();
	}

	public void setRiferimento(String riferimento) {
		textRiferimento.setText(riferimento);
	}
	
	public StatiCarico getStato() {
		return comboStato.getSelectedValue();
	}
	
	public void setStato(StatiCarico stato) {
		comboStato.setSelectedValue(stato);
	}
	
	public CaricoTipo getTipo() {
		return comboTipo.getSelectedValue();
	}
	
	public void setTipo(CaricoTipo tipo) {
		comboTipo.setSelectedValue(tipo);
	}

//	public String getStagione() {
//		return textStagione.getText();
//	}
//
//	public void setStagione(String stagione) {
//		textStagione.setText(stagione);
//	}
	
	public Stagione getStagione() {
		return comboStagione.getSelectedValue();
	}
	
	public void setStagione(Stagione stagione) {
		comboStagione.setSelectedValue(stagione);
	}

	public void setPezzi(String pezzi) {
		textPezzi.setText(pezzi);
	}

	public String getNote() {
		return textNote.getText();
	}

	public void setNote(String note) {
		textNote.setText(note);
	}

	public Date getDataEffettiva() {
		return dataEffettiva.getValue();
	}

	public void setDataEffettiva(Date data) {
		dataEffettiva.setValue(data);
	}

	public Date getDataPresunta() {
		return dataPresunta.getValue();
	}

	public void setDataPresunta(Date data) {
		dataPresunta.setValue(data);
	}

	public boolean getEccedenze() {
		return btnEccedenze.getSelection();
	}

	public void setEccedenze(boolean eccedenze) {
		btnEccedenze.setSelection(eccedenze);
	}

	public boolean getNonDichiarati() {
		return btnNonDichiarati.getSelection();
	}

	public void setNonDichiarati(boolean nonDichiarati) {
		btnNonDichiarati.setSelection(nonDichiarati);
	}

	public Fornitore getFornitore() {
		return textFornitore.getValue();
	}

	public void setFornitore(Fornitore fornitore) {
		textFornitore.setValue(fornitore);
	}

	public Commessa getCommessaSelezionata() {
		return commessaSelezionata;
	}

}

package it.ltc.logica.ufficio.gui.elements.ordinetestata;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.common.controller.uscite.ControllerOrdineTipi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.ordini.OrdineTipo;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.SpinnerField;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.destinatario.TextForDestinatario;
import it.ltc.logica.ufficio.gui.elements.mittente.TextForMittente;

public class CompositeOrdineTestata extends Gruppo {
	
	private static final String title = "Ordine";
	
	private TextForString textRiferimento;
	private TextForString textLista;
	private TextForDestinatario textDestinatario;
	private TextForMittente textMittente;
	private Text textPezzi;
//	private Text textColli;
	private ComboBox<OrdineTipo> comboTipo;
	private ComboBox<StatiOrdine> comboStato;
	private TextForDescription textNote;
	private DateField dataOrdine;
	private DateField dataConsegna;
	private SpinnerField sliderPriorita;
	
	private final Commessa commessaSelezionata;
	private final ControllerOrdineTipi controllerTipi;

	public CompositeOrdineTestata(ParentValidationHandler parentValidator, Composite parent, Commessa commessa) {
		super(parentValidator, parent, title);
		commessaSelezionata = commessa;
		controllerTipi = ControllerOrdineTipi.getInstance();
		if (commessaSelezionata != null) {
			textDestinatario.setCommessa(commessaSelezionata);
			textMittente.setCommessa(commessaSelezionata);
			comboTipo.setItems(controllerTipi.getTipi(commessaSelezionata));
		}
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
		
		Label lblLista = new Label(this, SWT.NONE);
		lblLista.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLista.setText("Lista: ");
		
		textLista = new TextForString(this);
		textLista.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textLista.setMessage("Generazione automatica");
		textLista.setEditable(false);
		textLista.setRequired(false);
		nonUpdatableElements.add(textLista);
		
		new SpacerLabel(this);
		
		Label lblDestinatario = new Label(this, SWT.NONE);
		lblDestinatario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDestinatario.setText("Destinatario: ");
		
		textDestinatario = new TextForDestinatario(this);
		textDestinatario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblMittente = new Label(this, SWT.NONE);
		lblMittente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMittente.setText("Mittente: ");
		
		textMittente = new TextForMittente(this);
		textMittente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblTipo = new Label(this, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo: ");
		
		comboTipo = new ComboBox<OrdineTipo>(this);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblStato = new Label(this, SWT.NONE);
		lblStato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStato.setText("Stato: ");
		
		comboStato = new ComboBox<>(this);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setItems(StatiOrdine.values());
		comboStato.setEnabled(false);
		nonUpdatableElements.add(comboStato);

		new SpacerLabel(this);
		
		Label lblTotalePezzi = new Label(this, SWT.NONE);
		lblTotalePezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotalePezzi.setText("Totale pezzi:");
		
		textPezzi = new Text(this, SWT.BORDER);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPezzi.setEditable(false);

		new SpacerLabel(this);
		
//		Label lblTotaleColli = new Label(this, SWT.NONE);
//		lblTotaleColli.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblTotaleColli.setText("Totale colli:");
//		
//		textColli = new Text(this, SWT.BORDER);
//		textColli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//		textColli.setEditable(false);
//
//		new SpacerLabel(this);
		
		Label lblDataOrdine = new Label(this, SWT.NONE);
		lblDataOrdine.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataOrdine.setText("Data ordine:");
		
		dataOrdine = new DateField(this);

		new SpacerLabel(this);
		
		Label lblDataConsegna = new Label(this, SWT.NONE);
		lblDataConsegna.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataConsegna.setText("Data consegna:");
		
		dataConsegna = new DateField(this, false);
		dataConsegna.setRequired(false);

		new SpacerLabel(this);
		
		Label lblPriorita = new Label(this, SWT.NONE);
		lblPriorita.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPriorita.setText("Priorit\u00E0:");
		
		sliderPriorita = new SpinnerField(this, 1, 10);
		
		new SpacerLabel(this);
		
		Label lblNote = new Label(this, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(this);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}

	public String getRiferimento() {
		return textRiferimento.getValue();
	}

	public void setRiferimento(String riferimento) {
		textRiferimento.setValue(riferimento);
	}

	public String getLista() {
		return textLista.getValue();
	}

	public void setLista(String lista) {
		textLista.setValue(lista);
	}

	public Indirizzo getDestinatario() {
		return textDestinatario.getValue();
	}

	public void setDestinatario(Indirizzo destinatario) {
		textDestinatario.setValue(destinatario);
	}

	public Indirizzo getMittente() {
		return textMittente.getValue();
	}

	public void setMittente(Indirizzo mittente) {
		textMittente.setValue(mittente);
	}

	public void setPezzi(int pezziOrdinati, int pezziImballati) {
		textPezzi.setText(pezziImballati + "/" + pezziOrdinati);
	}
	
//	public void setColli(int colli) {
//		textColli.setText(Integer.toString(colli));
//	}

	public OrdineTipo getTipo() {
		return comboTipo.getSelectedValue();
	}

	public void setTipo(OrdineTipo tipo) {
		comboTipo.setSelectedValue(tipo);
	}

	public StatiOrdine getStato() {
		return comboStato.getSelectedValue();
	}

	public void setStato(StatiOrdine stato) {
		comboStato.setSelectedValue(stato);
	}

	public String getNote() {
		return textNote.getValue();
	}

	public void settNote(String note) {
		textNote.setValue(note);
	}

	public Date getDataOrdine() {
		return dataOrdine.getSimpleStartValue();
	}

	public void setDataOrdine(Date data) {
		dataOrdine.setValue(data);
	}

	public Date getDataConsegna() {
		return dataConsegna.getSimpleStartValue();
	}

	public void setDataConsegna(Date data) {
		dataConsegna.setValue(data);
	}

	public Integer getPriorita() {
		return sliderPriorita.getValue();
	}

	public void setPriorita(Integer priorita) {
		sliderPriorita.setValue(priorita);
	}

}

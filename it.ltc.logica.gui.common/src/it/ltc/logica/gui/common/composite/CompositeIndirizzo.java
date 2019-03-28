package it.ltc.logica.gui.common.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.common.controller.trasporti.ControllerProvince;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.indirizzi.Provincia;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.composite.GruppoSemplice;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

/**
 * Composite molto utilizzata in diversi punti del programma.<br>
 * Alcuni esempi classici dentro le dialog model sono:<br>
 * <br>
 * <b>Impostazione campi composite:</b><br>
 * <br>
 * compositeIndirizzo.setRagioneSociale(valore.getRagioneSociale());<br>
 * compositeIndirizzo.setIndirizzo(valore.getIndirizzo());<br>
 * compositeIndirizzo.setCap(valore.getCap());<br>
 * compositeIndirizzo.setLocalita(valore.getLocalita());<br>
 * compositeIndirizzo.setProvincia(ControllerProvince.getInstance().getProvincia(valore.getProvincia()));<br>
 * compositeIndirizzo.setNazione(ControllerNazioni.getInstance().getNazione(valore.getNazione()));<br>
 * compositeIndirizzo.setTelefono(valore.getTelefono());<br>
 * compositeIndirizzo.setEmail(valore.getEmail());<br>
 * <br>
 * <b>Impostazione valori model:</b><br>
 * <br>
 * valore.setRagioneSociale(compositeIndirizzo.getRagioneSociale());<br>
 * valore.setIndirizzo(compositeIndirizzo.getIndirizzo());<br>
 * valore.setCap(compositeIndirizzo.getCap());<br>
 * valore.setLocalita(compositeIndirizzo.getLocalita());<br>
 * valore.setProvincia(compositeIndirizzo.getProvincia().getSigla());<br>
 * valore.setNazione(compositeIndirizzo.getNazione().getCodiceIsoTre());<br>
 * valore.setTelefono(compositeIndirizzo.getTelefono());<br>
 * valore.setEmail(compositeIndirizzo.getEmail());<br>
 * <br>
 * @author Damiano
 *
 */
public class CompositeIndirizzo extends Gruppo {
	
	/**
	 * Definisce le tipologie di indirizzo.
	 * Questo incide sul layout e sul titolo della composite.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	public enum TipoIndirizzo {
		
		DESTINATARIO("Indirizzo del Destinatario"),
		MITTENTE("Indirizzo del Mittente"),
		DEFAULT("Indirizzo"),
		SEMPLICE("Indirizzo");
		
		private final String titolo;
		
		private TipoIndirizzo(String titolo) {
			this.titolo = titolo;
		}
		
		public String getTitolo() {
			return titolo;
		}
	}
	
	public static final String TOOLTIP_GDO = "Specifica se l'indirizzo è una grande catena di distribuzione (es. centro commerciale, interporto,...) oppure no.";
	public static final String TOOLTIP_PIANO = "Specifica se l'indirizzo si trova ad un piano elevato oppure no.";
	public static final String TOOLTIP_PRIVATO = "Specifica se l'indirizzo è di un privato oppure no.";
	
	private TextForString textRagioneSociale;
	private TextForString textTelefono;
	private TextForString textMail;
	private TextForString textIndirizzo;
	private TextForString textLocalita;
	private TextForString textCap;
	private ComboBox<Provincia> comboProvincia;
	private ComboBox<Nazione> comboNazione;
	
	private Bottone btnPrivato;
	private Bottone btnPiano;
	private Bottone btnGDO;
	private Bottone btnAppuntamento;
	
	private GruppoParticolarita compositeParticolarita;
	
	public CompositeIndirizzo(ParentValidationHandler parentValidator, Composite parent, TipoIndirizzo tipo) {
		super(parentValidator, parent, tipo.getTitolo());
		
		if (tipo == TipoIndirizzo.SEMPLICE) {
			//Rimuovo i bottoni dalla validazione
			children.remove(btnPrivato);
			children.remove(btnPiano);
			children.remove(btnGDO);
			children.remove(btnAppuntamento);
			//Elimino il composite che contiene i bottoni e ridisegno
			compositeParticolarita.dispose();
			layout();
		}
	}
	
	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(1, false));
		
		GruppoDati dati = new GruppoDati(this, this);
		dati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		compositeParticolarita = new GruppoParticolarita(this, this);
		compositeParticolarita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}
	
	private class GruppoDati extends GruppoSemplice {

		public GruppoDati(ParentValidationHandler parentValidator, Composite parent) {
			super(parentValidator, parent);
		}

		@Override
		public void aggiungiElementiGrafici() {
			setLayout(new GridLayout(3, false));
			
			Label lblRagioneSociale = new Label(this, SWT.NONE);
			lblRagioneSociale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblRagioneSociale.setText("Ragione sociale: ");
			
			textRagioneSociale = new TextForString(this);
			textRagioneSociale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			addChild(textRagioneSociale);
			
			new SpacerLabel(this);
			
			Label lblTelefono = new Label(this, SWT.NONE);
			lblTelefono.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblTelefono.setText("Telefono: ");
			
			textTelefono = new TextForString(this, TextForString.REGEX_TELEFONO);
			textTelefono.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			textTelefono.setRequired(false);
			addChild(textTelefono);

			new SpacerLabel(this);
			
			Label lblEmail = new Label(this, SWT.NONE);
			lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblEmail.setText("E-Mail: ");
			
			textMail = new TextForString(this, TextForString.REGEX_EMAIL);
			textMail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			textMail.setRequired(false);
			addChild(textMail);

			new SpacerLabel(this);
			
			Label lblIndirizzo = new Label(this, SWT.NONE);
			lblIndirizzo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblIndirizzo.setText("Indirizzo: ");
			
			textIndirizzo = new TextForString(this);
			textIndirizzo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			addChild(textIndirizzo);
			
			new SpacerLabel(this);
			
			Label lblCap = new Label(this, SWT.NONE);
			lblCap.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblCap.setText("CAP: ");
			
			textCap = new TextForString(this);
			textCap.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			addChild(textCap);
			
			new SpacerLabel(this);
			
			Label lblLocalit = new Label(this, SWT.NONE);
			lblLocalit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblLocalit.setText("Localit\u00E0: ");
			
			textLocalita = new TextForString(this);
			textLocalita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			addChild(textLocalita);
			
			new SpacerLabel(this);
			
			Label lblProvincia = new Label(this, SWT.NONE);
			lblProvincia.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblProvincia.setText("Provincia: ");
			
			comboProvincia = new ComboBox<Provincia>(this);
			comboProvincia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			comboProvincia.setItems(ControllerProvince.getInstance().getProvince());
			addChild(comboProvincia);
			
			new SpacerLabel(this);
			
			Label lblNazione = new Label(this, SWT.NONE);
			lblNazione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNazione.setText("Nazione: ");
			
			comboNazione = new ComboBox<Nazione>(this);
			comboNazione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			comboNazione.setItems(ControllerNazioni.getInstance().getNazioni());
			comboNazione.setSelectedValue(ControllerNazioni.getInstance().getDefault());
			addChild(comboNazione);
			
			new SpacerLabel(this);
		}
		
	}
	
	private class GruppoParticolarita extends GruppoSemplice {

		public GruppoParticolarita(ParentValidationHandler parentValidator, Composite parent) {
			super(parentValidator, parent);
			validate();
		}

		@Override
		public void aggiungiElementiGrafici() {
			setLayout(new GridLayout(3, false));
			
			Label lblPrivato = new Label(this, SWT.NONE);
			lblPrivato.setToolTipText(TOOLTIP_PRIVATO);
			lblPrivato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblPrivato.setText("Privato: ");
			
			btnPrivato = new Bottone(this, SWT.CHECK);
			btnPrivato.setToolTipText(TOOLTIP_PRIVATO);
			addChild(btnPrivato);
			
			new SpacerLabel(this, SWT.NONE, SpacerLabel.SHORT);
			
			Label lblPiano = new Label(this, SWT.NONE);
			lblPiano.setToolTipText(TOOLTIP_PIANO);
			lblPiano.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblPiano.setText("Piano: ");
			
			btnPiano = new Bottone(this, SWT.CHECK);
			btnPiano.setToolTipText(TOOLTIP_PIANO);
			addChild(btnPiano);
			
			new SpacerLabel(this, SWT.NONE, SpacerLabel.SHORT);
			
			Label lblGdo = new Label(this, SWT.NONE);
			lblGdo.setToolTipText(TOOLTIP_GDO);
			lblGdo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblGdo.setText("GDO: ");
			
			btnGDO = new Bottone(this, SWT.CHECK);
			btnGDO.setToolTipText(TOOLTIP_GDO);
			addChild(btnGDO);

			new SpacerLabel(this, SWT.NONE, SpacerLabel.SHORT);
			
			Label lblAppuntamento = new Label(this, SWT.NONE);
			lblAppuntamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblAppuntamento.setText("Appuntamento: ");
			
			btnAppuntamento = new Bottone(this, SWT.CHECK);
			addChild(btnAppuntamento);
			
			new SpacerLabel(this, SWT.NONE, SpacerLabel.SHORT);
		}
		
	}

	
	public String getRagioneSociale() {
		return textRagioneSociale.getText();
	}
	
	public void setRagioneSociale(String text) {
		textRagioneSociale.setText(text);
	}
	
	public String getTelefono() {
		return textTelefono.getText();
	}
	
	public void setTelefono(String text) {
		textTelefono.setText(text);
	}
	
	public String getEmail() {
		return textMail.getText();
	}
	
	public void setEmail(String text) {
		textMail.setText(text);
	}
	
	public String getIndirizzo() {
		return textIndirizzo.getText();
	}
	
	public void setIndirizzo(String text) {
		textIndirizzo.setText(text);
	}
	
	public String getCap() {
		return textCap.getText();
	}
	
	public void setCap(String text) {
		textCap.setText(text);
	}
	
	public String getLocalita() {
		return textLocalita.getText();
	}
	
	public void setLocalita(String text) {
		textLocalita.setText(text);
	}
	
	public Nazione getNazione() {
		return comboNazione.getSelectedValue();
	}
	
	public void setNazione(Nazione value) {
		comboNazione.setSelectedValue(value);
	}
	
	public Provincia getProvincia() {
		return comboProvincia.getSelectedValue();
	}
	
	public void setProvincia(Provincia value) {
		comboProvincia.setSelectedValue(value);
	}
	
	public boolean getPrivato() {
		return btnPrivato.getSelection();
	}
	
	public void setPrivato(boolean value) {
		btnPrivato.setSelection(value);
	}
	
	public boolean getPiano() {
		return btnPiano.getSelection();
	}
	
	public void setPiano(boolean value) {
		btnPiano.setSelection(value);
	}
	
	public boolean getGDO() {
		return btnGDO.getSelection();
	}
	
	public void setGDO(boolean value) {
		btnGDO.setSelection(value);
	}
	
	public boolean getAppuntamento() {
		return btnAppuntamento.getSelection();
	}
	
	public void setAppuntamento(boolean value) {
		btnAppuntamento.setSelection(value);
	}
}

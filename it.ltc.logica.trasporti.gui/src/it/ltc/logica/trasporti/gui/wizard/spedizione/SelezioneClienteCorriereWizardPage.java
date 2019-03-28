package it.ltc.logica.trasporti.gui.wizard.spedizione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.uscite.ControllerOrdineTipi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.documenti.Documento;
import it.ltc.logica.database.model.centrale.ordini.OrdineTipo;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.wizard.PaginaWizard;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelezioneClienteCorriereWizardPage extends PaginaWizard {
	
	private static final String titolo = "Inserisci una nuova spedizione";
	private static final String descrizione = "Seleziona la commessa del cliente e inserisci i dati sull'ordine.";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<OrdineTipo> comboTipoOrdine;
	private TextForString textRiferimento;
	private TextForDescription textNote;
	private TextForInteger textPezzi;
	
	private final Documento documento;
	private final Spedizione spedizione;

	public SelezioneClienteCorriereWizardPage(Documento documento, Spedizione spedizione) {
		super(titolo, descrizione);
		
		this.documento = documento;
		this.spedizione = spedizione;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(3, false));
		
		Label lblCliente = new Label(container, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente: ");
		
		comboCommessa = new ComboBox<Commessa>(container);
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				impostaTipiOrdine();
			}
		});
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		addChild(comboCommessa);
		
		new SpacerLabel(container);
		
		Label lblTipoOrdine = new Label(container, SWT.NONE);
		lblTipoOrdine.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoOrdine.setText("Tipo Ordine: ");
		
		comboTipoOrdine = new ComboBox<>(container);
		comboTipoOrdine.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipoOrdine.setEnabled(false);
		addChild(comboTipoOrdine);
		
		new SpacerLabel(container);
		
		Label lblPezzi = new Label(container, SWT.NONE);
		lblPezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezzi.setText("Pezzi:");
		
		textPezzi = new TextForInteger(container);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(container);
		
		Label lblRiferimento = new Label(container, SWT.NONE);
		lblRiferimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimento.setText("Riferimento: ");
		
		textRiferimento = new TextForString(container);
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRiferimento.setMessage("Obbligatorio, es. DDT 1234. Compilare con precisione.");
		addChild(textRiferimento);
		
		new SpacerLabel(container);
		
		Label lblNote = new Label(container, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(container);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		addChild(textNote);
		
		new SpacerLabel(container);
	}
	
	private void impostaTipiOrdine() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			comboTipoOrdine.setEnabled(true);
			comboTipoOrdine.setItems(ControllerOrdineTipi.getInstance().getTipi(commessaSelezionata));
		} else {
			comboTipoOrdine.setEnabled(false);
		}
	}

	@Override
	public void copyDataToModel() {
		//Documento
		documento.setIdCommessa(comboCommessa.getSelectedValue().getId());
		documento.setRiferimentoCliente(textRiferimento.getText());
		documento.setRiferimentoInterno(comboTipoOrdine.getSelectedValue().getCodice());
		//Spedizione
		spedizione.setIdCommessa(comboCommessa.getSelectedValue().getId());
		spedizione.setRiferimentoCliente(textRiferimento.getText());
		spedizione.setRiferimentoMittente(textRiferimento.getText());
		spedizione.setNote(textNote.getText());
		spedizione.setPezzi(textPezzi.getValue());
	}
}

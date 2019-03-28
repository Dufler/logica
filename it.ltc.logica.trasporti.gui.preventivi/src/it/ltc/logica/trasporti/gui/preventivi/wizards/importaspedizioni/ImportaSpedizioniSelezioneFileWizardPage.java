package it.ltc.logica.trasporti.gui.preventivi.wizards.importaspedizioni;

import java.nio.file.FileSystems;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.gui.dialog.DialogSelezioneFile;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.importazione.FileSpedizioni;
import it.ltc.logica.trasporti.controller.importazione.TipoFileImportazioneSpedizioni;

public class ImportaSpedizioniSelezioneFileWizardPage extends PaginaWizard {

	public static final String title = "Importa Spedizioni per simulazione";
	public static final String description = "Selezione il file da importare e la tipologia";
	
	private Text textFile;
	private TextForString textNomeDocumento;
	private TextForDescription textNote;
	private DateField dataDocumento;
	private ComboBox<TipoFileImportazioneSpedizioni> comboTipoDocumento;
	
	private final FileSpedizioni file;
	
	protected ImportaSpedizioniSelezioneFileWizardPage(FileSpedizioni file) {
		super(title, description);
		this.file = file;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(2, false));
		
		Button btnApri = new Button(container, SWT.NONE);
		btnApri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezionaFile();
			}
		});
		btnApri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnApri.setText("Apri...");
		
		textFile = new Text(container, SWT.BORDER);
		textFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textFile.setEditable(false);
		
		Label lblTipo = new Label(container, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipo.setText("Tipo Documento: ");
		
		comboTipoDocumento = new ComboBox<>(container);
		comboTipoDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipoDocumento.setItems(TipoFileImportazioneSpedizioni.values());
		comboTipoDocumento.setEnabled(false);
		addChild(comboTipoDocumento);
		
		Label lblNomeDocumento = new Label(container, SWT.NONE);
		lblNomeDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNomeDocumento.setText("Nome Documento: ");
		
		textNomeDocumento = new TextForString(container);
		textNomeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textNomeDocumento.setEnabled(false);
		addChild(textNomeDocumento);
		
		Label lblDataDocumento = new Label(container, SWT.NONE);
		lblDataDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDocumento.setText("Data Documento: ");
		
		dataDocumento = new DateField(container);
		dataDocumento.setEnabled(false);
		addChild(dataDocumento);
		
		Label lblNote = new Label(container, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNote.setText("Note: ");
		
		textNote = new TextForDescription(container);
		textNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		textNote.setEnabled(false);
		addChild(textNote);
	}

	@Override
	public void copyDataToModel() {
		file.setTipo(comboTipoDocumento.getSelectedValue());
		file.setPathFile(textFile.getText());
		file.setNomeDocumento(textNomeDocumento.getText());
		file.setDataDocumento(dataDocumento.getValue());
		file.setNote(textNote.getText());
	}
	
	private void selezionaFile() {
		DialogSelezioneFile dialog = new DialogSelezioneFile();
		String filePath = dialog.open();
		int separatorIndex = filePath != null ? filePath.lastIndexOf(FileSystems.getDefault().getSeparator()) : -1;
		if (separatorIndex != -1) {
			//abilito i campi modificabili
			comboTipoDocumento.setEnabled(true);
			textNomeDocumento.setEnabled(true);
			dataDocumento.setEnabled(true);
			textNote.setEnabled(true);
			//Copio i dati
			textFile.setText(filePath);
			textNomeDocumento.setText(filePath.substring(separatorIndex + 1, filePath.length()));
			dataDocumento.setValue(new Date());
		}
	}

}

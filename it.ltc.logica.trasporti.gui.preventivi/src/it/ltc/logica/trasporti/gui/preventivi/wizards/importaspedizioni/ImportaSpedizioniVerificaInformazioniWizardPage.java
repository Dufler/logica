package it.ltc.logica.trasporti.gui.preventivi.wizards.importaspedizioni;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.importazione.FileSpedizioni;
import it.ltc.logica.trasporti.controller.importazione.ImportazioneFileSpedizioniController;
import it.ltc.logica.trasporti.gui.elements.spedizionesimulazionecheckbox.TabellaCheckBoxSpedizioneSimulazione;

public class ImportaSpedizioniVerificaInformazioniWizardPage extends PaginaWizardRisultati {
	
	public static final String title = "Importa Spedizioni per simulazione";
	public static final String description = "Controlla le informazioni inserite ed elaborate, clicca fine per importare le spedizioni a sistema.";

	private final FileSpedizioni file;
	private final List<SpedizioneSimulazione> spedizioni;
	private final SimpleDateFormat sdf;
	private final ImportazioneFileSpedizioniController controllerImportazione;
	
	private TabellaCheckBoxSpedizioneSimulazione tabella;
	private Text textNomeDocumento;
	private Text textDataDocumento;
	private Label lblSpedizioniTrovate;
	
	protected ImportaSpedizioniVerificaInformazioniWizardPage(FileSpedizioni file, List<SpedizioneSimulazione> spedizioni) {
		super(title, description, true);
		this.file = file;
		this.spedizioni = spedizioni;
		this.sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.controllerImportazione = ImportazioneFileSpedizioniController.getInstance();
	}

	@Override
	public void mostraRisultato() {
		List<SpedizioneSimulazione> spedizioniNelFile = controllerImportazione.estraiSpedizioniDaFile(file.getPathFile(), file.getTipo());
		spedizioni.clear();
		spedizioni.addAll(spedizioniNelFile);
		tabella.setElementi(spedizioni);
		tabella.setAllChecked(true);
		textNomeDocumento.setText(file.getNomeDocumento());
		textDataDocumento.setText(sdf.format(file.getDataDocumento()));
		lblSpedizioniTrovate.setText("Spedizioni trovate: " + spedizioniNelFile.size());
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(2, false));
		
		Label lblNomeDocumento = new Label(container, SWT.NONE);
		lblNomeDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNomeDocumento.setText("Nome Documento: ");
		
		textNomeDocumento = new Text(container, SWT.BORDER);
		textNomeDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textNomeDocumento.setEditable(false);
		
		Label lblDataDocumento = new Label(container, SWT.NONE);
		lblDataDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDocumento.setText("Data Documento: ");
		
		textDataDocumento = new Text(container, SWT.BORDER);
		textDataDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textDataDocumento.setEditable(false);
		
		lblSpedizioniTrovate = new Label(container, SWT.NONE);
		lblSpedizioniTrovate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSpedizioniTrovate.setText("Spedizioni trovate: ");
		
		new SpacerLabel(container);
		
		tabella = new TabellaCheckBoxSpedizioneSimulazione(container, TabellaCheckBoxSpedizioneSimulazione.TIPO_COMPLETO);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
	}

}

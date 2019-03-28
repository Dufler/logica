package it.ltc.logica.trasporti.gui.preventivi.wizards.importate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoSimulazioniImportateController;
import it.ltc.logica.trasporti.gui.elements.documentisimulazionespedizioni.TabellaDocumentiSimulazioneSpedizioni;

public class PreventivoSpedizioniImportateSelezioneDocumentoWizardPage extends PaginaWizard {
	
	private static final String title = "Preventivo di costo e fatturazione per delle spedizioni importate.";
	private static final String description = "Seleziona il documento che contiene le spedizioni da simulare.";

	private TabellaDocumentiSimulazioneSpedizioni tabella;
	
	protected PreventivoSpedizioniImportateSelezioneDocumentoWizardPage() {
		super(title, description);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaIlDocumento = new Label(container, SWT.NONE);
		lblSelezionaIlDocumento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblSelezionaIlDocumento.setText("Seleziona il documento che contiene le spedizioni da simulare.\r\nE' possibile importare nuovi documenti dal wizard \"Importa spedizioni di simulazione\"");
		
		tabella = new TabellaDocumentiSimulazioneSpedizioni(container);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		int documentiSelezionati = tabella.getCheckedElements().length;
		boolean selezioneValida = documentiSelezionati == 1; 
		return valid && selezioneValida;
	}

	@Override
	public void copyDataToModel() {
		DocumentoSpedizioniSimulazione documento = tabella.getRigaSelezionata();
		PreventivoSimulazioniImportateController.getInstance().setIdDocumento(documento != null ? documento.getId() : null);
	}

}

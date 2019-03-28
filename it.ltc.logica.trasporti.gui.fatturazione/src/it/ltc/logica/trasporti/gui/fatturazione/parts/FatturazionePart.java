
package it.ltc.logica.trasporti.gui.fatturazione.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.dialog.DialogSelezioneFile;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.controller.FatturazioneTNTController;
import it.ltc.logica.trasporti.controller.fatturazione.brt.FatturazioneBRTController;
import it.ltc.logica.trasporti.gui.fatturazione.elements.TabellaDocumentiFatturazioneTrasporti;
import it.ltc.logica.trasporti.gui.fatturazione.wizards.giacenze.FatturazioneGiacenzeWizard;
import it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni.FatturazioneSpedizioniWizard;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FatturazionePart {

	public static final String partID = "it.ltc.logica.trasporti.gui.fatturazione.part.fatturazione";

	private TabellaDocumentiFatturazioneTrasporti tabellaDocumentiSpedizioni;
	private Table tableSpedizioni;

	private TabellaDocumentiFatturazioneTrasporti tabellaDocumentiGiacenze;
	private Table tableGiacenze;

	private final boolean abilitaFatturazioneSpedizioni;
	private final boolean abilitaFatturazioneGiacenze;

	@Inject
	public FatturazionePart() {
		// Mi assicuro che i seguenti controller siano stati istanziati.
		ControllerListiniClienti.getInstance();
		ControllerListiniCorrieri.getInstance();
		ControllerCodiciClienteCorriere.getInstance();
		ControllerCap.getInstance();
		ControllerPreferenzeFatturazione.getInstance();
		ControllerCoordinateBancarie.getInstance();

		abilitaFatturazioneSpedizioni = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_FATTURAZIONE_SPEDIZIONI.getID());
		abilitaFatturazioneGiacenze = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.TRASPORTI_FATTURAZIONE_GIACENZE.getID());

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		//Spedizioni
		aggiungiTabSpedizioni(tabFolder);

		// Giacenze
		aggiungiTabGiacenze(tabFolder);

		// Riempo le tabelle.
		aggiornaTabellaDocumentiSpedizioni();
		aggiornaTabellaDocumentiGiacenze();

		// Imposto la selezione sulla prima tab per mostrarla.
		tabFolder.setSelection(0);
	}

	private void aggiungiTabSpedizioni(CTabFolder tabFolder) {

		CTabItem tbtmSpedizioni = new CTabItem(tabFolder, SWT.NONE);
		tbtmSpedizioni.setText("Spedizioni e Ritiri");

		Composite compositeSpedizioni = new Composite(tabFolder, SWT.NONE);
		tbtmSpedizioni.setControl(compositeSpedizioni);
		compositeSpedizioni.setLayout(new GridLayout(1, false));

		Button btnFattura = new Button(compositeSpedizioni, SWT.NONE);
		btnFattura.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				avviaFatturazioneSpedizioni();
			}
		});
		btnFattura.setText("Fattura Trasporti");
		btnFattura.setEnabled(abilitaFatturazioneSpedizioni);

		Button btnImportaFatturaCorriere = new Button(compositeSpedizioni, SWT.NONE);
		btnImportaFatturaCorriere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				importaDatiFatturazioneTNT();
			}
		});
		btnImportaFatturaCorriere.setText("Importa Fattura TNT");
		btnImportaFatturaCorriere.setEnabled(abilitaFatturazioneSpedizioni);

		Button btnImportaFatturaBrt = new Button(compositeSpedizioni, SWT.NONE);
		btnImportaFatturaBrt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				importaDatiFatturazioneBRT();
			}
		});
		btnImportaFatturaBrt.setText("Importa Fattura BRT");
		btnImportaFatturaBrt.setEnabled(abilitaFatturazioneSpedizioni);

		Composite compositeDocumenti = new Composite(compositeSpedizioni, SWT.NONE);
		compositeDocumenti.setLayout(new GridLayout(1, false));
		compositeDocumenti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblDocumentiDiFatturazione = new Label(compositeDocumenti, SWT.NONE);
		lblDocumentiDiFatturazione.setText("Documenti di Fatturazione: ");

		tabellaDocumentiSpedizioni = new TabellaDocumentiFatturazioneTrasporti(compositeDocumenti, 1);
		tableSpedizioni = tabellaDocumentiSpedizioni.getTable();
		tableSpedizioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}
	
	private void aggiungiTabGiacenze(CTabFolder tabFolder) {
		CTabItem tbtmGiacenze = new CTabItem(tabFolder, SWT.NONE);
		tbtmGiacenze.setText("Giacenze");

		Composite compositeGiacenze = new Composite(tabFolder, SWT.NONE);
		tbtmGiacenze.setControl(compositeGiacenze);
		compositeGiacenze.setLayout(new GridLayout(1, false));
		
		Button btnFatturaGiacenze = new Button(compositeGiacenze, SWT.NONE);
		btnFatturaGiacenze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				avviaFatturazioneGiacenze();
			}
		});
		btnFatturaGiacenze.setText("Fattura Giacenze");
		btnFatturaGiacenze.setEnabled(abilitaFatturazioneGiacenze);

		Composite compositeDocumentiGiacenze = new Composite(compositeGiacenze, SWT.NONE);
		compositeDocumentiGiacenze.setLayout(new GridLayout(1, false));
		compositeDocumentiGiacenze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblDocumentiDiFatturazioneGiacenze = new Label(compositeDocumentiGiacenze, SWT.NONE);
		lblDocumentiDiFatturazioneGiacenze.setText("Documenti di Fatturazione: ");

		tabellaDocumentiGiacenze = new TabellaDocumentiFatturazioneTrasporti(compositeDocumentiGiacenze, 2);
		tableGiacenze = tabellaDocumentiGiacenze.getTable();
		tableGiacenze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	public void aggiornaTabellaDocumentiSpedizioni() {
		tabellaDocumentiSpedizioni.aggiornaContenuto();
	}

	public void aggiornaTabellaDocumentiGiacenze() {
		tabellaDocumentiGiacenze.aggiornaContenuto();
	}

	private void avviaFatturazioneSpedizioni() {
		DialogWizard wizard = new DialogWizard(new FatturazioneSpedizioniWizard(this), DialogWizard.WIZARD_PREVENTIVO);
		int returnCode = wizard.open();
		if (returnCode == Window.OK) {
			aggiornaTabellaDocumentiSpedizioni();
		}
	}

	private void avviaFatturazioneGiacenze() {
		DialogWizard wizard = new DialogWizard(new FatturazioneGiacenzeWizard(this), DialogWizard.WIZARD_PREVENTIVO);
		int returnCode = wizard.open();
		if (returnCode == Window.OK) {
			aggiornaTabellaDocumentiGiacenze();
		}
	}

	private void importaDatiFatturazioneTNT() {
		DialogSelezioneFile dialog = new DialogSelezioneFile();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			int[] esito = FatturazioneTNTController.getInstance().importaXLSFatturazione(path);
			String titolo = "Esito Importazione";
			String messaggio = "\u00C8 stato importato il file '" + path + "'\r\n\r\n";
			messaggio += "Totale dei record contenuti nel file: " + esito[0] + "\r\n";
			messaggio += "Totale delle spedizioni aggiornate: " + esito[1] + "\r\n";
			messaggio += "Totale delle giacenze aggiornate: " + esito[2] + "\r\n";
			messaggio += "Totale degli indirizzi aggiornati: " + esito[3] + "\r\n";
			messaggio += "Totale degli errori riscontrati: " + esito[4] + "\r\n";
			messaggio += "Totale delle nuove spedizioni trovate che non erano presenti a sistema: " + esito[5] + "\r\n";
			DialogMessaggio.openInformation(titolo, messaggio);
		}
	}

	private void importaDatiFatturazioneBRT() {
		DialogSelezioneFile dialog = new DialogSelezioneFile();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			int[] esito = FatturazioneBRTController.getInstance().importaDocumentoFatturazione(path);
			String titolo = "Esito Importazione";
			String messaggio = "\u00C8 stato importato il file '" + path + "'\r\n\r\n";
			messaggio += "Totale dei record contenuti nel file: " + esito[0] + "\r\n";
			messaggio += "Totale delle spedizioni aggiornate: " + esito[1] + "\r\n";
			messaggio += "Totale delle giacenze aggiornate: " + esito[2] + "\r\n";
			messaggio += "Totale degli indirizzi aggiornati: " + esito[3] + "\r\n";
			messaggio += "Totale degli errori riscontrati: " + esito[4] + "\r\n";
			messaggio += "Totale delle nuove spedizioni trovate che non erano presenti a sistema: " + esito[5] + "\r\n";
			DialogMessaggio.openInformation(titolo, messaggio);
		}
	}
}
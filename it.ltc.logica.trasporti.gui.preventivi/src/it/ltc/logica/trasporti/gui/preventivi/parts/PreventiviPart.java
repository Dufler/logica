 
package it.ltc.logica.trasporti.gui.preventivi.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.elements.indirizzosimulazione.TabellaIndirizziSimulazione;
import it.ltc.logica.trasporti.gui.elements.indirizzosimulazione.ToolbarIndirizziSimulazione;
import it.ltc.logica.trasporti.gui.preventivi.wizards.esistenti.PreventivoSpedizioniEsistentiWizard;
import it.ltc.logica.trasporti.gui.preventivi.wizards.fittizia.PreventivoSpedizioneFittiziaWizard;
import it.ltc.logica.trasporti.gui.preventivi.wizards.fittiziadue.PreventivoSpedizioneFittiziaDueWizard;
import it.ltc.logica.trasporti.gui.preventivi.wizards.fittiziasucolli.PreventivoSpedizioneFittiziaSuColliWizard;
import it.ltc.logica.trasporti.gui.preventivi.wizards.importaspedizioni.ImportaSpedizioniWizard;
import it.ltc.logica.trasporti.gui.preventivi.wizards.importate.PreventivoSpedizioniImportateWizard;

public class PreventiviPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.preventivi.part.preventivi";
	
	@Inject
	public PreventiviPart() {
		ControllerIndirizzi.getInstance();
		ControllerCap.getInstance();
		ControllerGiacenze.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Button btnPreventivoPerSpedizione = new Button(parent, SWT.NONE);
		btnPreventivoPerSpedizione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardPreventivoFittizia();
			}
		});
		btnPreventivoPerSpedizione.setText("Spedizioni fittizie");
		
		Button btnSpedizioniFittizieSu = new Button(parent, SWT.NONE);
		btnSpedizioniFittizieSu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardPreventivoFittiziaSuColli();
			}
		});
		btnSpedizioniFittizieSu.setText("Spedizioni fittizie su pi\u00F9 colli");
		
		Button btnSpedizioniFittizieSuDestinazioni = new Button(parent, SWT.NONE);
		btnSpedizioniFittizieSuDestinazioni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardPreventivoFittiziaSuDestinazioni();
			}
		});
		btnSpedizioniFittizieSuDestinazioni.setText("Spedizioni fittizie su pi\u00F9 colli su pi\u00F9 destinazioni");
		
		Button btnPreventivoPerSpedizioni = new Button(parent, SWT.NONE);
		btnPreventivoPerSpedizioni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardPreventivoEsistenti();
			}
		});
		btnPreventivoPerSpedizioni.setText("Spedizioni esistenti");
		
		Button btnPreventivoPerImportate = new Button(parent, SWT.NONE);
		btnPreventivoPerImportate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardPreventivoImportate();
			}
		});
		btnPreventivoPerImportate.setText("Spedizioni importate");
		
		Button btnImportaSpedizioniSimulazione = new Button(parent, SWT.NONE);
		btnImportaSpedizioniSimulazione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardImportazioneSpedizioni();
			}
		});
		btnImportaSpedizioniSimulazione.setText("Importa Spedizioni per simulazione");
		
		Label lblIndirizziDiSimulazione = new Label(parent, SWT.NONE);
		lblIndirizziDiSimulazione.setText("Indirizzi di simulazione: ");
		
		ToolbarIndirizziSimulazione toolbar = new ToolbarIndirizziSimulazione(parent);
		
		TabellaIndirizziSimulazione tabellaIndirizzi = new TabellaIndirizziSimulazione(parent);
		Table table = tabellaIndirizzi.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tabellaIndirizzi);
	}
	
	private void apriWizardPreventivoFittizia() {
		DialogWizard wizard = new DialogWizard(new PreventivoSpedizioneFittiziaWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	private void apriWizardPreventivoFittiziaSuColli() {
		DialogWizard wizard = new DialogWizard(new PreventivoSpedizioneFittiziaSuColliWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	private void apriWizardPreventivoFittiziaSuDestinazioni() {
		DialogWizard wizard = new DialogWizard(new PreventivoSpedizioneFittiziaDueWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	private void apriWizardPreventivoEsistenti() {
		DialogWizard wizard = new DialogWizard(new PreventivoSpedizioniEsistentiWizard(/*sync*/), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	private void apriWizardPreventivoImportate() {
		DialogWizard wizard = new DialogWizard(new PreventivoSpedizioniImportateWizard(/*sync*/), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	private void apriWizardImportazioneSpedizioni() {
		DialogWizard wizard = new DialogWizard(new ImportaSpedizioniWizard(/*sync*/), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Se serve fai qualcosa.
		}
	}
	
	
}
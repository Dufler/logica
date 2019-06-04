 
package it.ltc.logica.trasporti.gui.report.parts;

import java.util.Date;
import java.util.List;

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
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.report.wizards.colli.DistribuzioneColliWizard;
import it.ltc.logica.trasporti.gui.report.wizards.destinazione.DistribuzioneDestinazioneWizard;
import it.ltc.logica.trasporti.gui.report.wizards.pesovolume.DistribuzionePesoVolumeWizard;
import it.ltc.logica.trasporti.gui.report.wizards.semplice.StatisticheSempliciSpedizioniWizard;

public class ReportPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.report.part.report";
	
	@Inject
	public ReportPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblReport = new Label(parent, SWT.NONE);
		lblReport.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblReport.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblReport.setText("Report");
		
		Button btnReportSemplice = new Button(parent, SWT.NONE);
		btnReportSemplice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardReportSemplice();
			}
		});
		btnReportSemplice.setText("Report semplice");
		
		Button btnReportSullaDistribuzione = new Button(parent, SWT.NONE);
		btnReportSullaDistribuzione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardReportPesoVolume();
			}
		});
		btnReportSullaDistribuzione.setText("Distribuzione Peso/Volume");
		
		Button btnDistribuzioneColli = new Button(parent, SWT.NONE);
		btnDistribuzioneColli.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardReportDistribuzioneColli();
			}
		});
		btnDistribuzioneColli.setText("Distribuzione Colli");
		
		Button btnDistribuzioneDestinazioni = new Button(parent, SWT.NONE);
		btnDistribuzioneDestinazioni.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				apriWizardReportDistribuzioneDestinazioni();
			}
		});
		btnDistribuzioneDestinazioni.setText("Distribuzione Destinazioni");
		
		Button btnJasper = new Button(parent, SWT.NONE);
		btnJasper.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lanciaReportJasper();
			}
		});
		btnJasper.setText("Jasper");
		
	}
	
	private void lanciaReportJasper() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			//Carico le spedizioni - TODO le condizioni di filtraggio sono provvisorie.
			Date dataA = new Date();
			Date dataDa = new Date();
			dataDa.setTime(dataDa.getTime() - 1000000000);
			CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
			List<Spedizione> spedizioni = ControllerSpedizioni.getInstance().selezionaSpedizioni(criteri);
			//Elaboro il report nel percorso indicato e lo apro.
			ReportSpedizioniGeneriche report = new ReportSpedizioniGeneriche();
			String exportPath = report.creaReport(spedizioni, dataA, dataDa, path);
			if (exportPath != null)
				report.apriFile();
		}
	}
	
	private void apriWizardReportSemplice() {
		DialogWizard wizard = new DialogWizard(new StatisticheSempliciSpedizioniWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Fai qualcosa, se necessario.
		}		
	}
	
	private void apriWizardReportPesoVolume() {
		DialogWizard wizard = new DialogWizard(new DistribuzionePesoVolumeWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Fai qualcosa, se necessario.
		}		
	}
	
	private void apriWizardReportDistribuzioneColli() {
		DialogWizard wizard = new DialogWizard(new DistribuzioneColliWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Fai qualcosa, se necessario.
		}		
	}
	
	private void apriWizardReportDistribuzioneDestinazioni() {
		DialogWizard wizard = new DialogWizard(new DistribuzioneDestinazioneWizard(), DialogWizard.WIZARD_PREVENTIVO);
		int esito = wizard.open();
		if (esito == WizardDialog.OK) {
			//Fai qualcosa, se necessario.
		}		
	}
	
}
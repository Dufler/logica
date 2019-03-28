 
package it.ltc.logica.trasporti.gui.report.parts;

import java.util.Date;
import java.util.HashMap;
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
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.report.wizards.colli.DistribuzioneColliWizard;
import it.ltc.logica.trasporti.gui.report.wizards.destinazione.DistribuzioneDestinazioneWizard;
import it.ltc.logica.trasporti.gui.report.wizards.pesovolume.DistribuzionePesoVolumeWizard;
import it.ltc.logica.trasporti.gui.report.wizards.semplice.StatisticheSempliciSpedizioniWizard;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;

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
		System.out.println("Avvio report jasper.");
		//String reportInfo = "C:\\Users\\Damiano\\Desktop\\report\\Test_Json2.jrxml";
		String exportPath = "C:\\Users\\Damiano\\Desktop\\report\\Test_Json3.pdf";
		Date dataA = new Date();
		Date dataDa = new Date();
		dataDa.setTime(dataDa.getTime() - 1000000000);
		HashMap<String, Object> parameters = new HashMap<>();
//		parameters.put("start_date", dataA);
//		parameters.put("end_date", dataDa);
		parameters.put("DataDa", dataA);
		parameters.put("DataA", dataDa);
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		//criteri.setDataA(dataA);
		//criteri.setDataDa(dataDa);
		List<Spedizione> spedizioni = ControllerSpedizioni.getInstance().selezionaSpedizioni(criteri);
		try {
			//JasperReportBuilder.buildReportPDF(reportInfo, exportPath, parameters);
			JasperReportBuilder.buildReportPDF(ReportJasper.SPEDIZIONI, exportPath, parameters, spedizioni);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Report jasper completato.");
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
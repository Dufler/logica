package it.ltc.logica.utilities.report;

import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.swt.program.Program;

/**
 * Classe astratta con metodi d'utilità per i report.
 * @author Damiano
 *
 */
public abstract class ReportJasperModel {
	
	protected final static String DEFAULT_REPORT_PATH = "C:\\Logica\\report";
	
	protected final static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	protected final ReportExportType exportType;
	protected final HashMap<String, Object> parameters;
	
	public ReportJasperModel() {
		this(ReportExportType.PDF);
	}
	
	public ReportJasperModel(ReportExportType type) {
		exportType = type;
		parameters = new HashMap<>();
	}
	
	protected String getReportExportPath(String nomeReport, String selectedFolderPath) {
		String separatore = FileSystems.getDefault().getSeparator();
		nomeReport = nomeReport.replace(separatore, "");
		String nomeFile = nomeReport + getReportExtension();
		String exportPath = selectedFolderPath + separatore + nomeFile;
		return exportPath;
	}
	
	protected String getDefaultReportExportPath() {
		String nomeReport = timestampFormat.format(new Date());
		return getReportExportPath(nomeReport, DEFAULT_REPORT_PATH);
	}
	
	protected String getReportExtension() {
		return exportType.getExtension();
	}
	
	protected void apriFile(String path) {
		Program.launch(path);
	}
	
//	public void creaReport(Insieme oggetti su cui fare il report) {
//		try {
//			//Imposto i parametri base
//			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
//			parameters.put("dataStampa", new Date());
//			//...
//			//Strutturo le info per creare il report in base a quanto mi hanno passato
//			//...
//			//Genero il report vero e proprio nel path indicato
//			JasperReportBuilder.buildReportPDF(ReportJasper.CARICO_PER_COLLO_PDF, exportPath, parameters, righe);
//		} catch (Exception e) {
//			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
//			e.printStackTrace();
//		}
//	}

}

package it.ltc.logica.trasporti.gui.report.parts;

import java.util.Date;
import java.util.List;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.report.ReportJasperModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportSpedizioniGeneriche extends ReportJasperModel {
	
	public String creaReport(List<Spedizione> spedizioni, Date dataA, Date dataDa, String folderPath) {
		String exportPath;
		try {
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("DataDa", dataA);
			parameters.put("DataA", dataDa);
			exportPath = getReportExportPath("Spedizioni", folderPath);
			//Genero il report vero e proprio nel path indicato
			JasperReportBuilder.buildReportPDF(ReportJasper.SPEDIZIONI_GENERICO_PDF, exportPath, parameters, spedizioni);
		} catch (Exception e) {
			exportPath = null;
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return exportPath;
	}

}

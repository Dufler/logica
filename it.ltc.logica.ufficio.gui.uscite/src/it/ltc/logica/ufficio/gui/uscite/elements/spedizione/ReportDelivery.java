package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.Date;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.report.ReportJasperModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportDelivery extends ReportJasperModel {
	
	public void creaReport(List<DatiSpedizione> spedizioni, Commessa commessa, Corriere corriere) {
		try {
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			//FIXME parametri da verificare
			parameters.put("dataDelivery", new Date());
			parameters.put("indirizzoRitiro", "LTC - Corciano");
			parameters.put("sessioneDelivery", "123456");
			parameters.put("commessa", commessa.getNome());
			parameters.put("corriere", corriere.getNome());
			//...
			//Strutturo le info per creare il report in base a quanto mi hanno passato
			//...
			String exportPath = getDefaultReportExportPath();
			//Genero il report vero e proprio nel path indicato
			JasperReportBuilder.buildReportPDF(ReportJasper.DELIVERY_LIST_PDF, exportPath, parameters, spedizioni);
			apriFile();
		} catch (Exception e) {
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}

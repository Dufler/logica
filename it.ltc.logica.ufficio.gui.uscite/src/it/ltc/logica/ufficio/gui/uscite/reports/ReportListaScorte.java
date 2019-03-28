package it.ltc.logica.ufficio.gui.uscite.reports;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportListaScorte extends ReportOrdine {
	
	public String creaReport(Commessa commessa, List<RisultatoAssegnazioneOrdine> listeConScorte) {
		String exportPath;
		try {
			//Mi appunto i vari numeri di lista coinvolti e faccio una lista riepilogativa dei pezzi a scorta.
			StringBuilder liste = new StringBuilder();
			List<RisultatoAssegnazioneRigaOrdine> scorte = new LinkedList<>();
			for (RisultatoAssegnazioneOrdine scorta : listeConScorte) {
				liste.append("\r\n");
				liste.append("- ");
				liste.append(scorta.getOrdine().getNumeroLista());
				scorte.addAll(scorta.getScorte());
			}
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("commessa", commessa.getNome());
			parameters.put("dataOrdine", new Date());
			parameters.put("numeroLista", liste.toString());
			parameters.put("barcodeDocumento", timestampFormat.format(new Date()));
			//Raggruppo le righe con lo stesso prodotto, lo stesso collo e la stessa ubicazione.
			Collection<RisultatoAssegnazioneRigaOrdine> elementi = raggruppaElementi(scorte);
			//Genero il report vero e proprio nel path di default
			exportPath = getDefaultReportExportPath();
			JasperReportBuilder.buildReportPDF(ReportJasper.ORDINE_LISTA_SCORTE_PDF, exportPath, parameters, elementi);
		} catch (Exception e) {
			exportPath = null;
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return exportPath;
	}

}

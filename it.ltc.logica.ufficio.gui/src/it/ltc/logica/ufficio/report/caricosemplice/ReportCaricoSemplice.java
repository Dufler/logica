package it.ltc.logica.ufficio.report.caricosemplice;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ingressi.ControllerFornitori;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportExportType;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.report.ReportJasperModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportCaricoSemplice extends ReportJasperModel {
	
	public ReportCaricoSemplice(ReportExportType type) {
		super(type);
	}
	
	public void creaReport(Commessa commessa, CaricoTestata carico, List<CaricoDettaglio> righeCarico, String path) {
		try {
			ControllerProdotti controller = new ControllerProdotti(commessa);
			ControllerFornitori controllerFornitori = new ControllerFornitori(commessa);
			Fornitore fornitore = controllerFornitori.getFornitore(carico.getFornitore());
			String exportPath = getReportExportPath("Carico " + carico.getRiferimento(), path);
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("riferimento", carico.getRiferimento());
			parameters.put("dataCarico", carico.getDataArrivo());
			parameters.put("fornitore", fornitore != null ? fornitore.getNome() : "N/A");
			parameters.put("commessa", commessa != null ? commessa.getNome() : "N/A");
			List<CaricoSempliceRiga> righe = new LinkedList<>();
			for (CaricoDettaglio dettaglio : righeCarico) {
				Prodotto prodotto = controller.trovaDaID(dettaglio.getArticolo());
				CaricoSempliceRiga riga = new CaricoSempliceRiga();
				riga.setSku(prodotto.getChiaveCliente());
				riga.setDescrizione(prodotto.getDescrizione());
				riga.setTaglia(prodotto.getTaglia());
				riga.setDichiarato(dettaglio.getQuantitaDichiarata());
				riga.setRiscontrato(dettaglio.getQuantitaRiscontrata());
				riga.setMagazzino(dettaglio.getMagazzino());
				int mancante = dettaglio.getQuantitaDichiarata() > dettaglio.getQuantitaRiscontrata() ? dettaglio.getQuantitaDichiarata() - dettaglio.getQuantitaRiscontrata() : 0;
				int eccedente = dettaglio.getQuantitaDichiarata() < dettaglio.getQuantitaRiscontrata() ? dettaglio.getQuantitaRiscontrata() - dettaglio.getQuantitaDichiarata() : 0;
				riga.setMancante(mancante);
				riga.setEccedente(eccedente);
				righe.add(riga);
			}
			//Creo il report giusto in base alla tipologia indicata.
			switch (exportType) {
				case PDF : JasperReportBuilder.buildReportPDF(ReportJasper.CARICO_SEMPLICE_PDF, exportPath, parameters, righe); break;
				case XLS : JasperReportBuilder.buildReportXLSX(ReportJasper.CARICO_SEMPLICE_EXCEL, exportPath, parameters, righe); break;
				default : throw new RuntimeException("La tipologia di report indicata non è valida. (" + exportType + ")");
			}
			//Apro il file creato.
			apriFile();
		} catch (Exception e) {
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}

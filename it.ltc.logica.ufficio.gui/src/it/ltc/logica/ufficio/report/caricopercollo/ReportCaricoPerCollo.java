package it.ltc.logica.ufficio.report.caricopercollo;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ingressi.ControllerFornitori;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.ColloCaricoJSON;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.database.model.centrale.ingressi.ProdottoCaricoJSON;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.report.ReportJasperModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportCaricoPerCollo extends ReportJasperModel {
	
	public void creaReport(Commessa commessa, CaricoTestata carico, List<ColloCaricoJSON> colli, List<ProdottoCaricoJSON> prodotti, String path) {
		try {
			ControllerProdotti controller = new ControllerProdotti(commessa);
			ControllerFornitori controllerFornitori = new ControllerFornitori(commessa);
			Fornitore fornitore = controllerFornitori.getFornitore(carico.getFornitore());
			String exportPath = getReportExportPath("Carico per collo " + carico.getRiferimento(), path);
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("riferimento", carico.getDocumentoRiferimento());
			parameters.put("dataCarico", carico.getDocumentoData());
			parameters.put("fornitore", fornitore != null ? fornitore.getNome() : "N/A");
			parameters.put("commessa", commessa != null ? commessa.getNome() : "N/A");
			//Creo una mappa dei colli e ubicazioni
			HashMap<Integer, String> mappaColli = new HashMap<>();
			for (ColloCaricoJSON collo : colli) {
				mappaColli.put(collo.getId(), collo.getCollo() + " " + collo.getUbicazione());
			}
			//Creo una mappa per i magazzini
			HashMap<Integer, String> mappaMagazzini = new HashMap<>();
			for (ColloCaricoJSON collo : colli) {
				mappaMagazzini.put(collo.getId(), collo.getMagazzino());
			}
			HashMap<String, CaricoPerColloRiga> mappaProdotti = new HashMap<>();
			for (ProdottoCaricoJSON dettaglio : prodotti) {
				Prodotto prodotto = controller.trovaDaID(dettaglio.getProdotto());
				String key = prodotto.getChiaveCliente() + dettaglio.getCollo();
				if (!mappaProdotti.containsKey(key)) {
					CaricoPerColloRiga riga = new CaricoPerColloRiga();
					riga.setSku(prodotto.getChiaveCliente());
					riga.setDescrizione(prodotto.getDescrizione());
					riga.setTaglia(prodotto.getTaglia());
					riga.setQuantita(dettaglio.getQuantita());
					riga.setColloEUbicazione(mappaColli.get(dettaglio.getCollo()));
					riga.setMagazzino(mappaMagazzini.get(dettaglio.getCollo()));
					mappaProdotti.put(key, riga);
				} else {
					CaricoPerColloRiga riga = mappaProdotti.get(key);
					riga.setQuantita(riga.getQuantita() + dettaglio.getQuantita());
				}
			}
			List<CaricoPerColloRiga> righe = new LinkedList<>();
			for (CaricoPerColloRiga riga : mappaProdotti.values())
				righe.add(riga);
			JasperReportBuilder.buildReportPDF(ReportJasper.CARICO_PER_COLLO_PDF, exportPath, parameters, righe);
			apriFile(exportPath);
		} catch (Exception e) {
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}


}

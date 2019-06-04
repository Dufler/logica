package it.ltc.logica.ufficio.report.ordine;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.uscite.ControllerDestinatari;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.database.model.centrale.ordini.ImballoProdotto;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.report.ReportJasperModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportImballoOrdine extends ReportJasperModel {
	
	public String creaReport(Commessa commessa, OrdineTestata ordine, List<ImballoCollo> colli) {
		String exportPath;
		try {
			//Recupero l'indirizzo di destinazione
			ControllerDestinatari controllerDestinatari = new ControllerDestinatari(commessa);
			Indirizzo destinatario = controllerDestinatari.getIndirizzo(ordine.getDestinatario());
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("commessa", commessa.getNome());
			parameters.put("dataOrdine", new Date());
			parameters.put("totaleQuantitaOrdinata", ordine.getQuantitaOrdinataTotale());
			parameters.put("riferimento", ordine.getRiferimento());
			parameters.put("numeroLista", ordine.getNumeroLista());
			parameters.put("cliente", ordine.getRagioneSocialeDestinatario());
			parameters.put("indirizzo", destinatario.getIndirizzo());
			parameters.put("cap", destinatario.getCap());
			parameters.put("localita", destinatario.getLocalita());
			parameters.put("provincia", destinatario.getProvincia());
			parameters.put("nazione", destinatario.getNazione());
			//Raggruppo le righe con lo stesso collo.
			Collection<ElementoImballoOrdine> elementi = raggruppaElementi(colli);
			//Genero il report vero e proprio nel path di default
			exportPath = getDefaultReportExportPath();
			JasperReportBuilder.buildReportPDF(ReportJasper.ORDINE_IMBALLO_PER_COLLO_PDF, exportPath, parameters, elementi);
		} catch (Exception e) {
			exportPath = null;
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return exportPath;
	}
	
	private Collection<ElementoImballoOrdine> raggruppaElementi(List<ImballoCollo> colli) {
		LinkedList<ElementoImballoOrdine> elementi = new LinkedList<>();
		for (ImballoCollo collo : colli) {
			for (ImballoProdotto prodotto : collo.getProdotti()) {
				ElementoImballoOrdine elemento = new ElementoImballoOrdine(collo, prodotto);
				elementi.add(elemento);
			}
		}
		return elementi;
	}

}

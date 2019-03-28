package it.ltc.logica.ufficio.gui.uscite.reports;

import java.util.Collection;
import java.util.Date;

import it.ltc.logica.common.controller.uscite.ControllerDestinatari;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.utilities.report.JasperReportBuilder;
import it.ltc.logica.utilities.report.ReportJasper;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ReportListaPrelievo extends ReportOrdine {

	public String creaReport(Commessa commessa, RisultatoAssegnazioneOrdine listaDiPrelievo) {
		String exportPath;
		try {
			//Recupero l'indirizzo di destinazione
			ControllerDestinatari controllerDestinatari = new ControllerDestinatari(commessa);
			Indirizzo destinatario = controllerDestinatari.getIndirizzo(listaDiPrelievo.getOrdine().getDestinatario());
			//Imposto i parametri base
			parameters.put("utente", ControllerVariabiliGlobali.getInstance().getString("utente.username"));
			parameters.put("dataStampa", new Date());
			parameters.put("commessa", commessa.getNome());
			parameters.put("numeroLista", listaDiPrelievo.getOrdine().getNumeroLista());
			parameters.put("riferimento", listaDiPrelievo.getOrdine().getRiferimento());
			parameters.put("dataOrdine", listaDiPrelievo.getOrdine().getDataOrdine());
			parameters.put("cliente", listaDiPrelievo.getOrdine().getRagioneSocialeDestinatario());
			parameters.put("indirizzo", destinatario.getIndirizzo());
			parameters.put("cap", destinatario.getCap());
			parameters.put("localita", destinatario.getLocalita());
			parameters.put("provincia", destinatario.getProvincia());
			parameters.put("nazione", destinatario.getNazione());
			//Raggruppo le righe con lo stesso prodotto, lo stesso collo e la stessa ubicazione.
			Collection<RisultatoAssegnazioneRigaOrdine> elementi = raggruppaElementi(listaDiPrelievo.getPrelevabili());
			//Genero il report vero e proprio nel path di default
			exportPath = getDefaultReportExportPath();
			JasperReportBuilder.buildReportPDF(ReportJasper.ORDINE_LISTA_PRELIEVO_PDF, exportPath, parameters, elementi);
		} catch (Exception e) {
			exportPath = null;
			DialogMessaggio.openError("Errore durante la generazione del report", "Errore durante la generazione del report: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return exportPath;
	}
	
}

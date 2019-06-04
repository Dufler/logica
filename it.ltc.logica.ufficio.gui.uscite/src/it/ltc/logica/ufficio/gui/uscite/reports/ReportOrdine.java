package it.ltc.logica.ufficio.gui.uscite.reports;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.utilities.report.ReportJasperModel;

public abstract class ReportOrdine extends ReportJasperModel {
	
	/**
	 * Raggruppa gli elementi con identica ubicazione, collo e prodotto andando a sommare le quantità.
	 */
	protected Collection<RisultatoAssegnazioneRigaOrdine> raggruppaElementi(List<RisultatoAssegnazioneRigaOrdine> lista) {
		HashMap<String, RisultatoAssegnazioneRigaOrdine> mappaRigheQuantita = new HashMap<>();
		for (RisultatoAssegnazioneRigaOrdine riga : lista) {
			String key = getChiaveRaggruppamento(riga);
			if (mappaRigheQuantita.containsKey(key)) {
				RisultatoAssegnazioneRigaOrdine presente = mappaRigheQuantita.get(key);
				presente.setQuantita(presente.getQuantita() + riga.getQuantita());
				presente.setTotalePezzi(presente.getTotalePezzi() + riga.getTotalePezzi());
			} else {
				//Creo una copia ad-hoc con i soli valori necessari al report.
				RisultatoAssegnazioneRigaOrdine copia = new RisultatoAssegnazioneRigaOrdine();
				copia.setSku(riga.getSku());
				copia.setDescrizione(riga.getDescrizione());
				copia.setTaglia(riga.getTaglia());
				copia.setQuantita(riga.getQuantita());
				copia.setTotalePezzi(riga.getTotalePezzi());
				copia.setCollo(riga.getCollo());
				copia.setUbicazione(riga.getUbicazione());
				String anomalia;
				switch (riga.getStato()) {
					case PRELIEVO : anomalia = ""; break;
					case SCORTA : anomalia = "scorta"; break;
					case NONUBICATO : anomalia = "non ubicato"; break;
					case NONPRESENTE : anomalia = "anomalia: non disponibile"; break;
					case LEGACY : anomalia = "anomalia: assegnazione legacy mista"; break;
					default : anomalia = "anomalia: non specificato"; break;
				}
				copia.setAnomalie(anomalia);
				mappaRigheQuantita.put(key, copia);
			}
		}
		return mappaRigheQuantita.values();
	}
	
	/**
	 * Restituisce una chiave di raggruppamento per gli elementi.
	 */
	protected String getChiaveRaggruppamento(RisultatoAssegnazioneRigaOrdine riga) {
		String key = riga.getUbicazione() + "#" + riga.getCollo() + "#" + riga.getSku() + "#" + riga.getTaglia();
		return key;
	}

}

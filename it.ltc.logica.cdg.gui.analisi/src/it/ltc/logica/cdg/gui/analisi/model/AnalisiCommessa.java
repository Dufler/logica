package it.ltc.logica.cdg.gui.analisi.model;

import java.util.Date;
import java.util.HashMap;

import it.ltc.logica.database.model.centrale.Commessa;

public class AnalisiCommessa {
	
	private final Date inizio;
	private final Date fine;
	private final Commessa commessa;
	private final HashMap<Integer, RiepilogoGiornalieroCDG> riepilogoPerGiorno;
	
	public AnalisiCommessa(Commessa commessa, HashMap<Integer, RiepilogoGiornalieroCDG> riepilogoPerGiorno, Date inizio, Date fine) {
		this.commessa = commessa;
		this.riepilogoPerGiorno = riepilogoPerGiorno;
		this.inizio = inizio;
		this.fine = fine;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public HashMap<Integer, RiepilogoGiornalieroCDG> getRiepilogoPerGiorno() {
		return riepilogoPerGiorno;
	}
	
	public Date getInizio() {
		return inizio;
	}

	public Date getFine() {
		return fine;
	}

	public double getTotaleCosti() {
		double costi = 0;
		for (RiepilogoGiornalieroCDG riepilogo : riepilogoPerGiorno.values()) {
			costi += riepilogo.getCostiReali();
		}
		return costi;
	}
	
	public HashMap<Integer, Double> getRiepilogoCostiPeriodoPerFase() {
		HashMap<Integer, Double> mappaFaseCosti = new HashMap<>();
		for (RiepilogoGiornalieroCDG riepilogo : riepilogoPerGiorno.values()) {
			HashMap<Integer, RiepilogoGiornalieroPerFaseCDG> mappaFasiDelGiorno = riepilogo.getMappaFaseTotali();
			for (Integer fase : mappaFasiDelGiorno.keySet()) {
				if (!mappaFaseCosti.containsKey(fase)) {
					mappaFaseCosti.put(fase, 0.0);
				}
				Double totaleFase = mappaFaseCosti.get(fase);
				totaleFase += mappaFasiDelGiorno.get(fase).getCostiReali();
				mappaFaseCosti.put(fase, totaleFase);
			}
		}
		return mappaFaseCosti;
	}
	
	public double getTotaleRicavi() {
		double ricavi = 0;
		for (RiepilogoGiornalieroCDG riepilogo : riepilogoPerGiorno.values()) {
			ricavi += riepilogo.getRicaviIpotetici();
		}
		return ricavi;
	}

	public HashMap<Integer, Double> getRiepilogoRicaviPeriodoPerFase() {
		HashMap<Integer, Double> mappaFaseCosti = new HashMap<>();
		for (RiepilogoGiornalieroCDG riepilogo : riepilogoPerGiorno.values()) {
			HashMap<Integer, RiepilogoGiornalieroPerFaseCDG> mappaFasiDelGiorno = riepilogo.getMappaFaseTotali();
			for (Integer fase : mappaFasiDelGiorno.keySet()) {
				if (!mappaFaseCosti.containsKey(fase)) {
					mappaFaseCosti.put(fase, 0.0);
				}
				Double totaleFase = mappaFaseCosti.get(fase);
				totaleFase += mappaFasiDelGiorno.get(fase).getRicaviIpotetici();
				mappaFaseCosti.put(fase, totaleFase);
			}
		}
		return mappaFaseCosti;
	}

	@Override
	public String toString() {
		return commessa != null ? commessa.toString() : "N/A";
	}
}

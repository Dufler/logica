package it.ltc.logica.cdg.gui.analisi.model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.cdg.CdgFase;

/**
 * Contiene gli elementi che formano il riepilogo per un dato giorno.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class RiepilogoGiornalieroCDG {
	
	private final Date giorno;
	private final List<ElementoBilancioCDG> elementi;
	
	private double costiExtra;
	private double costiReali;
	private double costiIpotetici;
	private double ricaviIpotetici;
	private int totaleSecondi;
	private int totalePezzi;
	
	private final HashMap<Integer, RiepilogoGiornalieroPerFaseCDG> mappaFaseTotali;
	
	public RiepilogoGiornalieroCDG(Date giorno) {
		this.giorno = giorno;
		this.elementi = new LinkedList<>();
		this.mappaFaseTotali = new HashMap<>();
		
		this.costiExtra = 0;
		this.costiReali = 0;
		this.costiIpotetici = 0;
		this.ricaviIpotetici = 0;
		this.totaleSecondi = 0;
		this.totalePezzi = 0;
	}
	
	public double getCostiExtra() {
		return costiExtra;
	}

	public double getCostiReali() {
		return costiReali;
	}

	public double getCostiIpotetici() {
		return costiIpotetici;
	}

	public double getRicaviIpotetici() {
		return ricaviIpotetici;
	}
	
	public int getTotaleSecondi() {
		return totaleSecondi;
	}
	
	public int getTotalePezzi() {
		return totalePezzi;
	}

	public HashMap<Integer, RiepilogoGiornalieroPerFaseCDG> getMappaFaseTotali() {
		return mappaFaseTotali;
	}

	public void aggiungiElemento(ElementoBilancioCDG elemento) {
		boolean add = elementi.add(elemento);
		if (add) {
			//Se la fase è quella degli extra lo metto a parte
			if (elemento.getFase() == CdgFase.ID_EXTRA)
				this.costiExtra += elemento.getCostoReale();
			this.costiReali += elemento.getCostoReale();
			this.costiIpotetici += elemento.getCostoIpotetico();
			this.ricaviIpotetici += elemento.getRicavoIpotetico();
			this.totaleSecondi += elemento.getSecondi() != null ? elemento.getSecondi() : 0;
			this.totalePezzi += elemento.getPezzi() != null ? elemento.getPezzi() : 0;
			if (!mappaFaseTotali.containsKey(elemento.getFase())) {
				mappaFaseTotali.put(elemento.getFase(), new RiepilogoGiornalieroPerFaseCDG(elemento.getFase()));
			}
			RiepilogoGiornalieroPerFaseCDG totaliPerFase = mappaFaseTotali.get(elemento.getFase());
			totaliPerFase.aggiungiElemento(elemento);
		}
	}

	public List<ElementoBilancioCDG> getElementi() {
		return elementi;
	}

	public Date getGiorno() {
		return giorno;
	}

	@Override
	public String toString() {
		return "RiepilogoGiornalieroCDG [giorno=" + giorno + ", costiExtra=" + costiExtra
				+ ", costiReali=" + costiReali + ", costiIpotetici=" + costiIpotetici + ", ricaviIpotetici="
				+ ricaviIpotetici + ", totaleSecondi=" + totaleSecondi + ", totalePezzi=" + totalePezzi + "]";
	}

}

package it.ltc.logica.cdg.gui.analisi.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Modella in maniera sommaria il riepilogo sulla fase.
 * @author Damiano
 *
 */
public class RiepilogoGiornalieroPerFaseCDG {
	
	private final int fase;
	
	protected double costiReali;
	protected double costiIpotetici;
	protected double ricaviIpotetici;
	
	protected int totalePezzi;
	protected int totaleSecondi;
	
	private final List<ElementoBilancioCDG> elementi;
	
	public RiepilogoGiornalieroPerFaseCDG(int fase) {
		this.fase = fase;
		this.elementi = new LinkedList<>();
	}
	
	public void aggiungiElemento(ElementoBilancioCDG elemento) {
		elementi.add(elemento);
		costiReali += elemento.getCostoReale();
		costiIpotetici += elemento.getCostoIpotetico();
		ricaviIpotetici += elemento.getRicavoIpotetico();
		
		totalePezzi += elemento.getPezzi() != null ? elemento.getPezzi() : 0;
		totaleSecondi += elemento.getSecondi() != null ? elemento.getSecondi() : 0;
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

	public int getFase() {
		return fase;
	}
	
	public int getTotalePezzi() {
		return totalePezzi;
	}

	public int getTotaleSecondi() {
		return totaleSecondi;
	}

	public double getScostamentoTempi() {
		return Math.abs(costiReali - costiIpotetici);
	}
	
	public boolean isPerditaTempi() {
		return costiReali > costiIpotetici;
	}
	
	public double getScostamentoCDG() {
		return Math.abs(costiReali - ricaviIpotetici);
	}
	
	public boolean isPerditaCDG() {
		return costiReali > ricaviIpotetici;
	}

}

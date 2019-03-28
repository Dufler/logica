package it.ltc.logica.utilities.chart;

import org.jfree.data.general.DefaultPieDataset;

public class DatiGraficoTorta {

	private final String titolo;
	
	private final DefaultPieDataset dataset;
	
	public DatiGraficoTorta(String title) {
		titolo = title;
		dataset = new DefaultPieDataset();
	}
	
	public void aggiungiValore(String chiave, double valore) {
		 dataset.setValue(chiave, valore);
	}
	
	public DefaultPieDataset getDati() {
		return dataset;
	}

	public String getTitolo() {
		return titolo;
	}

}

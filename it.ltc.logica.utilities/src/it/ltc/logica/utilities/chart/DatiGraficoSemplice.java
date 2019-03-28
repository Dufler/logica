package it.ltc.logica.utilities.chart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;

public class DatiGraficoSemplice {
	
	private final String titolo;
	private final String ascisse;
	private final String ordinate;
	
	private DefaultCategoryDataset dataset;
	
	public DatiGraficoSemplice(String title, String x, String y) {
		dataset = new DefaultCategoryDataset();
		titolo = title;
		ascisse = x;
		ordinate = y;
	}
	
	public void aggiungiValore(double value, String riga, String colonna) {
		 dataset.addValue(value, riga, colonna);
	}
	
	public void aggiungiGruppo(String nome) {
		DatasetGroup group = new DatasetGroup(nome);
		dataset.setGroup(group);
	}
	
	public CategoryDataset getDati() {
		return dataset;
	}

	public String getTitolo() {
		return titolo;
	}
	
	public String getNomeAscisse() {
		return ascisse;
	}
	
	public String getNomeOrdinate() {
		return ordinate;
	}

}

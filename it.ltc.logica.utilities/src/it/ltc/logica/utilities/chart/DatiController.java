package it.ltc.logica.utilities.chart;

import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import it.ltc.logica.utilities.excel.FileExcel;

public class DatiController {
	
	private static DatiController instance;

	private DatiController() {}

	public static synchronized DatiController getInstance() {
		if (null == instance) {
			instance = new DatiController();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public boolean esportaDatiGraficoSuExcel(String path, String titoloReport, DatiGraficoSemplice dati) {
		String nomeFile = path + "/" + titoloReport + ".xls";
		FileExcel workbook = FileExcel.getFileExcel(nomeFile);
		boolean successo = false;
		if (workbook != null) {
			workbook.creaFoglio(titoloReport);
			workbook.aggiungiTitolo(titoloReport, 0, 0, titoloReport);
			
			DefaultCategoryDataset dataset = (DefaultCategoryDataset) dati.getDati();
			//Scrivo i nomi delle colonne
			List<String> columns = dataset.getColumnKeys();
			int rigaColonne = 2;
			int colonnaColonne = 1;
			for (String key : columns) {
				workbook.aggiungiTesto(titoloReport, colonnaColonne, rigaColonne, key);
				colonnaColonne += 1;
			}
			//Scrivo i nomi delle righe
			List<String> rows = dataset.getRowKeys();
			int rigaRighe = 3;
			int colonnaRighe = 0;
			for (String key : rows) {
				workbook.aggiungiTesto(titoloReport, colonnaRighe, rigaRighe, key);
				rigaRighe += 1;
			}
			//Scrivo i valori
			int offsetRiga = 3;
			int offsetColonna = 1;
			for (int riga = 0; riga < dataset.getRowCount(); riga ++) {
				for (int colonna = 0; colonna < dataset.getColumnCount(); colonna++)
					workbook.aggiungiDouble(titoloReport, colonna + offsetColonna, riga + offsetRiga, dataset.getValue(riga, colonna).doubleValue());
			}
			successo = workbook.salvaEChiudi();
		}
		return successo;
	}

}

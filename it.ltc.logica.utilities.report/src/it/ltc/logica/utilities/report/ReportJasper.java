package it.ltc.logica.utilities.report;

import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public enum ReportJasper {
	
	//Path di produzione
	SPEDIZIONI("/it/ltc/logica/utilities/report/jrxml/spedizioniGenerico.jrxml"),
	

	ORDINE_LISTA_PRELIEVO_PDF("/it/ltc/logica/utilities/report/jrxml/ordine/ordinePrelievo_pdf.jrxml"),
	ORDINE_LISTA_SCORTE_PDF("/it/ltc/logica/utilities/report/jrxml/ordine/ordineScorte_pdf.jrxml"),
	ORDINE_LISTA_NON_UBICATI_PDF("/it/ltc/logica/utilities/report/jrxml/ordine/ordineNonUbicati_pdf.jrxml"),
	ORDINE_LISTA_NON_DISPONIBILI_PDF("/it/ltc/logica/utilities/report/jrxml/ordine/ordineNonDisponibili_pdf.jrxml"),
	
	CARICO_SEMPLICE_PDF("/it/ltc/logica/utilities/report/jrxml/carico/caricoSemplice_pdf.jrxml"),
	CARICO_SEMPLICE_EXCEL("/it/ltc/logica/utilities/report/jrxml/carico/caricoSemplice_xls.jrxml"),
	CARICO_PER_COLLO_PDF("/it/ltc/logica/utilities/report/jrxml/carico/caricoPerCollo_pdf.jrxml");
	
	//Path di test
//	ORDINE_LISTA_PRELIEVO_PDF("C:\\Users\\Damiano\\Desktop\\report\\ordinePrelievo_pdf.jrxml"),
//	SPEDIZIONI("C:\\Users\\Damiano\\Desktop\\report\\spedizioniGenerico.jrxml"),
//	CARICO_SEMPLICE("C:\\Users\\Damiano\\Desktop\\report\\caricoSemplice.jrxml"),
//	CARICO_PER_COLLO("C:\\Users\\Damiano\\Desktop\\report\\caricoPerCollo.jrxml");
	
	//private JasperDesign report;
	private final String path;
	
	private ReportJasper(String path) {
		this.path = path;
	}

	//Metodo di produzione
	public JasperDesign getReport() {
		JasperDesign report;
		try (InputStream stream = ReportJasper.class.getResourceAsStream(path)) {
			report = JRXmlLoader.load(stream);
		} catch (JRException | IOException e) {
			report = null;
			e.printStackTrace();
		}
		return report;
	}
	
	//Metodo di test
//	public JasperDesign getReport() {
//		JasperDesign report;
//		try (InputStream stream = new FileInputStream(path)) {
//			report = JRXmlLoader.load(stream);
//		} catch (JRException | IOException e) {
//			report = null;
//			e.printStackTrace();
//		}
//		return report;
//	}

}

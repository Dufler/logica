package it.ltc.logica.utilities.report;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * Classe con metodi statici che costruisce i report.
 * @author Damiano
 *
 */
public class JasperReportBuilder {
	
	private static final String connectionString = "jdbc:sqlite:C:/logica/db/logica.db";
	
	/**
	 * Metodo statico utilizzato per costruire i report pdf.
	 * @param reportInfo il percorso completo di nome dove si trova il file .jasper che contiene le informazioni necessarie a costruire il report. 
	 * @param exportPath il percorso completo di nome dove andare a salvare il pdf.
	 * @param parameters una mappa con chiavi stringhe e oggetti valori necessarie a parametrizzare il report.
	 * @throws Exception Il metodo lancia eccezioni nel caso in cui non riesce ad acquisire dati dal DB oppure ci sono problemi nel compilare il report.
	 */
	public static void buildReportPDF(String reportInfo, String exportPath, HashMap<String, Object> parameters) throws Exception {
		Connection connection = getConnection();
		//Non è necessario ricompilare i report ogni volta. La riga seguente è stata commentata.
		//JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportInfo, parameters, connection);
		JasperExportManager.exportReportToPdfFile(jasperPrint, exportPath);
	}
	
	public static void buildReportXLSX(String reportInfo, String exportPath, HashMap<String, Object> parameters) throws Exception {
		Connection connection = getConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportInfo, parameters, connection);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		File outputFile = new File(exportPath);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
		//Impostazioni sulla configurazione da inserire qui, vanno testate.
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}
	
	private static Connection getConnection() throws Exception {
		Connection connection;
		connection = DriverManager.getConnection(connectionString);
		return connection;
	}
	
	public static void buildReportPDF(ReportJasper report, String exportPath, HashMap<String, Object> parameters, Collection<?> data) throws Exception {
		JasperDesign jasperDesign = report.getReport();
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, exportPath);
	}
	
	public static void buildReportXLSX(ReportJasper report, String exportPath, HashMap<String, Object> parameters, Collection<?> data) throws Exception {
		JasperDesign jasperDesign = report.getReport();
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		File outputFile = new File(exportPath);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
		//Impostazioni sulla configurazione da inserire qui, vanno testate.
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
	}

}

package it.ltc.logica.utilities.report.print;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class Printer {

	public static boolean printDocument(String filePath) {
		boolean printed;
		try (InputStream is = new BufferedInputStream(new FileInputStream(filePath))) {
			// create a PDF doc flavor
			//DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
			// Locate the default print service for this environment.
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			// Create and return a PrintJob capable of handling data from any of the supported document flavors.
			DocPrintJob printJob = service.createPrintJob();
			// register a listener to get notified when the job is complete
			printJob.addPrintJobListener(new PrintJob());
			// Construct a SimpleDoc with the specified print data, doc flavor and doc attribute set.
			Doc doc = new SimpleDoc(is, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			// Print a document with the specified job attributes.
			printJob.print(doc, null);
			printed = true;
		} catch (Exception e) {
			printed = false;
			e.printStackTrace();
		}
		return printed;
	}

}

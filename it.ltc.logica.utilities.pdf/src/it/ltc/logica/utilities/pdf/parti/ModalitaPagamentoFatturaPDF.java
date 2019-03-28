package it.ltc.logica.utilities.pdf.parti;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import it.ltc.logica.utilities.pdf.ElementoPDF;
import it.ltc.logica.utilities.pdf.elementi.BoxPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoMultilineaPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoSemplicePDF;
import it.ltc.logica.utilities.pdf.stili.StileTestoPDF;

public class ModalitaPagamentoFatturaPDF extends ElementoPDF {
	
	private static final float margine = 25;
	private static final float margineTesto = 10;
	private static final float gap = 15;
	
	private static final float altezzaRiquadri = 100;
	private static final float larghezzaRiquadroDati = 200;
	
	private final TestoSemplicePDF testoGrassettoModalita;
	private final TestoSemplicePDF testoModalita;
	private final TestoMultilineaPDF testoCoordinate;
	
	private final TestoSemplicePDF testoImponibile;
	private final TestoSemplicePDF testoIva;
	private final TestoSemplicePDF testoTotale;
	
	private final BoxPDF riquadroModalita;
	private final BoxPDF riquadroTotali;
	
//	private final float altezza;
//	private final float larghezza;
	
	public ModalitaPagamentoFatturaPDF(float y, String modalitaPagamento, String coordinatePagamento, String imponibile, String iva, String totale) {
		float yTesto = y - margine;
		float xRiquadroTotali = PDRectangle.A4.getWidth() - margine - larghezzaRiquadroDati;
		testoGrassettoModalita = new TestoSemplicePDF("MODALITA' DI PAGAMENTO:", margine + margineTesto, yTesto, StileTestoPDF.HELVETICA_12_BOLD);
		testoImponibile = new TestoSemplicePDF("Imponibile: " + imponibile, xRiquadroTotali + margineTesto, yTesto, StileTestoPDF.HELVETICA_12);
		yTesto -= gap;
		testoModalita = new TestoSemplicePDF(modalitaPagamento, margine + margineTesto, yTesto, StileTestoPDF.HELVETICA_12);
		testoIva = new TestoSemplicePDF("IVA: " + iva, xRiquadroTotali + margineTesto, yTesto, StileTestoPDF.HELVETICA_12);
		yTesto -= gap;
		float maxWidth = xRiquadroTotali - margine - margineTesto * 2;
		testoCoordinate = new TestoMultilineaPDF(coordinatePagamento, margine + margineTesto, yTesto, maxWidth, gap, StileTestoPDF.HELVETICA_12);
		testoTotale = new TestoSemplicePDF("Tot. Fattura: " +  totale, xRiquadroTotali + margineTesto, yTesto, StileTestoPDF.HELVETICA_12);
		riquadroModalita = new BoxPDF(margine, y - altezzaRiquadri, xRiquadroTotali - margine, altezzaRiquadri, Color.BLACK);
		riquadroTotali = new BoxPDF(xRiquadroTotali, y - altezzaRiquadri, larghezzaRiquadroDati, altezzaRiquadri, Color.BLACK);
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		testoGrassettoModalita.aggiungi(stream);
		testoModalita.aggiungi(stream);
		testoCoordinate.aggiungi(stream);
		testoImponibile.aggiungi(stream);
		testoIva.aggiungi(stream);
		testoTotale.aggiungi(stream);
		riquadroModalita.aggiungi(stream);
		riquadroTotali.aggiungi(stream);
	}

}

package it.ltc.logica.utilities.pdf.parti;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import it.ltc.logica.utilities.pdf.ElementoPDF;
import it.ltc.logica.utilities.pdf.elementi.BoxPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoMultilineaPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoSemplicePDF;
import it.ltc.logica.utilities.pdf.stili.StileTestoPDF;

public class TabellaFattura extends ElementoPDF {
	
	private static final String testoConai = "CONTRIBUTO CONAI ASSOLTO OVE DOVUTO.";
	
	private static final float margine = 25;
	private static final float altezzaTabella = 380;
	private static final float altezzaHeader = 20;
	private static final float gapRighe = 20;
	
	private static final float xColonna1 = 80;
	private static final float xColonna2 = 345;
	private static final float xColonna3 = 382;
	private static final float xColonna4 = 422;
	private static final float xColonna5 = 498;
	
	private final BoxPDF riquadroColonnaCodice;
	private final BoxPDF riquadroColonnaDescrizione;
	private final BoxPDF riquadroColonnaQuantita;
	private final BoxPDF riquadroColonnaIVA;
	private final BoxPDF riquadroColonnaPrezzo;
	private final BoxPDF riquadroColonnaImporto;
	private final BoxPDF riquadroHeaderTabella;
	
	private final RigaTabellaFattura header;
	private final List<RigaTabellaFattura> righeFattura;
	
	private final TestoMultilineaPDF noteFattura;
	private final TestoSemplicePDF dicituraConai;
	
	public TabellaFattura(float y, List<String[]> righe, String note) {
		float width = PDRectangle.A4.getWidth() - margine * 2;
		riquadroColonnaCodice = new BoxPDF(margine, y - altezzaTabella, xColonna1 - margine, altezzaTabella, Color.BLACK);
		riquadroColonnaDescrizione = new BoxPDF(xColonna1, y - altezzaTabella, xColonna2 - xColonna1, altezzaTabella, Color.BLACK);
		riquadroColonnaQuantita = new BoxPDF(xColonna2, y - altezzaTabella, xColonna3 - xColonna2, altezzaTabella, Color.BLACK);
		riquadroColonnaIVA = new BoxPDF(xColonna3, y - altezzaTabella, xColonna4 - xColonna3, altezzaTabella, Color.BLACK);
		riquadroColonnaPrezzo = new BoxPDF(xColonna4, y - altezzaTabella, xColonna5 - xColonna4, altezzaTabella, Color.BLACK);
		riquadroColonnaImporto = new BoxPDF(xColonna5, y - altezzaTabella, PDRectangle.A4.getWidth() - margine - xColonna5, altezzaTabella, Color.BLACK);
		riquadroHeaderTabella = new BoxPDF(margine, y - altezzaHeader, width, altezzaHeader, Color.BLACK);
		header = new RigaTabellaFattura(y - altezzaHeader + 5, "Codice", "Descrizione", "Q.ta", "IVA", "Prezzo", "Importo", StileTestoPDF.HELVETICA_12_BOLD);
		righeFattura = new LinkedList<>();
		float yRiga = y -altezzaHeader - gapRighe;
		for (String[] riga : righe) {
			RigaTabellaFattura rigaTabella = new RigaTabellaFattura(yRiga, riga[0], riga[1], riga[2], riga[3], riga[4], riga[5], StileTestoPDF.HELVETICA_10);
			righeFattura.add(rigaTabella);
			float distanza = rigaTabella.getAltezzaRiga() > gapRighe ? rigaTabella.getAltezzaRiga() : gapRighe;
			yRiga -= distanza;
		}
		noteFattura = note != null ? new TestoMultilineaPDF(note, xColonna1 + 10, y - altezzaTabella + 50, xColonna2 - xColonna1, 15, StileTestoPDF.HELVETICA_12) : null;
		dicituraConai = new TestoSemplicePDF(testoConai, xColonna1 + 10, y - altezzaTabella + 15, StileTestoPDF.HELVETICA_10);
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		riquadroColonnaCodice.aggiungi(stream);
		riquadroColonnaDescrizione.aggiungi(stream);
		riquadroColonnaQuantita.aggiungi(stream);
		riquadroColonnaIVA.aggiungi(stream);
		riquadroColonnaPrezzo.aggiungi(stream);
		riquadroColonnaImporto.aggiungi(stream);
		riquadroHeaderTabella.aggiungi(stream);
		header.aggiungi(stream);
		for (RigaTabellaFattura riga : righeFattura)
			riga.aggiungi(stream);
		if (noteFattura != null)
			noteFattura.aggiungi(stream);
		dicituraConai.aggiungi(stream);
	}

	public float getAltezza() {
		return altezzaTabella;
	}

}

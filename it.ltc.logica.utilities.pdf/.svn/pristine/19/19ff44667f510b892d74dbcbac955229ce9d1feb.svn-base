package it.ltc.logica.utilities.pdf.elementi;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import it.ltc.logica.utilities.pdf.ElementoPDF;
import it.ltc.logica.utilities.pdf.stili.StileTestoPDF;

public class TestoMultilineaPDF extends ElementoPDF {
	
	private final List<TestoSemplicePDF> righe;
	
	private final float yFineTesto;
	private final float altezza;
	private final float larghezza;
	
	public TestoMultilineaPDF(String text, float x, float y, float maxWidth, float gap, StileTestoPDF stile) {
		List<String> righeDiTesto = new LinkedList<>();
		String[] parole = text.split("\\s+");
		String rigaDiTesto = parole[0];
		for (int index = 1; index < parole.length; index++) {
			String parola = parole[index];
			if (stile.getWidth(rigaDiTesto + " " + parola) <= maxWidth) {
				rigaDiTesto += " " + parola;
			} else {
				righeDiTesto.add(rigaDiTesto);
				rigaDiTesto = parola;
			}
		}
		//Al termine aggiungo l'ultima riga rimasta.
		righeDiTesto.add(rigaDiTesto);
		String[] testo = righeDiTesto.toArray(new String[righeDiTesto.size()]);
		//Procedo come nel caso semplice.
		righe = new LinkedList<>();
		float currentY = y;
		float larghezzaMassima = 0;
		for (String linea : testo) {
			TestoSemplicePDF riga = new TestoSemplicePDF(linea, x, currentY, stile);
			if (larghezzaMassima < riga.getWidth())
				larghezzaMassima = riga.getWidth();
			currentY -= gap;
			righe.add(riga);
		}
		yFineTesto = currentY + gap;
		altezza = testo.length * gap;
		larghezza = larghezzaMassima;
	}
	
	public TestoMultilineaPDF(String[] testo, float x, float y, float gap, StileTestoPDF stile, boolean toBottom) {
		righe = new LinkedList<>();
		float currentY = y;
		float larghezzaMassima = 0;
		for (String linea : testo) {
			TestoSemplicePDF riga = new TestoSemplicePDF(linea, x, currentY, stile);
			if (larghezzaMassima < riga.getWidth())
				larghezzaMassima = riga.getWidth();
			currentY = toBottom ? currentY + gap : currentY - gap;
			righe.add(riga);
		}
		yFineTesto = currentY + gap;
		altezza = testo.length * gap;
		larghezza = larghezzaMassima;
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		for (TestoSemplicePDF riga : righe)
			riga.aggiungi(stream);
	}

	public float getyFineTesto() {
		return yFineTesto;
	}

	public float getLarghezza() {
		return larghezza;
	}

	public float getAltezza() {
		return altezza;
	}

}

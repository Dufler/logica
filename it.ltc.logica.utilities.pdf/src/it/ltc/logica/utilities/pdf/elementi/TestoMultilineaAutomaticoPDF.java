package it.ltc.logica.utilities.pdf.elementi;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import it.ltc.logica.utilities.pdf.ElementoPDF;
import it.ltc.logica.utilities.pdf.stili.StileTestoPDF;

public class TestoMultilineaAutomaticoPDF extends ElementoPDF {
	
	private final TestoMultilineaPDF testo;
	
	public TestoMultilineaAutomaticoPDF(String text, float x, float y, float maxWidth, float gap, StileTestoPDF stile) {
		List<String> righe = new LinkedList<>();
		String[] parole = text.split("\\s+");
		String riga = parole[0];
		for (int index = 1; index < parole.length; index++) {
			String parola = parole[index];
			if (stile.getWidth(riga + " " + parola) <= maxWidth) {
				riga += " " + parola;
			} else {
				righe.add(riga);
				riga = parola;
			}
		}
		testo = new TestoMultilineaPDF(righe.toArray(new String[righe.size()]), x, y, gap, stile, false);
	}

	@Override
	public void aggiungi(PDPageContentStream stream) throws IOException {
		testo.aggiungi(stream);
	}

}

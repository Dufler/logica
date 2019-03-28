package it.ltc.logica.common.controller.fatturazione.documento;

import java.util.HashMap;

import it.ltc.logica.utilities.pdf.DocumentoPDF;
import it.ltc.logica.utilities.pdf.PaginaPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoMultilineaPDF;
import it.ltc.logica.utilities.pdf.elementi.TestoSemplicePDF;
import it.ltc.logica.utilities.pdf.stili.StileTestoPDF;

public class PaginaLegendaFatturazionePDF extends PaginaPDF {
	
	private static final float offsetIniziale = 40;
	private static final float margine = 20;
	private static final float gap = 13;
	
	private final TestoSemplicePDF titolo;
	private final TestoSemplicePDF sottoTitolo;
	private final TestoMultilineaPDF righeLegenda;

	public PaginaLegendaFatturazionePDF(DocumentoPDF documento, HashMap<String, String> legenda) {
		super(documento, false);
		
		String[] valoriLegenda = new String[legenda.size()];
		int index = 0;
		for (String key : legenda.keySet()) {
			String value = legenda.get(key);
			valoriLegenda[index] = value + ": " + key;
			index += 1;
		}
		
		float yTitolo = getHeight() - offsetIniziale;
		float ySottoTitolo = yTitolo - 2 * gap;
		float yLegenda = ySottoTitolo - 2 * gap;
		titolo = new TestoSemplicePDF("Legenda Colonne", margine, yTitolo, StileTestoPDF.TITOLO);
		aggiungiElemento(titolo);
		sottoTitolo = new TestoSemplicePDF("Voci di listino", margine, ySottoTitolo, StileTestoPDF.HELVETICA_12_BOLD);
		aggiungiElemento(sottoTitolo);
		righeLegenda = new TestoMultilineaPDF(valoriLegenda, margine, yLegenda, gap, StileTestoPDF.HELVETICA_10_BOLD, true);
		aggiungiElemento(righeLegenda);
	}

}

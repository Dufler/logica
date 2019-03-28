package it.ltc.logica.ufficio.gui.uscite.elements.generamovimenti;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.RisultatoGenerazioneMovimenti;
import it.ltc.logica.gui.elements.tree.Albero;

public class TabellaProblemiGenerazioneOrdine extends Albero<RisultatoGenerazioneMovimenti> {

	public TabellaProblemiGenerazioneOrdine(Composite parent) {
		super(parent, new GenerazioneMovimentiProvider());
	}

	@Override
	protected void impostaColonne() {
		aggiungiColonna("Lista", new GenerazioneMovimentiOrdineEtichettatore(1));
		aggiungiColonna("Riferimento", new GenerazioneMovimentiOrdineEtichettatore(2));
		aggiungiColonna("Esito", new GenerazioneMovimentiOrdineEtichettatore(3));
	}
	
	@Override
	protected String getTreeText() {
		StringBuilder textData = new StringBuilder();
		//Aggiungo i nomi delle colonne
		int columns = tree.getColumnCount();
		for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
			textData.append(tree.getColumn(columnIndex).getText());
			textData.append(TAB);
		}
		textData.setLength(textData.length() - 1);
		textData.append(NEW_LINE);
		//Aggiungo il contenuto selezionato
		for (RisultatoGenerazioneMovimenti item : getElementi()) {
			if (item.getMessaggi().isEmpty()) {
				textData.append(item.getOrdine().getNumeroLista()); textData.append(TAB);
				textData.append(item.getOrdine().getRiferimento()); textData.append(TAB);
				textData.append("Ok!"); textData.append(TAB);
				textData.append(NEW_LINE);
			} else for (String problema : item.getMessaggi()) {
				textData.append(item.getOrdine().getNumeroLista()); textData.append(TAB);
				textData.append(item.getOrdine().getRiferimento()); textData.append(TAB);
				textData.append(problema); textData.append(TAB);
				textData.append(NEW_LINE);
			}
		}
		textData.setLength(textData.length() - 1);
		return textData.toString();
	}

}

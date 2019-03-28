package it.ltc.logica.ufficio.gui.uscite.elements.finalizzazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.ProblemaFinalizzazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoFinalizzazioneOrdine;
import it.ltc.logica.gui.elements.tree.Albero;

public class TabellaProblemiFinalizzazioneOrdine extends Albero<RisultatoFinalizzazioneOrdine> {

	public TabellaProblemiFinalizzazioneOrdine(Composite parent) {
		super(parent, new FinalizzazioneProvider());
	}

	@Override
	protected void impostaColonne() {
		aggiungiColonna("Lista", new FinalizzazioneOrdineEtichettatore(1));
		aggiungiColonna("Riferimento", new FinalizzazioneOrdineEtichettatore(2));
		
		aggiungiColonna("Riga", new FinalizzazioneOrdineEtichettatore(3));
		aggiungiColonna("Prodotto", new FinalizzazioneOrdineEtichettatore(4));
		aggiungiColonna("Taglia", new FinalizzazioneOrdineEtichettatore(5));
		aggiungiColonna("Descrizione", new FinalizzazioneOrdineEtichettatore(6));
		aggiungiColonna("Disponibile", new FinalizzazioneOrdineEtichettatore(7));
		aggiungiColonna("Richiesta", new FinalizzazioneOrdineEtichettatore(8));
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
		for (RisultatoFinalizzazioneOrdine item : getElementi()) {
			for (ProblemaFinalizzazioneOrdine problema : item.getProblemi()) {
				//Inserisco tutti valori delle colonne
				textData.append(item.getOrdine().getNumeroLista()); textData.append(TAB);
				textData.append(item.getOrdine().getRiferimento()); textData.append(TAB);
				textData.append(problema.getIdRiga()); textData.append(TAB);
				textData.append(problema.getSku()); textData.append(TAB);
				textData.append(problema.getTaglia()); textData.append(TAB);
				textData.append(problema.getDescrizione()); textData.append(TAB);
				textData.append(problema.getQuantitaDisponibile()); textData.append(TAB);
				textData.append(problema.getQuantitaRichiesta());
				//A capo
				textData.append(NEW_LINE);
			}
		}
		textData.setLength(textData.length() - 1);
		return textData.toString();
	}

}

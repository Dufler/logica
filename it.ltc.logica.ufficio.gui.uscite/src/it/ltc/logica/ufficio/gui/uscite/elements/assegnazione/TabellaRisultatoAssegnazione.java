package it.ltc.logica.ufficio.gui.uscite.elements.assegnazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.gui.elements.tree.Albero;


public class TabellaRisultatoAssegnazione extends Albero<RisultatoAssegnazioneOrdine> {

	public TabellaRisultatoAssegnazione(Composite parent) {
		super(parent, new RisultatoProvider());
	}

	@Override
	protected void impostaColonne() {
		aggiungiColonna("Lista", new RisultatoEtichettatore(1));
		aggiungiColonna("Riferimento", new RisultatoEtichettatore(2));
		aggiungiColonna("Stato", new RisultatoEtichettatore(3));
		
		aggiungiColonna("Riga", new RisultatoEtichettatore(4));
		aggiungiColonna("Richiesti", new RisultatoEtichettatore(8));
		aggiungiColonna("Assegnati", new RisultatoEtichettatore(9));
		aggiungiColonna("Prodotto", new RisultatoEtichettatore(5));
		aggiungiColonna("Taglia", new RisultatoEtichettatore(6));
		aggiungiColonna("Descrizione", new RisultatoEtichettatore(7));
		aggiungiColonna("Quantit\u00E0 Prelievo", new RisultatoEtichettatore(13));
		aggiungiColonna("Assegnazione", new RisultatoEtichettatore(10));
		aggiungiColonna("Collo", new RisultatoEtichettatore(11));
		aggiungiColonna("Ubicazione", new RisultatoEtichettatore(12));
	}

}

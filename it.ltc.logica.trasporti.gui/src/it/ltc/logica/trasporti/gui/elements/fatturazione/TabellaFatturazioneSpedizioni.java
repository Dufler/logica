package it.ltc.logica.trasporti.gui.elements.fatturazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.EtichettatoreFatturazioneModel;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaFatturazioneSpedizioni extends TabellaFatturazione {

	public TabellaFatturazioneSpedizioni(Composite parent, ListinoCommessa listino, boolean modify) {
		super(parent, listino, modify);
		
		aggiungiColonna("Data", 100, 0);
		
		aggiungiColonna("LDV", 100, 12);
				
		aggiungiColonna("Destinatario", 100, 1);
		aggiungiColonna("Localit\u00E0", 100, 2);
		aggiungiColonna("Riferimento", 100, 3);
		
		aggiungiColonna("Contrassegno", 85, 15);
		
		aggiungiColonna("Pezzi", 60, 6);
		aggiungiColonna("Colli", 60, 7);
		aggiungiColonna("Peso e Volume", 120, 8);
		
		aggiungiColonna("Totale", 100, new EtichettatoreFatturazioneModel(), 4);
		
		if (listino != null) for (VoceDiListino voce : controller.getVociDiListino(listino.getId())) {
			aggiungiColonna(voce.getNome(), 100, new EtichettatoreFatturazioneModel(voce), 5);
		}
	}
	
	@Override
	protected void aggiungiColonne() {
		//Viene fatto in base al listino passato al costruttore.
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_FATTURAZIONE_SPEDIZIONI.getID();
	}

}

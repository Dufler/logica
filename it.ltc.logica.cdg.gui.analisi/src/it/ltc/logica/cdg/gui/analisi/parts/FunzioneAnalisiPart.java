 
package it.ltc.logica.cdg.gui.analisi.parts;

import javax.inject.Inject;

import it.ltc.logica.gui.elements.FunzionePart;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class FunzioneAnalisiPart extends FunzionePart {
	
	private static final String label = "Analisi Centri di Costo e Ricavo";
	private static final String tooltip = "Qui puoi analizzare i dati ed effettuare il controllo di Gestione.";
	
	public static final String partID = "it.ltc.logica.cdg.gui.analisi.part.analisi";
	public static final String funzionePartID = "it.ltc.logica.cdg.gui.analisi.part.funzioneanalisi";
	
	@Inject
	public FunzioneAnalisiPart() {
		super(label, tooltip, funzionePartID, partID);
	}

	@Override
	public boolean isVisible() {
		boolean visible = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_ANALISI.getID());
		return visible;
	}
}
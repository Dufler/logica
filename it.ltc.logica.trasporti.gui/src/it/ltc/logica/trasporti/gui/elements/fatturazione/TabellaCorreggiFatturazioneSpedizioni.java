package it.ltc.logica.trasporti.gui.elements.fatturazione;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

public class TabellaCorreggiFatturazioneSpedizioni extends TabellaFatturazioneSpedizioni {

	private final DocumentoFattura documento;
	private final HashMap<Integer, List<VoceFattura>> mappaListaVoci;
	
	public TabellaCorreggiFatturazioneSpedizioni(Composite parent, ListinoCommessa listino, boolean modify, DocumentoFattura documento, HashMap<Integer, List<VoceFattura>> mappaListaVoci) {
		super(parent, listino, modify);
		
		this.documento = documento;
		this.mappaListaVoci = mappaListaVoci;
	}
	
	@Override
	protected void apriDialog(SpedizioneModel selezione) {
		if (selezione != null) {
			List<VoceFattura> voci = mappaListaVoci.get(selezione.getSpedizione().getId());
			DialogCorreggiFatturazioneModel dialog = new DialogCorreggiFatturazioneModel(selezione, true, documento, voci);
			int esito = dialog.open();
			if (esito == Dialog.OK) {
				refresh();
			}
		}
	}

}

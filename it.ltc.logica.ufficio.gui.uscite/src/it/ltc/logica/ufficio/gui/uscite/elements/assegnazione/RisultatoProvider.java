package it.ltc.logica.ufficio.gui.uscite.elements.assegnazione;

import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.gui.elements.tree.AlberoProvider;

public class RisultatoProvider extends AlberoProvider<RisultatoAssegnazioneOrdine> {

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		if (parentElement instanceof RisultatoAssegnazioneOrdine) {
			RisultatoAssegnazioneOrdine risultato = (RisultatoAssegnazioneOrdine) parentElement;
			children = risultato.getAssegnazioni().toArray();
		}
		return children;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof RisultatoAssegnazioneRigaOrdine) {
			//DO NOTHING!? Sembra funzionare ugualmente.
		} 
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof RisultatoAssegnazioneOrdine) {
			RisultatoAssegnazioneOrdine p = (RisultatoAssegnazioneOrdine) element;
			hasChildren = p.getAssegnazioni() != null && !p.getAssegnazioni().isEmpty();
		}
		return hasChildren;
	}

}

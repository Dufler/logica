package it.ltc.logica.ufficio.gui.uscite.elements.finalizzazione;

import java.util.List;

import it.ltc.logica.database.model.centrale.ordini.ProblemaFinalizzazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoFinalizzazioneOrdine;
import it.ltc.logica.gui.elements.tree.AlberoProvider;

public class FinalizzazioneProvider extends AlberoProvider<RisultatoFinalizzazioneOrdine> {

	@Override
	public Object[] getElements(Object inputElement) {
		List<?> elements = (List<?>) inputElement;
		return elements.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		if (parentElement instanceof RisultatoFinalizzazioneOrdine) {
			RisultatoFinalizzazioneOrdine p = (RisultatoFinalizzazioneOrdine) parentElement;
			children = p.getProblemi().toArray();
		}
		return children;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof ProblemaFinalizzazioneOrdine) {
			//DO NOTHING!? Sembra funzionare ugualmente.
		} 
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof RisultatoFinalizzazioneOrdine) {
			RisultatoFinalizzazioneOrdine p = (RisultatoFinalizzazioneOrdine) element;
			hasChildren = p.getProblemi() != null && !p.getProblemi().isEmpty();
		}
		return hasChildren;
	}

}

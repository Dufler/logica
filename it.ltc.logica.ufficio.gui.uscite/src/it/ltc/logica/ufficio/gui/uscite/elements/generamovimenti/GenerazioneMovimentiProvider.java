package it.ltc.logica.ufficio.gui.uscite.elements.generamovimenti;

import java.util.List;

import it.ltc.logica.database.model.centrale.ordini.RisultatoGenerazioneMovimenti;
import it.ltc.logica.gui.elements.tree.AlberoProvider;

public class GenerazioneMovimentiProvider extends AlberoProvider<RisultatoGenerazioneMovimenti> {

	@Override
	public Object[] getElements(Object inputElement) {
		List<?> elements = (List<?>) inputElement;
		return elements.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;
		if (parentElement instanceof RisultatoGenerazioneMovimenti) {
			RisultatoGenerazioneMovimenti risultato = (RisultatoGenerazioneMovimenti) parentElement;
			children = risultato.getMessaggi().toArray();
		}
		return children;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		return parent;
	}

	@Override
	public boolean hasChildren(Object parentElement) {
		boolean hasChildren = false;
		if (parentElement instanceof RisultatoGenerazioneMovimenti) {
			RisultatoGenerazioneMovimenti risultato = (RisultatoGenerazioneMovimenti) parentElement;
			hasChildren = !risultato.getMessaggi().isEmpty();
		}
		return hasChildren;
	}

}

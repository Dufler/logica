package it.ltc.logica.container.element;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

import it.ltc.logica.container.model.Modulo;
import it.ltc.logica.container.model.FunzioneModulo;

public class ProviderFunzioni implements ITreeContentProvider {

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result;
		if (inputElement instanceof List) {
			result = ((List<Object>) inputElement).toArray();
		} else {
			result = (Object[]) inputElement;
		}
		return result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] funzioni = null;
		if (parentElement instanceof Modulo) {
			Modulo feature = (Modulo) parentElement;
			funzioni = feature.getFunzioni().toArray();
		}	
		return funzioni;
	}

	@Override
	public Object getParent(Object element) {
		Object feature = null;
		if (element instanceof FunzioneModulo) {
			FunzioneModulo funzione = (FunzioneModulo) element;
			feature = funzione.getPadre();
		}
		return feature;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if (element instanceof Modulo) {
			Modulo feature = (Modulo) element;
			hasChildren = !feature.getFunzioni().isEmpty();
		}
		return hasChildren;
	}

}

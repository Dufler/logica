package it.ltc.logica.admin.gui.logic;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

import it.ltc.logica.common.controller.sistema.ControllerPermessi;
import it.ltc.logica.database.model.centrale.utenti.Permesso;

public class PermessiContentProvider implements ITreeContentProvider {

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
		Permesso permesso = (Permesso) parentElement;
		Object[] figli = permesso.getFigli().toArray();
		return figli;
	}

	@Override
	public Object getParent(Object element) {
		Permesso permesso = (Permesso) element;
		Integer idPadre = permesso.getIdPadre();
		Permesso padre = idPadre == null ? null : ControllerPermessi.getInstance().getPermesso(idPadre);
		return padre;
	}

	@Override
	public boolean hasChildren(Object element) {
		Permesso permesso = (Permesso) element;
		boolean figli = permesso.getFigli().size() > 0;
		return figli;
	}

}

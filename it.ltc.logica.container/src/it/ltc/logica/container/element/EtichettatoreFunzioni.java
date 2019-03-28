package it.ltc.logica.container.element;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;

import it.ltc.logica.container.model.FunzioneModulo;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;

public class EtichettatoreFunzioni extends LabelProvider implements IStyledLabelProvider {

	@Override
	public StyledString getStyledText(Object element) {
		StyledString text = new StyledString();
		if (element instanceof FunzioneModulo) {
			FunzioneModulo funzione = (FunzioneModulo) element;
			text.append(funzione.getNome());
		} else {
			text.append("");
		}
		return text;
	}
	
	@Override
    public Image getImage(Object element) {
		return null;
	}

}

package it.ltc.logica.container.element;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import it.ltc.logica.container.model.Modulo;

public class EtichettatoreFeature extends LabelProvider implements IStyledLabelProvider {

	@Override
	public StyledString getStyledText(Object element) {
		StyledString text = new StyledString();
		if (element instanceof Modulo) {
			Modulo feature = (Modulo) element;
			text.append(feature.getNome());
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

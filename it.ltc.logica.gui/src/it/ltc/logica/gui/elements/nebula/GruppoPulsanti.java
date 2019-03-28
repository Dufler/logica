package it.ltc.logica.gui.elements.nebula;

import org.eclipse.nebula.widgets.pgroup.PGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class GruppoPulsanti extends PGroup {
	
	protected final Composite composite;

	public GruppoPulsanti(Composite parent, Image image, String text) {
		super(parent, SWT.NONE);
		setImage(image);
		setText(text);
		composite = new Composite(this, SWT.NONE);
	}

}

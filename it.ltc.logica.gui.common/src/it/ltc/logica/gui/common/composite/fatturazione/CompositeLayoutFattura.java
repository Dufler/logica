package it.ltc.logica.gui.common.composite.fatturazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public abstract class CompositeLayoutFattura extends Gruppo {

	private final static String title = "Layout Allegato - ";
	
	protected final static String SEPARATOR = "|";
	protected final static String KEY_VALUE_SEPARATOR = "=";
	
	public CompositeLayoutFattura(ParentValidationHandler parentValidator, Composite parent, String subTitle) {
		super(parentValidator, parent, title + subTitle);
	}
	
	public abstract void setLayoutFatturazione(String layout);
	
	public abstract String getLayoutFatturazione();

}

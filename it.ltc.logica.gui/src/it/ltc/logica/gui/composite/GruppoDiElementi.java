package it.ltc.logica.gui.composite;

import org.eclipse.swt.widgets.Control;

import it.ltc.logica.gui.input.Element;

public interface GruppoDiElementi extends Element {
	
	/**
	 * Indica se il gruppo di elementi è stato compilato dall'utente.
	 * @return
	 */
	public boolean isDirty();
	
	/**
	 * Aggiunge un elemento che non può essere modificato.
	 * @param control
	 */
	public void addNonUpdatableElement(Control control);
	
	/**
	 * Imposta come disabilitati tutti i controlli non aggiornabili.
	 */
	public void lockNonUpdatableElements();
	
	/**
	 * Resetta tutti gli elementi di input.
	 */
	public void resetValues();

}

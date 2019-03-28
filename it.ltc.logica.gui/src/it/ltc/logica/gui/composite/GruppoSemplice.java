package it.ltc.logica.gui.composite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import it.ltc.logica.gui.input.InputElement;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.gui.validation.ValidationHandler;

/**
 * Oggetto simile ad un form, raccoglie elementi di input.
 * @author Damiano
 *
 */
public abstract class GruppoSemplice extends Composite implements ParentValidationHandler, GruppoDiElementi {
	
	protected boolean valid;
	protected boolean dirty;
	protected ParentValidationHandler successor;
	protected final LinkedList<ValidationHandler> children;
	protected final Set<Control> nonUpdatableElements;
	
	/**
	 * Costruttore di default.
	 * @param parent l'oggetto che ospiterà il gruppo.
	 */
	public GruppoSemplice(ParentValidationHandler parentValidator, Composite parent) {
		this(parentValidator, parent, SWT.NONE);
	}

	/**
	 * Costruttore che permette di specificare lo stile SWT da applicare al gruppo.
	 * @param parent l'oggetto che ospiterà il gruppo.
	 * @param style lo stile SWT da applicare
	 */
	public GruppoSemplice(ParentValidationHandler parentValidator, Composite parent, int style) {
		super(parent, style);
		if (parentValidator != null) {
			successor = (ParentValidationHandler) parentValidator;
			successor.addChild(this);
		} else {
			successor = null;
		}
		children = new LinkedList<ValidationHandler>();
		nonUpdatableElements = new HashSet<>();
		aggiungiElementiGrafici();
	}
	
	/**
	 * Questo metodo va implementato, viene richiamato per aggiungere gli elementi di input e grafici.
	 */
	public abstract void aggiungiElementiGrafici();
	
	public boolean isDirty() {
		dirty = false;
		for (Control child : getChildren()) {
			if (child instanceof InputElement) {
				InputElement i = (InputElement) child;
				dirty = i.isDirty();
			} else if (child instanceof GruppoDiElementi) {
				GruppoDiElementi g = (GruppoDiElementi) child;
				dirty = g.isDirty();
			}
			//Basta che uno sia "dirty" per terminare la verifica.
			if (dirty)
				break;
		}
		return dirty;
	}
	
	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public boolean validate() {
		valid = true;
		for (ValidationHandler child : children) {
			valid = child.isValid();
			if (!valid)
				break;
		}
		valid = validazioneSpecifica(valid);
		forwardValidation();
		return valid;
	}
	
	/**
	 * Metodo astratto da riscrevere nel caso di validazione specifiche.
	 * @return
	 */
	protected boolean validazioneSpecifica(boolean valid) {
		return valid;
	}

	@Override
	public void forwardValidation() {
		if (successor != null)
			successor.validate();
	}

	@Override
	public void setParent(ParentValidationHandler parent) {
		successor = parent;
	}

	@Override
	public void addChild(ValidationHandler child) {
		child.setParent(this);
		children.add(child);
	}
	
	@Override
	public void removeChild(ValidationHandler child) {
		child.setParent(null);
		children.remove(child);
	}
	
	public void addNonUpdatableElement(Control control) {
		nonUpdatableElements.add(control);
	}
	
	/**
	 * Abilita o disabilita tutti gli elementi di input.
	 * @param enable true -> abilita, false-> disabilita
	 */
	@Override
	public void enableElement(boolean enable) {
		for (Control child : getChildren()) {
			if (child instanceof InputElement) {
				if (!enable || !nonUpdatableElements.contains(child))
					child.setEnabled(enable);
			} else if (child instanceof GruppoDiElementi) {
				((GruppoDiElementi) child).enableElement(enable);
			}
		}
	}
	
	/**
	 * Imposta come disabilitati tutti i controlli non aggiornabili.
	 */
	public void lockNonUpdatableElements() {
		for (Control element : nonUpdatableElements)
			element.setEnabled(false);
	}
	
	@Override
	protected void checkSubclass() {
		//DO NOTHING!
	}
	
	/**
	 * Resetta tutti gli elementi di input.
	 */
	public void resetValues() {
		for (Control child : getChildren()) {
			if (child instanceof InputElement)
				((InputElement) child).resetValue();
			else if (child instanceof GruppoDiElementi)
				((GruppoDiElementi) child).resetValues();
		}
	}

}

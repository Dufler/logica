package it.ltc.logica.gui.composite;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import it.ltc.logica.gui.input.InputElement;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.gui.validation.ValidationHandler;

/**
 * Oggetto simile ad un form, raccoglie elementi di input.
 * E' possibile specificare un titolo.
 * @author Damiano
 *
 */
public abstract class Gruppo extends Group implements ParentValidationHandler, GruppoDiElementi {
	
	protected boolean valid;
	protected boolean dirty;
	protected ParentValidationHandler successor;
	protected final Set<ValidationHandler> children;
	protected final Set<Control> nonUpdatableElements;
	
	/**
	 * Costruttore di default.
	 * @param parent l'oggetto che ospiterà il gruppo.
	 * @param title il titolo da dare all'insieme di elementi.
	 */
	public Gruppo(ParentValidationHandler parentValidator, Composite parent, String title) {
		this(parentValidator, parent, SWT.NONE, title, null);
	}
	
	/**
	 * Costruttore con setup.
	 * @param parentValidator
	 * @param parent
	 * @param title
	 */
	public Gruppo(ParentValidationHandler parentValidator, Composite parent, String title, Object setupInfo) {
		this(parentValidator, parent, SWT.NONE, title, setupInfo);
	}

	/**
	 * Costruttore che permette di specificare lo stile SWT da applicare al gruppo.
	 * @param parent l'oggetto che ospiterà il gruppo.
	 * @param title il titolo da dare all'insieme di elementi.
	 * @param style lo stile SWT da applicare
	 */
	public Gruppo(ParentValidationHandler parentValidator, Composite parent, int style, String title, Object setupInfo) {
		super(parent, style);
		setText(title);
		if (parentValidator != null) {
			successor = (ParentValidationHandler) parentValidator;
			successor.addChild(this);
		} else {
			successor = null;
		}
		children = new HashSet<>();
		nonUpdatableElements = new HashSet<>();
		setup(setupInfo);
		aggiungiElementiGrafici();
	}
	
	/**
	 * Questo metodo va esteso per poter valorizzare variabili prima degli elementi grafici.
	 */
	public void setup(Object setupInfo) {
		//DO NOTHING!
	}
	
	/**
	 * Questo metodo va implementato, viene richiamato per aggiungere gli elementi di input e grafici.
	 * TODO: si protrebbe mettere un oggetto di configurazione (estendibile) come parametro in ingresso a questo metodo he contiene tutte le info necessarie alla configurazione (es. commessa) la maggior parte delle volte questo oggetto sarebbe null.
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
		forwardValidation();
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

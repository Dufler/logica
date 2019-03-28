package it.ltc.logica.gui.common.composite.fatturazione;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.gui.composite.GruppoSemplice;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class ElementoLayout extends GruppoSemplice {
	
	private final String nomeCampo;
	
	private Label label;
	private Bottone visibile;
	private TextForInteger larghezza;

	public ElementoLayout(ParentValidationHandler parentValidator, Composite parent, String nomeCampo) {
		super(parentValidator, parent);
		
		this.nomeCampo = nomeCampo;
		label.setText(nomeCampo);
	}
	
	public void setValoreLarghezza(Integer valoreLarghezza) {
		if (valoreLarghezza != null && valoreLarghezza > 0) {
			visibile.setSelection(true);
			larghezza.setValue(valoreLarghezza);
			larghezza.setRequired(true);
		} else {
			visibile.setSelection(false);
			larghezza.setEnabled(false);
			larghezza.setRequired(false);
		}
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(4, false));
		
		label = new Label(this, SWT.NONE);
		
		visibile = new Bottone(this, SWT.CHECK);
		visibile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean visible = visibile.getSelection();
				larghezza.setEnabled(visible);
				larghezza.setRequired(visible);
				larghezza.setFocus();
			}
		});
		
		larghezza = new TextForInteger(this);
		larghezza.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	@Override
	public String toString() {
		String key = nomeCampo + "=";
		String value = visibile.getSelection() ? larghezza.getText() : "-1";
		return  key + value;
	}

}


package it.ltc.logica.cdg.gui.costiricavi.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.elements.budget.TabellaBudget;
import it.ltc.logica.cdg.gui.costiricavi.elements.budget.ToolbarBudget;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

public class BudgetPart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		ToolbarBudget toolbar = new ToolbarBudget(parent);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaBudget tabella = new TabellaBudget(parent);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		toolbar.setTabella(tabella);
	}

}

package it.ltc.logica.admin.gui.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.admin.gui.elements.feature.TabellaFeature;
import it.ltc.logica.admin.gui.elements.feature.ToolBarFeature;
import it.ltc.logica.admin.gui.elements.funzione.TabellaFunzioni;
import it.ltc.logica.admin.gui.elements.funzione.ToolbarFunzioni;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

public class FeaturePart {
	
	private TabellaFeature tabellaFeature;
	private ToolBarFeature toolbarFeature;
	private TabellaFunzioni tabellaFunzioni;
	private ToolbarFunzioni toolbarFunzioni;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeControlliFeature = new Composite(parent, SWT.NONE);
		compositeControlliFeature.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeControlliFeature.setLayout(new GridLayout(2, false));
		
		Label lblFeatures = new Label(compositeControlliFeature, SWT.NONE);
		lblFeatures.setText("Features:");
		
		toolbarFeature = new ToolBarFeature(compositeControlliFeature);
		toolbarFeature.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		tabellaFeature = new TabellaFeature(parent);
		
		toolbarFeature.setTabella(tabellaFeature);
		
		Composite compositeControlliFunzioni = new Composite(parent, SWT.NONE);
		compositeControlliFunzioni.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeControlliFunzioni.setLayout(new GridLayout(2, false));
		
		Label lblFunzioni = new Label(compositeControlliFunzioni, SWT.NONE);
		lblFunzioni.setText("Funzioni: ");
		
		toolbarFunzioni = new ToolbarFunzioni(compositeControlliFunzioni);
		toolbarFunzioni.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		tabellaFunzioni = new TabellaFunzioni(parent);
		
		toolbarFunzioni.setTabella(tabellaFunzioni);
	}

}
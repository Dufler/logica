
package it.ltc.logica.container.trasporti.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.container.controller.Utenza;
import it.ltc.logica.container.element.style.ManagerStileFeatureFunzioni;
import it.ltc.logica.container.element.style.ManagerStileFeatureFunzioni.Style;
import it.ltc.logica.container.model.ControllerFeatureFunzioni;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class FunzioniPart {
	
	private final ManagerStileFeatureFunzioni styler;
	
	private Text textUtente;
	private Text textCommessa;
	
	@Inject
	public FunzioniPart(EPartService partService, MApplication app, EModelService modelService) {
		ControllerFeatureFunzioni.injectInstance(partService, app, modelService);
		styler = new ManagerStileFeatureFunzioni();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite compositeInfoUtente = new Composite(parent, SWT.BORDER);
		compositeInfoUtente.setLayout(new GridLayout(2, false));
		compositeInfoUtente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblUtente = new Label(compositeInfoUtente, SWT.NONE);
		lblUtente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUtente.setText("Utente: ");
		
		textUtente = new Text(compositeInfoUtente, SWT.BORDER);
		textUtente.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		textUtente.setEditable(false);
		textUtente.setEnabled(false);
		textUtente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textUtente.setText(getUtente());
		
		Label lblCommessa = new Label(compositeInfoUtente, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		textCommessa = new Text(compositeInfoUtente, SWT.BORDER);
		textCommessa.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		textCommessa.setEditable(false);
		textCommessa.setEnabled(false);
		textCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textCommessa.setText("Non selezionata");
		ControllerUtente.getInstance().setTextCommessa(textCommessa);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeMenu = new Composite(scrolledComposite, SWT.NONE);
		
		styler.setStyleAndFeatures(compositeMenu, Style.PGROUP);
		compositeMenu.setLayout(new GridLayout(1, false));
		
		scrolledComposite.setContent(compositeMenu);
		scrolledComposite.setMinSize(compositeMenu.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		//Altri possibili stili!
		
		//styler.setStyleAndFeatures(parent, Style.EXPANDBAR);
		//styler.setStyleAndFeatures(parent, Style.PSHELF);
		//styler.setStyleAndFeatures(parent, Style.TABFOLDER);
	}
	
	private String getUtente() {
		Utenza utente = ControllerUtente.getInstance().getUtente();
		String testo = utente != null ? utente.getNome() + " " + utente.getCognome() : "N/A (Contattare il CED)";
		return testo;
	}
}
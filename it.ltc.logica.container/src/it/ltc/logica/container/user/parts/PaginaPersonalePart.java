
package it.ltc.logica.container.user.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.container.composite.CompositeDatiUtente;
import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.container.controller.Utenza;

public class PaginaPersonalePart {
	
	public static final String partID = "it.ltc.logica.container.part.user.paginapersonale";
	
	private ControllerUtente controller;
	
	private CompositeDatiUtente compositeDatiUtente;

	@Inject
	public PaginaPersonalePart() {
		controller = ControllerUtente.getInstance();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite.widthHint = 116;
		composite.setLayoutData(gd_composite);

		Label lblTitle = new Label(composite, SWT.NONE);
		lblTitle.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblTitle.setText("Pagina Personale");
		
		compositeDatiUtente = new CompositeDatiUtente(null, composite);
		compositeDatiUtente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDatiUtente.enableElement(false);
		
		caricaModel();
	}
	
	private void caricaModel() {
		Utenza utente = controller.getUtente();
		if (utente != null) {
			compositeDatiUtente.setUsername(utente.getUsername());
			compositeDatiUtente.setNome(utente.getNome());
			compositeDatiUtente.setCognome(utente.getCognome());
			compositeDatiUtente.setEmail(utente.getEmail());
		}
	}

}
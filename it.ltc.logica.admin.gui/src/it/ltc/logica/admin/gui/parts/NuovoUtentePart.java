 
package it.ltc.logica.admin.gui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import it.ltc.logica.admin.gui.logic.ControllerUtenti;
import it.ltc.logica.gui.composite.GruppoSemplice;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.input.TextForString;

public class NuovoUtentePart {
	
	public static final String partID = "it.ltc.logica.admin.gui.part.nuovoutente";
	
	private ControllerUtenti controller;
	
	private GruppoSemplice compositeControlli;
	private TextForString textNome;
	private TextForString textCognome;
	private TextForString textEmail;
	private TextForString textUsername;
	
	@Inject
	public NuovoUtentePart() {
		controller = ControllerUtenti.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblNuovoUtente = new Label(parent, SWT.NONE);
		lblNuovoUtente.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblNuovoUtente.setText("Nuovo Utente");
		
		compositeControlli = new GruppoSemplice(null, parent, SWT.NONE) {
			
			private Button btnInserisci;

			@Override
			public void aggiungiElementiGrafici() {
				
				Label lblNome = new Label(this, SWT.NONE);
				lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblNome.setText("Nome: ");
				
				textNome = new TextForString(this);
				textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				Label lblCognome = new Label(this, SWT.NONE);
				lblCognome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblCognome.setText("Cognome: ");
				
				textCognome = new TextForString(this);
				textCognome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				Label lblEmail = new Label(this, SWT.NONE);
				lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblEmail.setText("Email: ");
				
				textEmail = new TextForString(this);
				textEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				Label lblUsername = new Label(this, SWT.NONE);
				lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblUsername.setText("Username: ");
				
				textUsername = new TextForString(this);
				textUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				new Label(this, SWT.NONE);
				
				btnInserisci = new Button(this, SWT.NONE);
				btnInserisci.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String nome = textNome.getText();
						String cognome = textCognome.getText();
						String email = textEmail.getText();
						String username = textUsername.getText();
						String message = controller.inserisciNuovoUtente(nome, cognome, email, username);
						if (message != null) {
							DialogMessaggio.openError("Errore", message);
						} else {
							DialogMessaggio.openInformation("Inserimento Nuovo Utente", "E' stato inserito il nuovo utente: " + username);
							textNome.setText("");
							textCognome.setText("");
							textEmail.setText("");
							textUsername.setText("");
						}
						
					}
				});
				btnInserisci.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				btnInserisci.setText("Inserisci");
				btnInserisci.setEnabled(false);
				
			}
			
			@Override
			public boolean validate() {
				boolean valid = super.validate();
				btnInserisci.setEnabled(valid);
				return valid;
			}
		};
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeControlli.setLayout(new GridLayout(2, false));
		
	}
	
}
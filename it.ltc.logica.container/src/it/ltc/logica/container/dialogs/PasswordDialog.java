package it.ltc.logica.container.dialogs;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.database.model.locale.User;
import it.ltc.logica.gui.decoration.Immagine;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * Questa classe costruisce la dialog di login che viene presentata all'avvio di Logica.
 * 
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class PasswordDialog extends Dialog {
	
	private static final String titolo = "Logica - Log In";
	private static final String OK_LABEL = "Log In";
	private static final String CANCEL_LABEL = "Chiudi";
	private static final String MESSAGE_WRONG_PASSWORD = "Password errata";
	private static final String MESSAGE_WRONG_USERNAME = "Utente non esistente";
	private static final String MESSAGE_CONNECTIVITY_PROBLEM = "Problemi di rete.\n\rContattare il reparto IT.";
	private static final String MESSAGE_GENERIC_PROBLEM = "Problemi generici.\n\rContattare il reparto IT.";
	
	private final ControllerUtente controller;
	
	private Composite container;
	private Text textPassword;
	private Label lblMessage;
	private Combo comboUsername;
	private Button btnMemorizzaPassword;
	private Label lblLogica;
	private Button okButton;
	private Button buttonPreferenze;

	public PasswordDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER);
		controller = ControllerUtente.getInstance();		
	}
	
	@Override
    protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(titolo);
    }
	
	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(255, 204, 0));
		container = (Composite) super.createDialogArea(parent);
		container.setBackground(SWTResourceManager.getColor(255, 204, 0));
		container.setLayout(new GridLayout(2, false));
		
		buttonPreferenze = new Button(container, SWT.NONE);
		buttonPreferenze.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		buttonPreferenze.setBackground(SWTResourceManager.getColor(255, 204, 0));
		buttonPreferenze.setImage(Immagine.IMPOSTAZIONI_16X16.getImage());
		buttonPreferenze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProprietaDialog dialogProprieta = new ProprietaDialog();
				dialogProprieta.open();
			}
		});
		
		lblLogica = new Label(container, SWT.NONE);
		lblLogica.setBackground(SWTResourceManager.getColor(255, 204, 0));
		lblLogica.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD | SWT.ITALIC));
		lblLogica.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLogica.setText("Logica - LTC \u00AE");
		
		Label lblUsername = new Label(container, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblUsername.setBackground(SWTResourceManager.getColor(255, 204, 0));
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username: ");
		
		comboUsername = new Combo(container, SWT.NONE);
		comboUsername.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Recupero la selezione dell'utente e ne controllo la validita'
				int selectionIndex = comboUsername.getSelectionIndex();
				if (selectionIndex != -1) {
					//Recupero l'utente selezionato
					String username = comboUsername.getItem(selectionIndex);
					List<User> utenti = ControllerUtente.getInstance().getUtentiSuggeriti();
					for (User utente : utenti) {
						if (utente.getUsername().equals(username)) {
							//Se ha una password salvata allora compilo l'apposito campo.
							String password = utente.getPassword();
							if (password == null)
								password = "";
							textPassword.setText(password);
							boolean memorizza = !password.isEmpty();
							btnMemorizzaPassword.setSelection(memorizza);								
							break;
						}
					}
				}
			}
		});
		comboUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPassword.setBackground(SWTResourceManager.getColor(255, 204, 0));
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password: ");
		
		textPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		textPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		btnMemorizzaPassword = new Button(container, SWT.CHECK);
		btnMemorizzaPassword.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnMemorizzaPassword.setText("Memorizza Password");
		btnMemorizzaPassword.setBackground(SWTResourceManager.getColor(255, 204, 0));
		
		lblMessage = new Label(container, SWT.NONE);
		lblMessage.setBackground(SWTResourceManager.getColor(255, 204, 0));
		GridData layoutMessage = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		layoutMessage.horizontalSpan = 2;
		lblMessage.setLayoutData(layoutMessage);
		
		setUsernameSuggestion();

		return container;
	}
	
	/**
	 * Riempo la combo degli username con tutte le utenze salvate.
	 */
	private void setUsernameSuggestion() {
		List<User> utenti = ControllerUtente.getInstance().getUtentiSuggeriti();
		int comboIndex = 0;
		for (User utente : utenti) {
			comboUsername.add(utente.getUsername(), comboIndex);
			comboIndex += 1;
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(255, 204, 0));
		okButton = createButton(parent, IDialogConstants.OK_ID, OK_LABEL, true);
		okButton.setBackground(SWTResourceManager.getColor(255, 204, 0));
		createButton(parent, IDialogConstants.CANCEL_ID, CANCEL_LABEL, false);
	}
	
	@Override
	protected void okPressed() {
		okButton.setEnabled(false);
		boolean validazione = login();
		if (validazione)
			super.okPressed();
	}
	
	/**
	 * Metodo per avviare il processo di autenticazione con il server.
	 * @return l'esito dell'operazione.
	 */
	private boolean login() {
		boolean success = false;
		String username = comboUsername.getText();
		String password = textPassword.getText();
		boolean memorizzaPassword = btnMemorizzaPassword.getSelection();
		int returnCode = controller.login(username, password, memorizzaPassword);
		switch (returnCode) {
			case ControllerUtente.STATUS_SUCCESSO : success = true; break;
			case ControllerUtente.STATUS_PASSWORD_ERRATA : mostraMessaggio(MESSAGE_WRONG_PASSWORD); break;
			case ControllerUtente.STATUS_UTENTE_NON_TROVATO : mostraMessaggio(MESSAGE_WRONG_USERNAME); break;
			case ControllerUtente.STATUS_PROBLEMI_DI_RETE : mostraMessaggio(MESSAGE_CONNECTIVITY_PROBLEM); break;
			case ControllerUtente.STATUS_PROBLEMI_GENERICI : mostraMessaggio(MESSAGE_GENERIC_PROBLEM); break;
		}
		return success;
	}
	
	/**
	 * Nel caso in cui ci sia da mostrare un messaggio dopo il tentativo di autenticazione.
	 * @param message Il messaggio da mostrare.
	 */
	private void mostraMessaggio(String message) {
		lblMessage.setText(message);
		okButton.setEnabled(true);
	}

}

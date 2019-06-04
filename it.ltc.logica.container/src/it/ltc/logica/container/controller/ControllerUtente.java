package it.ltc.logica.container.controller;

import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.swt.widgets.Text;

import it.ltc.database.dao.locali.ProprietaLogicaDao;
import it.ltc.database.dao.locali.UserDao;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.common.ws.RestClientSede;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.locale.ProprietaLogica;
import it.ltc.logica.database.model.locale.User;
import it.ltc.logica.update.UpdateController;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class ControllerUtente {
	
	public static final int STATUS_SUCCESSO = 0;
	public static final int STATUS_UTENTE_NON_TROVATO = 1;
	public static final int STATUS_PASSWORD_ERRATA = 2;
	public static final int STATUS_PROBLEMI_DI_RETE = 3;
	public static final int STATUS_PROBLEMI_GENERICI = 4;
	
	private static ControllerUtente instance;
	private final UISynchronize sync;
	private final IEclipseContext context;
	
	private final UserDao managerUtenti;
	private final ProprietaLogicaDao managerProprieta;
	
	private Utenza utente;
	
	private Text textCommessa;
	
	private final LinkedHashSet<String> featureNames;
	
	private ControllerUtente(UISynchronize uisync, IEclipseContext e4context) {
		sync = uisync;
		context = e4context;
		managerUtenti = new UserDao();
		managerProprieta = new ProprietaLogicaDao(); //TODO - Cancellare anche i files temporanei.
		featureNames = new LinkedHashSet<String>();
	}
	
	public static void createInstance(UISynchronize uisync, IEclipseContext e4context) {
		instance = new ControllerUtente(uisync, e4context);
	}
	
	public static ControllerUtente getInstance() {
		return instance;
	}
	
	private void setupProprietaLogica() {
		IEclipsePreferences rootNode = Platform.getPreferencesService().getRootNode();
		List<ProprietaLogica> proprieta = managerProprieta.trovaTutte();
		for (ProprietaLogica p : proprieta) {
			rootNode.put(p.getKey(), p.getValue());
		}
	}
	
	public int login(String username, String password, boolean memorizzaPassword) {
		
		//Setup delle proprieta generali di Logica (compresi gli indirizzi dei ws da chiamare)
		setupProprietaLogica();
		
		int returnCode;
		getAuthenticationString(username, password);
		
		/*nuovo: vado a fare la chiamata di login sulla sede. */
		RestClient a = new RestClientSede();
		utente = a.get("utente/login", Utenza.class);
		/*fine*/
		
		if (a.getStatus() == 200) {
			
			//utente = parseJSON(a.getMessage(), username, password, authString);
			
			//Imposto le variabili globali
			setGlobalInfo(utente);
			
			//Imposto i dettagli dell'utente
			//setUserDetails(a.getMessage());
		    
		    //Imposto il sistema con le informazioni recuperate.
		    //setupElementiVisibili(utente.getPermessi());
		    
		    //Salvo l'utente e le sue info di login, se necessario.
		    memorizzaUtente(username, password, memorizzaPassword);
		    
		    //Avvio la ricerca di aggiornamenti in base alle features legate all'utenza.
		    checkForUpdates(utente.getFeatures());
		    
			returnCode = STATUS_SUCCESSO;
		} else if (a.getStatus() == 401) {
			returnCode = STATUS_PASSWORD_ERRATA;
		} else {
			returnCode = STATUS_PROBLEMI_DI_RETE;
		}
		return returnCode;
	}
	
	private void setGlobalInfo(Utenza utente) {
		IEclipsePreferences rootNode = Platform.getPreferencesService().getRootNode();
		//Valori generali sull'utente.
		rootNode.put("utente.email", utente.getEmail());
		rootNode.put("utente.nome", utente.getNome());
		rootNode.put("utente.cognome", utente.getCognome());
		//Permessi dell'utente
	    boolean[] permessi = new boolean[100];
	    for (int id : utente.getPermessi()) {
	    	permessi[id] = true;
	    }
		for (int index = 0; index < permessi.length; index++) {
			Boolean permesso = permessi[index];
			String key = "utente.permessi." + index; 
			rootNode.put(key, permesso.toString());
		}
		//Imposto l'ambiente da cui recuperare i dati in base ai permessi
		//setupDB(permessi);
		Boolean testing = ControllerVariabiliGlobali.getInstance().isTesting(); //permessi[Permessi.TEST.getID()] ? "true" : "false";
		rootNode.put("globale.testing", testing.toString());
		//ControllerVariabiliGlobali.getInstance().isTesting();
		//E' stato spostato qui.
		boolean[] commesse = new boolean[50];
		for (int id : utente.getCommesse()) {
			commesse[id] = true;
		}
		for (int index = 0; index < commesse.length; index++) {
			Boolean commessa = commesse[index];
			String key = "utente.commesse." + index; 
			rootNode.put(key, commessa.toString());
		}
		setupElementiVisibili(permessi);
	}

	/**
	 * Metodo da provare a far funzionare per nascondere elementi della toolbar per Perspective.
	 * @return
	 */
	private void setupElementiVisibili(boolean[] permessiUtente) {
		Boolean adminPerspectiveVisible = permessiUtente[Permessi.ADMIN.getID()];
		context.set("it.ltc.logica.admin.perspective.isVisibile", adminPerspectiveVisible.toString());
	}
	
	public LinkedHashSet<String> getFeaturesNames() {
		return featureNames;
	}

	private void checkForUpdates(String[] features) {
		for (String name : features) {
			featureNames.add(name);
		}		
		UpdateController controller = new UpdateController(featureNames);
		controller.updateFeatures();
		controller.scheduleUpdateCheck(sync);
	}

//	private void setupDB(boolean[] permessi) {
//		if (permessi[Permessi.TEST.getID()]) {
//			ConfigurationManager.setDefault(Database.TEST);
//			WSCentrale.getInstance().setDomain(WSCentrale.TEST_DOMAIN);
//		}
//	}
	
	public Utenza getUtente() {
		return utente;
	}

	public UISynchronize getSync() {
		return sync;
	}
	
	public File getWorkSpaceFolder() {
		File workspaceDirectory = new File(Platform.getInstanceLocation().getURL().getFile());
		return workspaceDirectory;
	}
	
	public List<User> getUtentiSuggeriti() {
		List<User> utenti = managerUtenti.trovaTutti();
		utenti.sort(null);
		return utenti;
	}
	
	private void memorizzaUtente(String username, String password, boolean memorizzaPassword) {
		User user = new User();
		user.setUsername(username);
		if (memorizzaPassword)
			user.setPassword(password);
		else
			user.setPassword("");
		user.setUltimoLogin(new Date());
		if (getUtentiSuggeriti().contains(user)) {
			managerUtenti.aggiorna(user);
		} else {
			managerUtenti.inserisci(user);
		}
	}
	
//	public String getUserAuthenticationString() {
//		String auth = utente != null ? utente.getAuthString() : "";
//		return auth;
//	}
	
	private String getAuthenticationString(String username, String password) {
		String basicAuth = username + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(basicAuth.getBytes());
		IEclipsePreferences rootNode = Platform.getPreferencesService().getRootNode();
		//Valori generali sull'utente.
		rootNode.put("utente.username", username);
		rootNode.put("utente.password", password);
		rootNode.put("utente.authstring", encodedAuth);
		return encodedAuth;
	}
	
	public void setTextCommessa(Text textField) {
		textCommessa = textField;
	}
	
	public void setCommessaSelezionata(Commessa commessa) {
		if (commessa != null && textCommessa != null && !textCommessa.isDisposed())
			textCommessa.setText(commessa.getNome());
	}

}

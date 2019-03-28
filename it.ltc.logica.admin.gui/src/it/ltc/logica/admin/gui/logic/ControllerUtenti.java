package it.ltc.logica.admin.gui.logic;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.utenti.Utente;

public class ControllerUtenti extends ControllerCRUD<Utente> {
	
	private static final String title = "Gestione Utenti";
	private static final String resource = "gestioneutenti";
	
	private static ControllerUtenti instance;
	
	private Set<Utente> listaUtenti = new HashSet<Utente>();
	
	private ControllerUtenti() {
		super(title, resource, Utente[].class, true, false, false);
		caricaDati();
	}
	
	public static ControllerUtenti getInstance() {
		if (instance == null) {
			instance = new ControllerUtenti();
		}
		return instance;
	}
	
	public Collection<Utente> getUtenti() {
		return listaUtenti;
	}
	
	public String inserisciNuovoUtente(String nome, String cognome, String email, String username) {
		String message = null;
		boolean datiValidi = checkDati(nome, cognome, email, username);
		if (datiValidi) {
			Utente nuovoUtente = new Utente();
			nuovoUtente.setNome(nome);
			nuovoUtente.setCognome(cognome);
			nuovoUtente.setEmail(email);
			nuovoUtente.setUsername(username);
			boolean inserimento = inserisci(nuovoUtente);
			if (!inserimento)
				message = "Ci sono stati problemi con l'inserimento!";				
		} else {
			message = "Controlla i dati sul nuovo utente!";
		}
		return message;
	}
	
	public boolean aggiornaDettagliUtente(Utente utente, Set<Integer> sediSelezionate, Set<String> featureSelezionate, Set<Integer> commesseSelezionate, Set<Integer> permessiSelezionati) {
		Utente copy = copy(utente);
		copy.setSedi(sediSelezionate);
		copy.setFeatures(featureSelezionate);
		copy.setCommesse(commesseSelezionate);
		copy.setPermessi(permessiSelezionati);
		boolean update = aggiorna(copy);
		//Se è andato a buon fine aggiorno l'oggetto rimasto nel client, combobox etc.
		if (update) {
			utente.setSedi(sediSelezionate);
			utente.setFeatures(featureSelezionate);
			utente.setCommesse(commesseSelezionate);
			utente.setPermessi(permessiSelezionati);
		}
		return update;
	}
	
	private Utente copy(Utente utente) {
		Utente copy = new Utente();
		copy.setUsername(utente.getUsername());
		copy.setCognome(utente.getCognome());
		copy.setNome(utente.getNome());
		copy.setEmail(utente.getEmail());
		copy.setPassword(utente.getPassword());
		copy.setRisorsaTemporanea(utente.getRisorsaTemporanea());
		copy.setScadenzaRisorsa(utente.getScadenzaRisorsa());
		copy.setCommesse(utente.getCommesse());
		copy.setFeatures(utente.getFeatures());
		copy.setPermessi(utente.getPermessi());
		copy.setSedi(utente.getSedi());
		return copy;
	}
	
	private boolean checkDati(String nome, String cognome, String email, String username) {
		boolean datiValidi = true;
		if (nome == null || nome.isEmpty()) {
			datiValidi = false;
		}
		if (cognome == null || cognome.isEmpty()) {
			datiValidi = false;
		}
		if (email == null || email.isEmpty()) {
			datiValidi = false;
		}
		if (username == null || username.isEmpty()) {
			datiValidi = false;
		}
		return datiValidi;
	}

	@Override
	protected void aggiornaInfoInserimento(Utente object, Utente nuovo) {
		listaUtenti.add(nuovo);		
	}

	@Override
	protected void aggiornaInfoElemento(Utente object) {
		listaUtenti.add(object);
	}

	@Override
	protected void aggiornaInfoEliminazione(Utente object) {
		listaUtenti.remove(object);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Utente> lista) {
		boolean add = listaUtenti.addAll(lista);
		return add;
	}

//	public void salvaSedi(String username, List<Sede> sediSelezionate) {
//		UtenteSediJoin filtro = new UtenteSediJoin();
//		filtro.setUtente(username);
//		managerSedi.delete(filtro);
//		for (Sede sede : sediSelezionate) {
//			UtenteSediJoin join = new UtenteSediJoin();
//			join.setUtente(username);
//			join.setIdSede(sede.getId());
//			managerSedi.insert(join);
//		}
//	}
//
//	public void salvaCommesse(String username, List<Commessa> commesseSelezionate) {
//		UtenteCommesseJoin filtro = new UtenteCommesseJoin();
//		filtro.setUtente(username);
//		managerCommesse.delete(filtro);
//		for (Commessa commessa : commesseSelezionate) {
//			UtenteCommesseJoin join = new UtenteCommesseJoin();
//			join.setUtente(username);
//			join.setIdCommessa(commessa.getId());
//			managerCommesse.insert(join);
//		}
//	}
//
//	public void salvaFeature(String username, List<Feature> featureSelezionate) {
//		UtenteFeaturesJoin filtro = new UtenteFeaturesJoin();
//		filtro.setUtente(username);
//		managerFeatures.delete(filtro);
//		for (Feature feature : featureSelezionate) {
//			UtenteFeaturesJoin join = new UtenteFeaturesJoin();
//			join.setUtente(username);
//			join.setFeature(feature.getNome());
//			managerFeatures.insert(join);
//		}
//	}
//
//	public void salvaPermessi(String username, List<Permesso> permessiSelezionati) {
//		UtentePermessiJoin filtro = new UtentePermessiJoin();
//		filtro.setUtente(username);
//		managerPermessi.delete(filtro);
//		for (Permesso permesso : permessiSelezionati) {
//			UtentePermessiJoin join = new UtentePermessiJoin();
//			join.setUtente(username);
//			join.setIdPermesso(permesso.getId());
//			managerPermessi.insert(join);
//		}
//	}
//
//	public String inserisciNuovoUtente(String nome, String cognome, String email, String username) {
//		String message = null;
//		boolean datiValidi = true;
//		if (nome == null || nome.isEmpty()) {
//			message = "Bisogna inserire un nome";
//			datiValidi = false;
//		}
//		if (cognome == null || cognome.isEmpty()) {
//			message = "Bisogna inserire un cognome";
//			datiValidi = false;
//		}
//		if (email == null || email.isEmpty()) {
//			message = "Bisogna inserire una email";
//			datiValidi = false;
//		}
//		if (username == null || username.isEmpty()) {
//			message = "Bisogna inserire un username";
//			datiValidi = false;
//		}
//		if (datiValidi) {
//			Utente nuovoUtente = new Utente();
//			nuovoUtente.setNome(nome);
//			nuovoUtente.setCognome(cognome);
//			nuovoUtente.setEmail(email);
//			nuovoUtente.setUsername(username);
//			String hashedPassword = HashUtility.getHash("ltc" + nome.toLowerCase());
//			nuovoUtente.setPassword(hashedPassword);
//			boolean inserimento = managerUtenti.insert(nuovoUtente);
//			if (!inserimento)
//				message = "Ci sono stati problemi con l'inserimento!";
//			else
//				listaUtenti.add(nuovoUtente);
//		}
//		return message;
//	}

}

package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

/**
 * Controller adibito alla gestione delle commesse.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ControllerCommesse extends ControllerReadOnly<Commessa> {
	
	private static final String title = "commesse";
	private static final String resource = "commessa";
	
	private static ControllerCommesse instance;
	
	private final HashMap<Integer, Commessa> commesse;

	private ControllerCommesse() {
		super(title, resource, Commessa[].class);
		commesse = new HashMap<Integer, Commessa>();
		caricaDati();
	}

	public static ControllerCommesse getInstance() {
		if (instance == null) {
			instance = new ControllerCommesse();
		}
		return instance;
	}
	
	public Commessa getCommessa(int idCommessa) {
		return commesse.get(idCommessa);
	}
	
	public Collection<Commessa> getCommesse() {
		List<Commessa> commesseDisponibili = new LinkedList<>();
		for (Commessa commessa : commesse.values()) {
			if (ControllerVariabiliGlobali.getInstance().getBoolean("utente.commesse." + commessa.getId()))
				commesseDisponibili.add(commessa);
		}
		return commesseDisponibili;
		//return commesse.values();
	}
	
	public Collection<Commessa> getTutteCommesse() {
		return commesse.values();
	}
	
	public List<Commessa> getCommessePerSede(int idSede) {
		List<Commessa> commessePerSede = new LinkedList<>();
		for (Commessa commessa : getCommesse()) {
			if (commessa.getIdSede() == idSede) {
				commessePerSede.add(commessa);
			}
		}
		return commessePerSede;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Commessa> lista) {
		commesse.clear();
		for (Commessa commessa : lista) {
			commesse.put(commessa.getId(), commessa);
		}
		return true;
	}
	
	@Override
	public String getContextPath() {
		return RestClient.CONTEXT_PATH_SEDE;
	}
	
//	public String getDomain() {
//		return RestClient.DOMAIN_SEDE;
//	}

}

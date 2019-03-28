package it.ltc.logica.common.controller;

import java.util.List;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;

public abstract class ControllerCommessa<T> extends ControllerCRUD<T> {
	
	protected final Sede sede;
	protected final Commessa commessa;
	
	public ControllerCommessa(String title, String resource, Class<T[]> c, Commessa commessa) {
		this(title, resource, c, true, true, true, commessa);
	}

	public ControllerCommessa(String title, String resource, Class<T[]> c, boolean insert, boolean update,	boolean delete, Commessa commessa) {
		super(title, resource, c, insert, update, delete);
		this.commessa = commessa;
		this.sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
	}
	
	@Override
	public String getRisorsaCommessa() {
		return commessa.getNomeRisorsa();
	}
	
	@Override
	public String getContextPath() {
		return RestClient.CONTEXT_PATH_SEDE;
	}
	
	@Override
	public String getDomain() {
		return sede.getIndirizzoWeb();
	}
	
	public Commessa getCommessa() {
		return commessa;
	}
	
	public Sede getSede() {
		return sede;
	}
	
	@Override
	protected void aggiornaInfoInserimento(T object, T nuovo) {
		//DO NOTHING! - Non serve di solito
	}

	@Override
	protected void aggiornaInfoElemento(T object) {
		//DO NOTHING! - Non serve di solito
	}

	@Override
	protected void aggiornaInfoEliminazione(T object) {
		//DO NOTHING! - Non serve di solito
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<T> lista) {
		//DO NOTHING! - Non serve di solito
		return false;
	}

}

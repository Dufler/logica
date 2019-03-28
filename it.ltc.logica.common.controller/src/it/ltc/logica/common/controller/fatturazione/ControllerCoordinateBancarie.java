package it.ltc.logica.common.controller.fatturazione;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;

public class ControllerCoordinateBancarie extends ControllerCRUD<CoordinateBancarie> {
	
	private static final String title = "Coordinate Bancarie";
	private static final String resource = "coordinatebancarie";
	
	private static ControllerCoordinateBancarie instance;
	
	private final HashMap<Integer, CoordinateBancarie> coordinate;

	private ControllerCoordinateBancarie() {
		super(title, resource, CoordinateBancarie[].class);
		coordinate = new HashMap<>();
		caricaDati();
	}

	public static ControllerCoordinateBancarie getInstance() {
		if (instance == null) {
			instance = new ControllerCoordinateBancarie();
		}
		return instance;
	}
	
	public Collection<CoordinateBancarie> getCoordinate() {
		return coordinate.values();
	}
	
	public CoordinateBancarie getCoordinata(int id) {
		return coordinate.get(id);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CoordinateBancarie> lista) {
		coordinate.clear();
		for (CoordinateBancarie coordinata : lista) {
			coordinate.put(coordinata.getId(), coordinata);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CoordinateBancarie coordinateFatturazione, CoordinateBancarie coordinata) {
		coordinateFatturazione.setId(coordinata.getId());
		coordinate.put(coordinata.getId(), coordinata);
	}

	@Override
	protected void aggiornaInfoElemento(CoordinateBancarie object) {
		//TODO - ricarica i dati
	}

	@Override
	protected void aggiornaInfoEliminazione(CoordinateBancarie coordinateFatturazione) {
		coordinate.remove(coordinateFatturazione.getId());
	}
	
}

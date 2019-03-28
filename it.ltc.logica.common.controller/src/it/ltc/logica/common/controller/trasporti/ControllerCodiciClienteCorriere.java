package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;

public class ControllerCodiciClienteCorriere extends ControllerCRUD<CodiceClienteCorriere> {
	
	private static final String title = "Codici Cliente";
	private static final String resource = "codiceclientecorriere";
	
	private static ControllerCodiciClienteCorriere instance;
	
	private final HashMap<String, CodiceClienteCorriere> codici;

	private ControllerCodiciClienteCorriere() {
		super(title, resource, CodiceClienteCorriere[].class);
		codici = new HashMap<>();
		caricaDati();
	}

	public static ControllerCodiciClienteCorriere getInstance() {
		if (instance == null) {
			instance = new ControllerCodiciClienteCorriere();
		}
		return instance;
	}
	
	public Collection<CodiceClienteCorriere> getCodiciCliente() {
		return codici.values();
	}
	
	public CodiceClienteCorriere getCodiceCliente(String codice) {
		return codici.get(codice);
	}
	
	/**
	 * Restituisce tutti i codici cliente che appartengono alla commessa e al corriere con gli ID specificati.<br>
	 * Indicando -1 come ID vengono considerati tutti gli elementi.
	 * @param commessa l'ID della commessa desiderata, inserire -1 se si vogliono tutte le commesse.
	 * @param corriere l'ID del corriere desiderato, inserire -1 se si vogliono tutti i corrieri.
	 * @return una lista di codici cliente.
	 */
	public List<CodiceClienteCorriere> getCodiciPerClienteECorriere(int commessa, int corriere) {
		List<CodiceClienteCorriere> codici = new LinkedList<CodiceClienteCorriere>();
		for (CodiceClienteCorriere codice : getCodiciCliente()) {
			boolean checkCommessa = codice.getCommessa() == commessa || commessa == -1;
			boolean checkCorriere = codice.getCorriere() == corriere || corriere == -1;
			if (checkCommessa && checkCorriere)
				codici.add(codice);
		}
		return codici;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CodiceClienteCorriere> lista) {
		codici.clear();
		for (CodiceClienteCorriere codice : lista) {
			codici.put(codice.getCodiceCliente(), codice);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CodiceClienteCorriere object, CodiceClienteCorriere codice) {
		codici.put(codice.getCodiceCliente(), codice);
	}

	@Override
	protected void aggiornaInfoElemento(CodiceClienteCorriere object) {
		//TODO - ricarica le info
	}

	@Override
	protected void aggiornaInfoEliminazione(CodiceClienteCorriere codice) {
		codici.remove(codice.getCodiceCliente());
	}

}

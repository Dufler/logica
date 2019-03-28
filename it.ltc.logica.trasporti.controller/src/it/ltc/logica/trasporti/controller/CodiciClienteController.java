package it.ltc.logica.trasporti.controller;

public class CodiciClienteController {

	private static CodiciClienteController instance;

	//private List<CodiceClienteCorriere> listaCodiciCliente;
	//private final EntityManager<CodiceClienteCorriere> manager;

	private CodiciClienteController() {
		//manager = new EntityManager<CodiceClienteCorriere>(CodiceClienteCorriere.class, ConfigurationManager.getDefault());
	}

	public static CodiciClienteController getInstance() {
		if (instance == null) {
			instance = new CodiciClienteController();
		}
		return instance;
	}

//	public boolean inserisci(CodiceClienteCorriere codice) {
//		boolean inserito = manager.insert(codice);
//		if (inserito) {
//			getCodiciCliente().add(codice);
//		}
//		return inserito;
//	}
//
//	public boolean modifica(CodiceClienteCorriere codice, String codiceOriginale) {
//		CodiceClienteCorriere condizione = new CodiceClienteCorriere();
//		condizione.setCodiceCliente(codiceOriginale);
//		int modifiche = manager.update(codice, condizione);
//		return modifiche == 1;
//	}
//
//	public boolean elimina(CodiceClienteCorriere codice) {
//		int eliminati = manager.delete(codice);
//		boolean eliminazione = eliminati == 1;
//		if (eliminazione) {
//			getCodiciCliente().remove(codice);
//		}
//		return eliminazione;
//	}
//
//	public List<CodiceClienteCorriere> getCodiciCliente() {
//		if (listaCodiciCliente == null) {
//			listaCodiciCliente = manager.getEntities();
//		}
//		return listaCodiciCliente;
//	}
//
//	public CodiceClienteCorriere getCodiceCliente(String codice) {
//		CodiceClienteCorriere codiceCliente = null;
//		for (CodiceClienteCorriere cc : getCodiciCliente()) {
//			if (cc.getCodiceCliente().equals(codice)) {
//				codiceCliente = cc;
//				break;
//			}
//		}
//		return codiceCliente;
//	}
//
//	public List<CodiceClienteCorriere> getCodiciPerClienteECorriere(Integer idCommessa, Integer idCorriere) {
//		ArrayList<CodiceClienteCorriere> codici = new ArrayList<CodiceClienteCorriere>();
//		for (CodiceClienteCorriere codice : getCodiciCliente()) {
//			if (codice.getIdCommessa().equals(idCommessa) && codice.getIdCorriere().equals(idCorriere))
//				codici.add(codice);
//		}
//		return codici;
//	}

}

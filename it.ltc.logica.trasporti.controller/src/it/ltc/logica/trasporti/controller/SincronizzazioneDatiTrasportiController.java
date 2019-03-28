package it.ltc.logica.trasporti.controller;

import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerGiacenze;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;

/**
 * Controller utility per sincronizzare tutti i dati relativi ai trasporti.
 * @author Damiano
 *
 */
public class SincronizzazioneDatiTrasportiController {
	
	private static  SincronizzazioneDatiTrasportiController instance;
	
	private final ControllerSpedizioni controllerSpedizioni;
	private final ControllerGiacenze controllerGiacenze;
	private final ControllerContrassegni controllerContrassegni;
	private final ControllerIndirizzi controllerIndirizzi;
	private final ControllerCap controllerCap;

	private SincronizzazioneDatiTrasportiController() {
		controllerCap = ControllerCap.getInstance();
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerGiacenze = ControllerGiacenze.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
	}

	public static synchronized SincronizzazioneDatiTrasportiController getInstance() {
		if (null == instance) {
			instance = new SincronizzazioneDatiTrasportiController();
		}
		return instance;
	}
	
	/**
	 * Metodo di convenienza per sincronizzare tutti i dati relativi ai trasporti.
	 */
	public synchronized void sincronizzaDati() {
		controllerCap.caricaDati();
		controllerIndirizzi.caricaDati();
		controllerSpedizioni.caricaDati();
		controllerGiacenze.caricaDati();
		controllerContrassegni.caricaDati();
	}

}

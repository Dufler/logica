package it.ltc.logica.trasporti.calcolo.algoritmi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import it.ltc.logica.common.calcolo.algoritmi.Calcolatore;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class CalcolatoreSimulazioni extends Calcolatore<SpedizioneModel, VoceDiListinoSimulazione> {
	
//	private static final Integer NOLO_PRIORITY_10 = 28;
//	private static final Integer NOLO_PRIORITY_12 = 29;
	private static final Integer NOLO_SUD = 77;
	private static final Integer NOLO_ESTERO = 78;
	private static final Integer NOLO_BASE = 76;
	private static final Integer NOLO_REGIONI_SPECIFICHE = 94;
	private static final Integer NOLO_LIGHT = 98;
	
	private static CalcolatoreSimulazioni instance;
	
	private final ListiniSimulazioneController controllerListini;

	private CalcolatoreSimulazioni() {
		controllerListini = ListiniSimulazioneController.getInstance();
	}

	public static CalcolatoreSimulazioni getInstance() {
		if (instance == null) {
			instance = new CalcolatoreSimulazioni();
		}
		return instance;
	}
	
	public List<VoceDiListinoSimulazione> getTutteLeVociDiListinoSimulazione(Integer idListino) {
		List<ListinoSimulazioneVoce> vociCaricate = controllerListini.getVociDiListino(idListino);
		Scopo scopo = controllerListini.getScopo(idListino);
		LinkedList<VoceDiListinoSimulazione> voci = new LinkedList<VoceDiListinoSimulazione>();
		for (ListinoSimulazioneVoce voce : vociCaricate) {
			VoceDiListinoSimulazione voceModel = VoceDiListinoSimulazione.getVoceSimulazione(voce, scopo);
			voci.add(voceModel);
		}
		voci.sort(null);
		return voci;
	}

	@Override
	public List<VoceDiListinoSimulazione> getVociDiListinoUsabili(Calcolo computo, SpedizioneModel model) {
		List<VoceDiListinoSimulazione> vociApplicabili = new ArrayList<VoceDiListinoSimulazione>();
		Set<Integer> vociNoloBase = new HashSet<Integer>();
		List<VoceDiListinoSimulazione> voci = getTutteLeVociDiListinoSimulazione(computo.getIdListino());
		for (VoceDiListinoSimulazione voce : voci) {
			IAmbitoTrasporto ambito = voce.getAmbito();
			if (ambito.isApplicabile(model)) {
				vociApplicabili.add(voce);
				// Inoltre se è una voce nolo base la aggiungo alla lista delle
				// sole voci nolo base
				if (ambito.getTipo() == IAmbitoTrasporto.Tipo.NOLO_BASE) {
					vociNoloBase.add(ambito.getId());
				}
			}
		}
		// Controllo se ne ho più tipi
		boolean tipiNoloBaseDiversi = vociNoloBase.size() > 1;
		// Se è così elimino il nolo base italia
		if (tipiNoloBaseDiversi) {
			int noloDaUsare = getNoloDaUsare(vociNoloBase);
			ListIterator<VoceDiListinoSimulazione> iterator = vociApplicabili.listIterator();
			while (iterator.hasNext()) {
				VoceDiListinoSimulazione voce = iterator.next();
				IAmbitoTrasporto ambitoTrasporto = voce.getAmbito();
				if (ambitoTrasporto.getTipo() == IAmbitoTrasporto.Tipo.NOLO_BASE) {
					if (ambitoTrasporto.getId() != noloDaUsare)
						iterator.remove();
				}
			}
		}
		return vociApplicabili;
	}
	
	public Integer getNoloDaUsare(Set<Integer> vociNoloBase) {
		Integer nolo;
//		if (vociNoloBase.contains(NOLO_PRIORITY_10))
//			nolo = NOLO_PRIORITY_10;
//		else if (vociNoloBase.contains(NOLO_PRIORITY_12))
//			nolo = NOLO_PRIORITY_12;
		if (vociNoloBase.contains(NOLO_LIGHT))
			nolo = NOLO_LIGHT;
		else if (vociNoloBase.contains(NOLO_REGIONI_SPECIFICHE))
			nolo = NOLO_REGIONI_SPECIFICHE;
		else if (vociNoloBase.contains(NOLO_SUD))
			nolo = NOLO_SUD;
		else if (vociNoloBase.contains(NOLO_ESTERO))
			nolo = NOLO_ESTERO;
		else
			nolo = NOLO_BASE;
		return nolo;
	}
	
	public void calcolaSimulazione(SpedizioneModel model, ListinoSimulazione listino) {
		Calcolo.Tipo tipo = controllerListini.getScopo(listino.getId()) == Scopo.COSTO ? Calcolo.Tipo.COSTO : Calcolo.Tipo.RICAVO;
		Calcolo preventivoCosto = new Calcolo(listino.getNome(), listino.getId(), tipo);
		calcola(preventivoCosto, model);
	}
	
}

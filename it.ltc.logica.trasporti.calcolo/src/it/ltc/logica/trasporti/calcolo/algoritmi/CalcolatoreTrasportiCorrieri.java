package it.ltc.logica.trasporti.calcolo.algoritmi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import it.ltc.logica.common.calcolo.algoritmi.Calcolatore;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloEsteroGenerico;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloItaliaCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloLightCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloNazioniSpecificheCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloPriorityOre10;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloPriorityOre12;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.NoloRegioniSpecificheCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.simulazione.NoloNazioniSpecificheSimulazione;
import it.ltc.logica.trasporti.calcolo.ambiti.trasporti.NoloNazioniSpecificheTrasporti;

public class CalcolatoreTrasportiCorrieri extends Calcolatore<SpedizioneModel, VoceDiListinoTrasportatore> {
	
	/**
	 * Contiene tutti gli ID dei sotto ambiti di fatturazione per il nolo base.
	 * L'ordine in cui vengono aggiunti è importante: quelli in testa verranno selezionati prima.
	 */
	private static final int[] ID_NOLO = 
		{
			NoloNazioniSpecificheCorriere.ID,
			NoloNazioniSpecificheSimulazione.ID,
			NoloNazioniSpecificheTrasporti.ID,
			NoloEsteroGenerico.ID,
			NoloPriorityOre10.ID, 
			NoloPriorityOre12.ID,
			NoloLightCorriere.ID,
			NoloRegioniSpecificheCorriere.ID,
			NoloItaliaCorriere.ID
		};
	
	private static CalcolatoreTrasportiCorrieri instance;
	
	private final ControllerListiniCorrieri controllerListiniCorrieri;

	private CalcolatoreTrasportiCorrieri() {
		controllerListiniCorrieri = ControllerListiniCorrieri.getInstance();
	}

	public static CalcolatoreTrasportiCorrieri getInstance() {
		if (instance == null) {
			instance = new CalcolatoreTrasportiCorrieri();
		}
		return instance;
	}
	
	public List<VoceDiListinoTrasportatore> getTutteLeVociDiListinoCorriere(Integer idListino) {
		List<VoceDiListinoCorriere> vociCaricate = controllerListiniCorrieri.getVociDiListino(idListino);
		LinkedList<VoceDiListinoTrasportatore> voci = new LinkedList<VoceDiListinoTrasportatore>();
		for (VoceDiListinoCorriere voce : vociCaricate) {
			VoceDiListinoTrasportatore voceModel = VoceDiListinoTrasportatore.getVoceCorriere(voce);
			voci.add(voceModel);
		}
		voci.sort(null);
		return voci;
	}

	@Override
	public List<VoceDiListinoTrasportatore> getVociDiListinoUsabili(Calcolo computo, SpedizioneModel model) {
		List<VoceDiListinoTrasportatore> vociApplicabili = new ArrayList<VoceDiListinoTrasportatore>();
		Set<Integer> vociNoloBase = new HashSet<Integer>();
		List<VoceDiListinoTrasportatore> voci = getTutteLeVociDiListinoCorriere(computo.getIdListino());
		for (VoceDiListinoTrasportatore voce : voci) {
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
			ListIterator<VoceDiListinoTrasportatore> iterator = vociApplicabili.listIterator();
			while (iterator.hasNext()) {
				VoceDiListinoTrasportatore voce = iterator.next();
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
		Integer nolo = null;
		for (int n : ID_NOLO) {
			if (vociNoloBase.contains(n)) {
				nolo = n;
				break;
			}
		}
		if (nolo == null)
			nolo = vociNoloBase.iterator().next();
		return nolo;
	}
	
	public void calcolaCosto(SpedizioneModel model, ListinoCorriere listino) {
		Calcolo preventivoCosto = new Calcolo(listino.getNome(), listino.getId(), Calcolo.Tipo.COSTO);
		calcola(preventivoCosto, model);
	}

}

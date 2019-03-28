package it.ltc.logica.trasporti.calcolo.algoritmi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import it.ltc.logica.common.calcolo.algoritmi.Calcolatore;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

public class CalcolatoreGiacenze extends Calcolatore<SpedizioneModel, VoceDiListinoGiacenza> {
	
	private static final Integer RICONSEGNA_SUD = 65;
	private static final Integer RICONSEGNA_BASE = 16;
	private static final Integer RESO = 15;
	
	
	private static CalcolatoreGiacenze instance;
	
	private final ControllerListiniClienti controllerListini;

	private CalcolatoreGiacenze() {
		controllerListini = ControllerListiniClienti.getInstance();
	}

	public static CalcolatoreGiacenze getInstance() {
		if (instance == null) {
			instance = new CalcolatoreGiacenze();
		}
		return instance;
	}
	
	public static Integer getNoloDaUsare(Set<Integer> vociNoloBase) {
		Integer nolo;
		if (vociNoloBase.contains(RICONSEGNA_SUD))
			nolo = RICONSEGNA_SUD;
		else if (vociNoloBase.contains(RICONSEGNA_BASE))
			nolo = RICONSEGNA_BASE;
		else
			nolo = RESO;
		return nolo;
	}
	
	public List<VoceDiListinoGiacenza> getTutteLeVociDiListino(Integer idListino) {
		List<VoceDiListino> vociCaricate = controllerListini.getVociDiListino(idListino);
		LinkedList<VoceDiListinoGiacenza> voci = new LinkedList<VoceDiListinoGiacenza>();
		for (VoceDiListino voce : vociCaricate) {
			VoceDiListinoGiacenza voceModel = VoceDiListinoGiacenza.getVoceGiacenza(voce);
			voci.add(voceModel);
		}
		voci.sort(null);
		return voci;
	}

	@Override
	public List<VoceDiListinoGiacenza> getVociDiListinoUsabili(Calcolo computo, SpedizioneModel model) {
		List<VoceDiListinoGiacenza> vociApplicabili = new ArrayList<VoceDiListinoGiacenza>();
		Set<Integer> vociNoloBase = new HashSet<Integer>();
		List<VoceDiListinoGiacenza> voci;
		//voci = computo.getTipo() == Calcolo.Tipo.RICAVO ? getTutteLeVociDiListino(computo.getIdListino()) : getTutteLeVociDiListinoCorriere(computo.getIdListino());
		voci = getTutteLeVociDiListino(computo.getIdListino());
		for (VoceDiListinoGiacenza voce : voci) {
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
			ListIterator<VoceDiListinoGiacenza> iterator = vociApplicabili.listIterator();
			while (iterator.hasNext()) {
				VoceDiListinoGiacenza voce = iterator.next();
				IAmbitoTrasporto ambitoTrasporto = voce.getAmbito();
				if (ambitoTrasporto.getTipo() == IAmbitoTrasporto.Tipo.NOLO_BASE) {
					if (ambitoTrasporto.getId() != noloDaUsare)
						iterator.remove();
				}
			}
		}
		return vociApplicabili;
	}
	
	public void calcolaRicavo(SpedizioneModel model, ListinoCommessa listino) {
		Calcolo preventivoRicavo = new Calcolo(listino.getNome(), listino.getId(), Calcolo.Tipo.RICAVO);
		calcola(preventivoRicavo, model);
	}

}

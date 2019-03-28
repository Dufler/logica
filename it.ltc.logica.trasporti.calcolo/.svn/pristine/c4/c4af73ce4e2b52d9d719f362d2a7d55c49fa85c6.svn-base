package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.IAlgoritmo;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;
import it.ltc.logica.trasporti.calcolo.ambiti.simulazione.FactoryAmbitiSimulazione;

public class VoceDiListinoSimulazione extends MVoceDiListino<SpedizioneModel> {
	
	private final IAmbitoTrasporto ambito;
	
	/**
	 * Vanno inseriti i seguenti parametri ricavabili dalla voce di listino collegata.
	 * @param idListino
	 * @param idVoce
	 * @param nomeVoce
	 * @param ambito
	 * @param algoritmo
	 * @param minimo
	 * @param massimo
	 */
	public VoceDiListinoSimulazione(Integer idListino, Integer idVoce, String nomeVoce, IAmbitoTrasporto ambito, IAlgoritmo algoritmo, MDettaglioVoce dettaglio, Scopo scopo) {
		super(idListino, idVoce, nomeVoce, ambito, algoritmo, dettaglio, scopo);
		this.ambito = ambito;
	}
	
	public static VoceDiListinoSimulazione getVoceSimulazione(ListinoSimulazioneVoce voce, Scopo scopo) {
		int idListino = voce.getIdListino();
		int idVoce = voce.getId();
		String nomeVoce = voce.getNome();
		IAmbitoTrasporto ambito = FactoryAmbitiSimulazione.getInstance().getAmbito(voce.getIdsottoAmbito(), voce.getValoreSottoAmbito());
		IAlgoritmo algoritmo = FactoryAlgoritmiTrasporti.getInstance().getAlgoritmo(voce.getTipo(), voce.getStrategia());
		MDettaglioVoce dettagli = ListiniSimulazioneController.getInstance().getDettagliVoce(voce);
		VoceDiListinoSimulazione nuovaVoce = new VoceDiListinoSimulazione(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, scopo);
		return nuovaVoce;
	}

	@Override
	public IAmbitoTrasporto getAmbito() {
		return ambito;
	}

	@Override
	public String toString() {
		return "VoceModel [ambito=" + ambito + ", tipoAlgoritmo=" + algoritmo + "]";
	}
	
	@Override
	public int compareTo(MVoceDiListino<SpedizioneModel> voce) {
		int ordinamento = super.compareTo(voce);
		if (ordinamento == 0 && voce instanceof VoceDiListinoSimulazione) {
			VoceDiListinoSimulazione voceTrasporti = (VoceDiListinoSimulazione) voce;
			IAmbitoTrasporto ambitoTrasporti = voceTrasporti.getAmbito();
			ordinamento = ambito.getTipo().compareTo(ambitoTrasporti.getTipo());
		}
		return ordinamento;
	}
	
}

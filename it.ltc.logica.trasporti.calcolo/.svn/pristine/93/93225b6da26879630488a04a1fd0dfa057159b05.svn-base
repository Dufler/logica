package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.IAlgoritmo;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;
import it.ltc.logica.trasporti.calcolo.ambiti.corriere.FactoryAmbitiCorriere;

public class VoceDiListinoTrasportatore extends MVoceDiListino<SpedizioneModel> {
	
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
	public VoceDiListinoTrasportatore(Integer idListino, Integer idVoce, String nomeVoce, IAmbitoTrasporto ambito, IAlgoritmo algoritmo, MDettaglioVoce dettaglio, Scopo scopo) {
		super(idListino, idVoce, nomeVoce, ambito, algoritmo, dettaglio, scopo);
		this.ambito = ambito;
	}
	
	public static VoceDiListinoTrasportatore getVoceCorriere(VoceDiListinoCorriere voce) {
		int idListino = voce.getIdListino();
		int idVoce = voce.getId();
		String nomeVoce = voce.getNome();
		IAmbitoTrasporto ambito = FactoryAmbitiCorriere.getInstance().getAmbito(voce.getIdSottoAmbito(), voce.getValoreSottoAmbito());
		IAlgoritmo algoritmo = FactoryAlgoritmiTrasporti.getInstance().getAlgoritmo(voce.getTipoCalcolo(), voce.getStrategiaCalcolo());
		MDettaglioVoce dettagli = ControllerListiniCorrieri.getInstance().getDettagliVoce(voce);
		VoceDiListinoTrasportatore nuovaVoce = new VoceDiListinoTrasportatore(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, Scopo.COSTO);
		return nuovaVoce;
	}

	@Override
	public IAmbitoTrasporto getAmbito() {
		return ambito;
	}

	@Override
	public String toString() {
		return "Voce corriere [ambito=" + ambito + ", tipoAlgoritmo=" + algoritmo + "]";
	}
	
	@Override
	public int compareTo(MVoceDiListino<SpedizioneModel> voce) {
		int ordinamento = super.compareTo(voce);
		if (ordinamento == 0 && voce instanceof VoceDiListinoTrasporti) {
			VoceDiListinoTrasportatore voceTrasporti = (VoceDiListinoTrasportatore) voce;
			IAmbitoTrasporto ambitoTrasporti = voceTrasporti.getAmbito();
			ordinamento = ambito.getTipo().compareTo(ambitoTrasporti.getTipo());
		}
		return ordinamento;
	}

}

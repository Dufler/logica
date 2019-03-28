package it.ltc.logica.trasporti.calcolo.ambiti;

import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;

/**
 * Classe che specializza gli ambiti di trasporto rendendo necessario un valore per verificare l'applicabilità ed effettuare il calcolo.
 * Alcuni esempi sono la sosta in giacenza la cui franchigia in giorni deve essere specificata oppure il contrassegno che superi una certa soglia.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public abstract class IAmbitoTrasportoConValore extends IAmbitoTrasporto {

	protected final String valore;
	
	public IAmbitoTrasportoConValore(SottoAmbitoFattura ambito, String valore) {
		super(ambito);
		this.valore = valore;
	}

}

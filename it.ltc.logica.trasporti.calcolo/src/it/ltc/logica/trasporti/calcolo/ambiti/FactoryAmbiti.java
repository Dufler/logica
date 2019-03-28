package it.ltc.logica.trasporti.calcolo.ambiti;

/**
 * Classe astratta da implementare per ciascuno dei macroambiti di fatturazione/simulazione.
 * A partire dall'ID del sotto ambito restituisce un'istanza di una classe capace di calcolare l'applicabilità e il costo di una voce di listino per una data spedizione.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public abstract class FactoryAmbiti {
	
	public abstract IAmbitoTrasporto getAmbito(Integer id, String valore);

}

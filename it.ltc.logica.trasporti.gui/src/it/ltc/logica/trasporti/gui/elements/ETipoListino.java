package it.ltc.logica.trasporti.gui.elements;

/**
 * Enum che contiene tutti i possibili tipi di listino per i trasporti.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public enum ETipoListino {
	
	CORRIERE(4, "Propriet\u00E0 - Voce di Listino Corriere"),
	CLIENTE_SPEDIZIONI_ITALIA(1, "Propriet\u00E0 - Voce di Listino Cliente - Spedizioni in Italia"),
	CLIENTE_SPEDIZIONI_UE(11, "Propriet\u00E0 - Voce di Listino Cliente - Spedizioni all'estero (UE)"),
	CLIENTE_SPEDIZIONI_EXTRA_UE(14, "Propriet\u00E0 - Voce di Listino Cliente - Spedizioni all'estero (Extra UE)"),
	CLIENTE_GIACENZE(2, "Propriet\u00E0 - Voce di Listino Cliente - Giacenze"),
	CLIENTE_RITIRI(9, "Propriet\u00E0 - Voce di Listino Cliente - Ritiri"),
	CLIENTE_SHOP_TO_SHOP(10, "Propriet\u00E0 - Voce di Listino Cliente - Shop To Shop"),
	SIMULAZIONE_TRASPORTI_CLIENTE(3, "Propriet\u00E0 - Voce di Listino di Simulazione"),
	SIMULAZIONE_CORRIERE(8, "Propriet\u00E0 - Voce di Listino di Simulazione");
	
	private final int idAmbito;
	private final String titolo;
	
	private ETipoListino(int idAmbito, String titolo) {
		this.idAmbito = idAmbito;
		this.titolo = titolo;
	}

	public int getIdAmbito() {
		return idAmbito;
	}

	public String getTitolo() {
		return titolo;
	}
	
	public static ETipoListino getByID(int id) {
		ETipoListino tipo = null;
		for (ETipoListino t : values()) {
			if (t.getIdAmbito() == id) {
				tipo = t;
				break;
			}
		}
		return tipo;
	}

}

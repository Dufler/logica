package it.ltc.logica.trasporti.controller.fatturazione.brt;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;

public class RigaFatturazione {
	
	private final TipoSpedizione tipoSpedizione;
	private final String letteraDiVettura;
	private final String codiceCliente;
	private final String riferimentoMittente;
	private final String dataSpedizione; //formato yyyyMMdd
	private final double imponibile;
	private final boolean piano;
	private final boolean gdo;
	private final boolean ztl;
	private final boolean disagiata;
	private final boolean isola;
	private final boolean giacenza;
	
	private final int colli;
	private final double peso;
	private final double volume;
	private final double valoreContrassegno;
	private final String tipoContrassegno;
	
	private final String capDestinazione;
	private final String indirizzoDestinazione;
	private final String localitaDestinazione;
	private final String provinciaDestinazione;
	private final String nazioneDestinazione;
	private final String ragioneSociale;
	
	public RigaFatturazione(String letteraDiVettura, String codiceCliente, String riferimentoMittente, String dataSpedizione, double imponibile, boolean piano, boolean gdo, boolean ztl, boolean disagiata, boolean isola, boolean giacenza) {
		this.letteraDiVettura = letteraDiVettura;
		this.codiceCliente = codiceCliente;
		this.riferimentoMittente = riferimentoMittente;
		this.dataSpedizione = dataSpedizione;
		this.imponibile = imponibile;
		this.piano = piano;
		this.gdo = gdo;
		this.ztl = ztl;
		this.disagiata = disagiata;
		this.isola = isola;
		this.giacenza = giacenza;
		this.tipoSpedizione = TipoSpedizione.ITALIA;
		
		colli = 0;
		peso = 0;
		volume = 0;
		valoreContrassegno = 0;
		tipoContrassegno = "SC";
		
		capDestinazione = "";
		indirizzoDestinazione = "";
		localitaDestinazione = "";
		provinciaDestinazione = "";
		ragioneSociale = "";
		nazioneDestinazione = "ITA";
	}
	
	public RigaFatturazione(String letteraDiVettura, String codiceCliente, String riferimentoMittente, String dataSpedizione, double imponibile, boolean piano, boolean gdo, boolean ztl, boolean disagiata, boolean isola, boolean giacenza, int colli, double peso, double volume, double valoreContrassegno, String tipoContrassegno, String cap, String localita, String provincia, String indirizzo, String nazione, String ragioneSociale) {
		this.letteraDiVettura = letteraDiVettura;
		this.codiceCliente = codiceCliente;
		this.riferimentoMittente = riferimentoMittente;
		this.dataSpedizione = dataSpedizione;
		this.imponibile = imponibile;
		this.piano = piano;
		this.gdo = gdo;
		this.ztl = ztl;
		this.disagiata = disagiata;
		this.isola = isola;
		this.giacenza = giacenza;
		this.tipoSpedizione = TipoSpedizione.ITALIA; //TODO - metterlo in base alla nazione
		
		this.colli = colli;
		this.peso = peso;
		this.volume = volume;
		this.valoreContrassegno = valoreContrassegno;
		this.tipoContrassegno = tipoContrassegno;
		
		capDestinazione = cap;
		indirizzoDestinazione = indirizzo;
		localitaDestinazione = localita;
		provinciaDestinazione = provincia;
		nazioneDestinazione = (nazione == null || nazione.length() < 3) ? "ITA" : nazione; //TODO - trascodificare
		this.ragioneSociale = ragioneSociale;
		
	}
	
	public boolean isIndirizzoParticolare() {
		boolean particolarita = piano || gdo;
		return particolarita;
	}
	
	public boolean isCAPParticolare() {
		boolean particolarita = ztl || disagiata || isola;
		return particolarita;
	}

	public String getLetteraDiVettura() {
		return letteraDiVettura;
	}
	
	public String getCodiceCliente() {
		return codiceCliente;
	}
	
	public String getRiferimentoMittente() {
		return riferimentoMittente;
	}

	public String getDataSpedizione() {
		return dataSpedizione;
	}

	public double getImponibile() {
		return imponibile;
	}
	
	public boolean isPiano() {
		return piano;
	}
	
	public boolean isGdo() {
		return gdo;
	}
	
	public boolean isZtl() {
		return ztl;
	}
	
	public boolean isDisagiata() {
		return disagiata;
	}
	
	public boolean isIsola() {
		return isola;
	}
	
	public boolean isGiacenza() {
		return giacenza;
	}
	
	public TipoSpedizione getTipoSpedizione() {
		return tipoSpedizione;
	}

	public int getColli() {
		return colli;
	}

	public double getPeso() {
		return peso;
	}

	public double getVolume() {
		return volume;
	}

	public double getValoreContrassegno() {
		return valoreContrassegno;
	}

	public String getTipoContrassegno() {
		return tipoContrassegno;
	}

	public String getCapDestinazione() {
		return capDestinazione;
	}

	public String getIndirizzoDestinazione() {
		return indirizzoDestinazione;
	}

	public String getLocalitaDestinazione() {
		return localitaDestinazione;
	}

	public String getProvinciaDestinazione() {
		return provinciaDestinazione;
	}

	public String getNazioneDestinazione() {
		return nazioneDestinazione;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

}

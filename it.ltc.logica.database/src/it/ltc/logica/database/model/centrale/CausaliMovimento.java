package it.ltc.logica.database.model.centrale;

/**
 * Enum con tutte le causali possibili di movimento.
 * @author Damiano
 *
 */
public enum CausaliMovimento {
	
	INV("Inventario", 1, 1, 0, "SI", 1, 0, "+", "+", "N", "I", "INV", "IV"),
	CPK("Carico", 1, 1, 0, "SI", 1, 0, "+", "+", "N", "E", "CAR", "CR"),
	SPE("Scarico errori", -1, -1, 0, "SI", 0, 1, "-", "-", "N", "_", "___", "__"),
	RES("Carico da reso", 1, 1, 0, "SI", 1, 0, "+", "+", "N", "E", "CAR", "CR"),
	CPR("Rettifica carico", 1, 1, 0, "SI", 1, 0, "+", "+", "N", "R", "RET", "CR"),
	IOS("Impegno ordine", 0, -1, 1, "NO", 0, 0, "N", "-", "+", "O", "ORD", "IP"),
	REO("Cancellazione ordine", 0, 1, -1, "NO", 0, 0, "N", "+", "-", "A", "PAK", "RO"),
	IBO("Imballo ordine", -1, 0, -1, "SI", 0, 1, "-", "N", "-", "O", "ORD", "OR"),
	REL("Rettifica non spedito", 0, 1, -1, "NO", 0, 0, "N", "+", "-", "O", "ORD", "OR"),
	ANC("Annullamento collo", -1, -1, 0, "SI", 1, 0, "-", "-", "N", "O", "RET", "RT");
	
	private final String descrizione;
	
	private final int moltiplicatoreEsistenza;
	private final int moltiplicatoreDisponibile;
	private final int moltiplicatoreImpegnato;
	
	private final String incrementoTotali;
	private final int totaleIn;
	private final int totaleOut;
	
	private final String segnoEsistenza;
	private final String segnoDisponibile;
	private final String segnoImpegnato;
	
	private final String categoriaDocumento; //L'equivalente di doccat, 1 carattere.
	private final String tipoDocumento; //l'equivalente di doctipo, 3 caratteri.
	private final String tipoMovimento; //l'equivaliente di tipo, 2 caratteri.
	
	private CausaliMovimento(String descrizione, int e, int d, int i, String incremento, int in, int out, String se, String sd, String si, String cd, String td, String tm) {
		this.descrizione = descrizione;
		this.moltiplicatoreEsistenza = e;
		this.moltiplicatoreDisponibile = d;
		this.moltiplicatoreImpegnato = i;
		this.incrementoTotali = incremento;
		this.totaleIn = in;
		this.totaleOut = out;
		this.segnoEsistenza = se;
		this.segnoDisponibile = sd;
		this.segnoImpegnato = si;
		this.categoriaDocumento = cd; //"_";
		this.tipoDocumento = td; //"___";
		this.tipoMovimento = tm; //"__";
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public int getMoltiplicatoreEsistenza() {
		return moltiplicatoreEsistenza;
	}

	public int getMoltiplicatoreDisponibile() {
		return moltiplicatoreDisponibile;
	}
	
	public int getMoltiplicatoreImpegnato() {
		return moltiplicatoreImpegnato;
	}

	public String getIncrementoTotali() {
		return incrementoTotali;
	}

	public int getTotaleIn() {
		return totaleIn;
	}

	public int getTotaleOut() {
		return totaleOut;
	}

	public String getSegnoEsistenza() {
		return segnoEsistenza;
	}

	public String getSegnoDisponibile() {
		return segnoDisponibile;
	}

	public String getSegnoImpegnato() {
		return segnoImpegnato;
	}

	public String getCategoriaDocumento() {
		return categoriaDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	@Override
	public String toString() {
		return descrizione;
	}
	
}

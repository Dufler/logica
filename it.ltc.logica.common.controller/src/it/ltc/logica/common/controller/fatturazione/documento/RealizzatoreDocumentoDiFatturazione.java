package it.ltc.logica.common.controller.fatturazione.documento;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.sistema.ControllerClienti;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;

/**
 * Classe che si occupa di realizzare un documento di fatturazione a partire dai dati salvati e confermati dagli utenti.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public abstract class RealizzatoreDocumentoDiFatturazione {
	
	protected String filePath;
	protected DocumentoFattura documento;
	protected boolean bozza;
	protected List<VoceFattura> voci;
	
	protected final ControllerAmbitiFatturazione controllerAmbiti;
	protected final ControllerCommesse controllerCommesse;
	protected final ControllerClienti controllerClienti;
	protected final ControllerIndirizzi controllerIndirizzi;
	protected final ControllerSpedizioni controllerSpedizioni;
	protected final ControllerContrassegni controllerContrassegni;
	protected final ControllerListiniClienti controllerListiniClienti;
	protected final ControllerPreferenzeFatturazione controllerPreferenze;
	
	protected final SimpleDateFormat semplice;
	protected final DecimalFormat formatNumeri;
	protected final DecimalFormat formatEuro;
	
	public RealizzatoreDocumentoDiFatturazione() {
		controllerAmbiti = ControllerAmbitiFatturazione.getInstance();
		controllerCommesse = ControllerCommesse.getInstance();
		controllerClienti = ControllerClienti.getInstance();
		controllerIndirizzi = ControllerIndirizzi.getInstance();
		controllerSpedizioni = ControllerSpedizioni.getInstance();
		controllerContrassegni = ControllerContrassegni.getInstance();
		controllerListiniClienti = ControllerListiniClienti.getInstance();
		controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
		
		semplice = new SimpleDateFormat("dd/MM/yyyy");
		formatNumeri = new DecimalFormat();
		formatNumeri.applyPattern("0.#");
		formatEuro = new DecimalFormat();
		formatEuro.applyPattern("#,##0.000 \u20AC");
	}
	
	public void setFile(String filePath) {
		this.filePath = filePath;		
	}
	
	public void setDocumento(DocumentoFattura documento) {
		this.documento = documento;
	}

	public void setVoci(List<VoceFattura> voci) {
		this.voci = voci;
	}
	
	public void setBozza(boolean bozza) {
		this.bozza = bozza;		
	}
	
	/**
	 * Restituisce il path completo del file che verrà salvato.<br>
	 * Il nome viene realizzato a partire dalle informazioni del documento.
	 * @param path il path della cartella in cui verrà salvato il file.
	 * @param estensione l'estensione del file completa di . davanti.
	 * @return il path completo del documento da salvare.
	 */
	protected String getNomeFile(String path, String estensione) {
		String tipoFile = "Fatturazione";
		String nomAmbito = controllerAmbiti.getAmbito(documento.getIdAmbito()).getNome();
		String nomeCommessa = controllerCommesse.getCommessa(documento.getIdCommessa()).getNome();
		String periodo = documento.getMeseAnno();
		String nomeFile = path  + "\\"  + tipoFile + " " + nomAmbito + " " + nomeCommessa + " " + periodo + estensione;
		return nomeFile;
	}
	
	public abstract void elaboraDocumento();
	
}

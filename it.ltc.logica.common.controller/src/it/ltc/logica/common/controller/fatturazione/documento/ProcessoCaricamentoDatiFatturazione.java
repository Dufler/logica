package it.ltc.logica.common.controller.fatturazione.documento;

import java.util.List;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.fatturazione.ControllerDocumentiDiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.gui.task.Processo;

public class ProcessoCaricamentoDatiFatturazione extends Processo {
	
	private static final String titolo = "Caricamento dei dati di fatturazione";
	
	private final ControllerDocumentiDiFatturazione controller;
	
	//private final int idDocumento;
	private final String path;
	private final boolean bozza;
	
	private final DocumentoFattura documento;
	private final AmbitoFattura modello;
	private final List<VoceFattura> voci;
	private final RealizzatoreDocumentoDiFatturazione realizzatore;
	
	public ProcessoCaricamentoDatiFatturazione(String path, int idDocumento, boolean bozza) {
		super(titolo, 100);
		controller = ControllerDocumentiDiFatturazione.getInstance();
		this.path = path;
		//this.idDocumento = idDocumento;
		this.bozza = bozza;
		
		this.documento = controller.getDocumento(idDocumento);
		this.modello = ControllerAmbitiFatturazione.getInstance().getAmbito(documento.getIdAmbito());
		this.voci = controller.caricaVociPerDocumento(documento);
		this.realizzatore = ControllerDocumentiDiFatturazione.getInstance().getRealizzatore(modello);
	}

	@Override
	public void eseguiOperazioni() {
		//Controlli
		if (documento == null)
			throw new RuntimeException("Documento non trovato!");
		aumentaProgresso(10);
		if (modello == null)
			throw new RuntimeException("Modello per il documento non valido!");
		aumentaProgresso(10);
		//Caricamento dati
		realizzatore.setFile(path);
		realizzatore.setDocumento(documento);
		realizzatore.setVoci(voci);
		realizzatore.setBozza(bozza);
		aumentaProgresso(20);
		//Elabora il documento
		realizzatore.elaboraDocumento();
		aumentaProgresso(55);
	}

}

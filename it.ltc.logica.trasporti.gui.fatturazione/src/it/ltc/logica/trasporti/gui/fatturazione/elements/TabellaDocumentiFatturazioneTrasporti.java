package it.ltc.logica.trasporti.gui.fatturazione.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.gui.common.tables.fatturazione.TabellaDocumentiFatturazione;
import it.ltc.logica.trasporti.gui.fatturazione.dialogs.DialogModificaDatiFatturazioneSpedizioni;

public class TabellaDocumentiFatturazioneTrasporti extends TabellaDocumentiFatturazione {
	
	private final int ambito;

	public TabellaDocumentiFatturazioneTrasporti(Composite parent, int ambito) {
		super(parent);
		this.ambito = ambito;
	}
	
	protected void apriDialogModificaDatiFatturazione() {
		DocumentoFattura documento = getRigaSelezionata();
		if (documento != null) {
			DialogModificaDatiFatturazioneSpedizioni dialog = new DialogModificaDatiFatturazioneSpedizioni(documento);
			dialog.open();
		}
	}
	
	@Override
	public void aggiornaContenuto() {
		if (controllerDocumenti != null && ambito > 0)
			setElementi(controllerDocumenti.getDocumentiPerAmbito(ambito));
	}

}

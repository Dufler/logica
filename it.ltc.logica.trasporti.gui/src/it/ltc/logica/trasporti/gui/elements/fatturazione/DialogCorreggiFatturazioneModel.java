package it.ltc.logica.trasporti.gui.elements.fatturazione;

import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.fatturazione.ControllerDocumentiDiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.VoceFattura;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.controller.FatturazioneSpedizioniController;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.DialogSpedizioneModel;
import it.ltc.logica.trasporti.gui.elements.spedizionemodel.TabellaVoceCalcolata;

public class DialogCorreggiFatturazioneModel extends DialogSpedizioneModel {

	private final ControllerDocumentiDiFatturazione controllerDocumenti;

	private final DocumentoFattura documento;
	private final List<VoceFattura> voci;

	public DialogCorreggiFatturazioneModel(SpedizioneModel sm, boolean modify, DocumentoFattura documento, List<VoceFattura> voci) {
		super(sm, modify);

		controllerDocumenti = ControllerDocumentiDiFatturazione.getInstance();

		this.documento = documento;
		this.voci = voci;
	}
	
	@Override
	public boolean isDirty() {
		boolean compositeDirty = super.isDirty();
		boolean vociModificate = isModificata();
		return compositeDirty || vociModificate;
	}
	
	private boolean isModificata() {
		boolean vociModificate = false;
		for (TabellaVoceCalcolata t : listaTableViewerRicavi) {
			if (t.isVoceModificata())
				vociModificate = true;
		}
		for (TabellaVoceCalcolata t : listaTableViewerCosti) {
			if (t.isVoceModificata())
				vociModificate = true;
		}
		return vociModificate;
	}
	
	@Override
	public void copyDataToModel() {
		// Dati spedizione
		spedizione.setIdCorriere(compositeDatiSpedizione.getCorriere().getId());
		spedizione.setServizio(compositeDatiSpedizione.getServizio().getCodice());
		spedizione.setLetteraDiVettura(compositeDatiSpedizione.getLetteraDiVettura());
		spedizione.setColli(compositeDatiSpedizione.getColli());
		spedizione.setVolume(compositeDatiSpedizione.getVolume());
		spedizione.setPeso(compositeDatiSpedizione.getPeso());
		spedizione.setDataPartenza(compositeDatiSpedizione.getDataSpedizione());
		String riferimentoCliente = compositeOrdine.getRiferimentoCliente();
		String riferimentoMittente = compositeOrdine.getRiferimentoInterno();
		String note = compositeOrdine.getNote().isEmpty() ? null : compositeOrdine.getNote();
		spedizione.setRiferimentoCliente(riferimentoCliente);
		spedizione.setRiferimentoMittente(riferimentoMittente);
		spedizione.setPezzi(compositeOrdine.getPezzi());
		spedizione.setNote(note);
		
		// Controllo la completezza dei dati
		Integer colli = spedizione.getColli();
		Integer pezzi = spedizione.getPezzi();
		Double volume = spedizione.getVolume();
		Double peso = spedizione.getPeso();
		if (colli != null && colli > 0 && pezzi != null && pezzi > 0 && volume != null && volume > 0 && peso != null && peso > 0) {
			spedizione.setDatiCompleti(true);
		}
		// Contrassegno
		if (contrassegno != null) {
			contrassegno.setValore(compositeContrassegno.getValore());
			contrassegno.setValuta(compositeContrassegno.getValuta().getCodice());
			contrassegno.setTipo(compositeContrassegno.getTipoContrassegno().getCodice());
			contrassegno.setAnnullato(compositeContrassegno.getAnnullato());
		}
		// Destinatario
		destinazione.setIndirizzo(compositeDestinazione.getIndirizzo());
		destinazione.setCap(compositeDestinazione.getCap());
		destinazione.setRagioneSociale(compositeDestinazione.getRagioneSociale());
		destinazione.setLocalita(compositeDestinazione.getLocalita());
		destinazione.setNazione(compositeDestinazione.getNazione().getCodiceIsoTre());
		destinazione.setProvincia(compositeDestinazione.getProvincia().getSigla());
		destinazione.setConsegnaAlPiano(compositeDestinazione.getPiano());
		destinazione.setConsegnaPrivato(compositeDestinazione.getPrivato());
		destinazione.setConsegnaGdo(compositeDestinazione.getGDO());
		destinazione.setConsegnaAppuntamento(compositeDestinazione.getAppuntamento());
	}

	@Override
	public boolean updateModel() {
		// Verifico se sono stati cambiati dati sulla spedizione
		boolean modificheSpedizione = compositeDatiSpedizione.isDirty() || compositeOrdine.isDirty();
		boolean modificheContrassegno = contrassegno != null ? compositeContrassegno.isDirty() : false;
		boolean modificheDestinatario = compositeDestinazione.isDirty();
		boolean modifiche = modificheSpedizione || modificheContrassegno || modificheDestinatario;
		
		//Se ho fatto modifiche sui dati ricalcolo le voci
		if (modifiche) {
			model.verificaDatiFatturazione();
			FatturazioneSpedizioniController.getInstance().ricalcolaSpedizione(model);
		}
		
		//Aggiungo il nuovo valore di ricavo
		spedizione.setRicavo(model.getPreventivoRicavo().getTotale());
		
		//Aggiorno i dati
		boolean updateSpedizione = modificheSpedizione || isModificata() ? controllerSpedizioni.aggiorna(spedizione) : true;
		boolean updateContrassegno = true;
		if (contrassegno != null)
			updateContrassegno = modificheContrassegno ? controllerContrassegni.aggiorna(contrassegno) : true;
		boolean updateDestinatario = modificheDestinatario ? controllerIndirizzi.aggiorna(destinazione) : true;
		boolean updateDati = updateSpedizione && updateContrassegno && updateDestinatario;
		
		//Verifico se sono state fatte modifiche alle voci, se si salvo le modifiche
		boolean updateVoci = true;
		if (modifiche || isModificata()) {
			boolean eliminaVoci = eliminaVociVecchie();	
			boolean inserisciVoci = inserisciVociNuove();
			updateVoci = eliminaVoci && inserisciVoci;
		}
		
		//L'aggiornamento di tutti i dati è condizionato dalle varie parti.
		boolean update = updateDati && updateVoci;
		return update;
	}

	private boolean eliminaVociVecchie() {
		boolean eliminazioni = true;
		for (VoceFattura voce : voci) {
			boolean eliminazione = controllerDocumenti.eliminaVoce(voce);
			if (!eliminazione)
				eliminazioni = false;
		}
		if (!eliminazioni)
			System.out.println("Almeno un eliminazione è fallita!");
		return eliminazioni;
	}

	private boolean inserisciVociNuove() {
		// Inserisco quelle confermate dall'utente
		boolean inserimenti = true;
		Calcolo calcolo = model.getCalcoloRichiesto(Scopo.RICAVO);
		for (VoceCalcolata voce : calcolo.getVoci()) {
			VoceFattura nuovaVoce = new VoceFattura();
			nuovaVoce.setIdAmbito(documento.getIdAmbito());
			nuovaVoce.setIdSottoAmbito(voce.getIdAmbito());
			nuovaVoce.setIdCommessa(documento.getIdCommessa());
			nuovaVoce.setIdDocumento(documento.getId());
			nuovaVoce.setIdListino(calcolo.getIdListino());
			nuovaVoce.setIdRiferimento(spedizione.getId());
			nuovaVoce.setIdVoce(voce.getIdVoce());
			nuovaVoce.setImporto(voce.getCosto());
			boolean inserimento = controllerDocumenti.inserisciVoce(nuovaVoce);
			if (!inserimento)
				inserimenti = false;
		}
		if (!inserimenti)
			System.out.println("Almeno un inserimento è fallito.");
		return inserimenti;
	}

}

package it.ltc.logica.common.controller.fatturazione;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura.Iva;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura.Stato;
import it.ltc.logica.database.model.centrale.fatturazione.ModalitaPagamentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public abstract class FatturazioneController {
	
	protected static final String TITOLO_ERRORE_CODICE_NON_TROVATO = "Attenzione";
	protected static final String MESSAGGIO_ERRORE_CODICE_NON_TROVATO = "Il codice cliente [codice] non è stato ancora inserito, le spedizioni ad esso legate non saranno fatturate.";
	
	protected final ControllerListiniClienti controllerListini;
	protected final ControllerPreferenzeFatturazione controllerPreferenze;
	protected final ControllerDocumentiDiFatturazione controllerDocumenti;
	
	protected final SimpleDateFormat sdf;
	protected final SimpleDateFormat meseEAnno;
	protected final SimpleDateFormat semplice;
	
	protected Commessa commessa;
	protected Date inizio;
	protected Date fine;
	
	protected FatturazioneController() {
		controllerListini = ControllerListiniClienti.getInstance();
		controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
		controllerDocumenti = ControllerDocumentiDiFatturazione.getInstance();
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		meseEAnno = new SimpleDateFormat("MM-yyyy");
		semplice = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public void setCommessa(Commessa selectedValue) {
		commessa = selectedValue;		
	}

	public void setDataInizio(Date da) {
		inizio = new Date(da.getTime());
	}

	public void setDataFine(Date a) {
		fine = new Date(a.getTime());
	}
	
	/**
	 * Genera un nuovo documento di fatturazione sempre e comunque (diversamente da prima)<br>
	 * La data di inizio viene usata per determinare alcune info come l'anno e il mese di riferimento.
	 * @param idCommessa La commessa a cui attribuire la fattura.
	 * @param idAmbito L'ambito di applicazione della fattura.
	 * @param inizio La data d'inizio del periodo della fattura.
	 * @return l'ID del documento o -1 in caso di errore.
	 */
	public int getDocumentoFatturazione(int idCommessa, int idAmbito, Date inizio) {
		//Elaboro le info generale sulla fattura a partire dalla data di inizio.
		String meseAnno = meseEAnno.format(inizio);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(inizio);
		int anno = gc.get(Calendar.YEAR);
		DocumentoFattura documento = new DocumentoFattura();
		documento.setUtenteCreatore(getUsername());
		documento.setDataGenerazione(new Date());
		documento.setIdCommessa(idCommessa);
		documento.setIdAmbito(idAmbito);
		documento.setMeseAnno(meseAnno);
		documento.setAnnoFattura(anno);
		documento.setDataEmissione(new Date());
		documento.setNumeroFattura(controllerDocumenti.getProssimoNumeroFatturaPerAnno(anno));
		documento.setIva(Iva.IVA22);
		documento.setStato(Stato.GENERATA);
		//Imposto le preferenze di fatturazione, se presenti.
		PreferenzeFatturazione preferenze = controllerPreferenze.getPreferenzePerCommessaSuAmbito(idCommessa, idAmbito);
		if (preferenze != null) {
			documento.setCoordinatePagamento(preferenze.getCoordinatePagamento());
			documento.setDescrizioneFattura(preferenze.getDescrizioneFattura());
			documento.setModalitaPagamento(preferenze.getModalitaPagamento());
		} else {
			documento.setModalitaPagamento(ModalitaPagamentoFattura.B30);
		}
		boolean inserimento = controllerDocumenti.inserisci(documento);
		int idDocumento = inserimento ? documento.getId() : -1;
		return idDocumento;
	}
	
//	public int getDocumentoFatturazione(int idCommessa, int idAmbito, Date data) {
//		int idDocumento;
//		DocumentoFattura documento = null;
//		//Controllo se ho già inserito un documento per quel tipo di fatturazione, per quel mese, per quella commessa
//		String meseAnno = meseEAnno.format(data);
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTime(data);
//		int anno = gc.get(Calendar.YEAR);
//		for (DocumentoFattura d : controllerDocumenti.getDocumentiPerAmbito(idAmbito)) {
//			if (d.getIdCommessa() == idCommessa && d.getIdAmbito() == idAmbito && d.getMeseAnno().equals(meseAnno)) {
//				documento = d;
//				break;
//			}
//		}
//		//Se non ne ho trovati allora ne inserisco uno
//		if (documento == null) {
//			
//			documento = new DocumentoFattura();
//			documento.setUtenteCreatore(getUsername());
//			
//			documento.setDataGenerazione(new Date());
//			documento.setIdCommessa(idCommessa);
//			documento.setIdAmbito(idAmbito);
//			documento.setMeseAnno(meseAnno);
//			
//			documento.setAnnoFattura(anno);
//			documento.setDataEmissione(new Date());
//			documento.setNumeroFattura(controllerDocumenti.getProssimoNumeroFatturaPerAnno(anno));
//			documento.setIva(Iva.IVA22);
//			documento.setStato(Stato.GENERATA);
//			
//			//Imposto le preferenze di fatturazione
//			PreferenzeFatturazione preferenze = controllerPreferenze.getPreferenzePerCommessaSuAmbito(idCommessa, idAmbito);
//			if (preferenze != null) {
//				documento.setCoordinatePagamento(preferenze.getCoordinatePagamento());
//				documento.setDescrizioneFattura(preferenze.getDescrizioneFattura());
//				documento.setModalitaPagamento(preferenze.getModalitaPagamento());
//			} else {
//				documento.setModalitaPagamento(ModalitaPagamentoFattura.B30);
//			}
//			
//			boolean inserimento = controllerDocumenti.inserisci(documento);
//			idDocumento = inserimento ? documento.getId() : -1;
//			
//		} else {
//			idDocumento = documento.getId();
//		}
//		return idDocumento;
//	}
	
	private String getUsername() {
		String username = ControllerVariabiliGlobali.getInstance().getString("utente.username");
		return username;
	}

}

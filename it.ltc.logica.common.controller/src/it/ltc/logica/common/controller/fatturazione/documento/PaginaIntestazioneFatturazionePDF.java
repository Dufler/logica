package it.ltc.logica.common.controller.fatturazione.documento;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;
import it.ltc.logica.database.model.centrale.Cliente;
import it.ltc.logica.database.model.centrale.fatturazione.CoordinateBancarie;
import it.ltc.logica.database.model.centrale.fatturazione.DocumentoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.ModalitaPagamentoFattura;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.utilities.pdf.DocumentoPDF;
import it.ltc.logica.utilities.pdf.PaginaPDF;
import it.ltc.logica.utilities.pdf.parti.InfoClienteFattura;
import it.ltc.logica.utilities.pdf.parti.IntestazioneFattura;
import it.ltc.logica.utilities.pdf.parti.ModalitaPagamentoFatturaPDF;
import it.ltc.logica.utilities.pdf.parti.TabellaFattura;

public class PaginaIntestazioneFatturazionePDF extends PaginaPDF {
	
	private SimpleDateFormat sdf;
	private final DecimalFormat formatEuro;

	private static final float offsetIniziale = 40;

	public PaginaIntestazioneFatturazionePDF(DocumentoPDF documento, DocumentoFattura fattura, Cliente cliente, Indirizzo indirizzo, double totale, List<TotaleVoceFatturazionePDF> voci) {
		super(documento, false);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		formatEuro = new DecimalFormat();
		formatEuro.applyPattern("#,##0.000 \u20AC");

		float y = getHeight() - offsetIniziale;
		// Intestazione
		IntestazioneFattura intestazione = new IntestazioneFattura(y);
		aggiungiElemento(intestazione);
		// Info cliente e fattura
		String nome = cliente.getRagioneSociale();
		String numeroCliente = cliente.getCodice();
		String via = indirizzo.getIndirizzo();
		String citta = indirizzo.getLocalita();
		String cap = indirizzo.getCap();
		String provincia = indirizzo.getProvincia();
		String telefono = indirizzo.getTelefono();
		String piva = cliente.getPartitaIva();
		String numeroFattura = fattura.getNumeroFattura() + "/" + fattura.getAnnoFattura();
		Date dataEmissione = fattura.getDataEmissione() != null ? fattura.getDataEmissione() : new Date();
		String dataFattura = sdf.format(dataEmissione);
		String modalitaPagamento = getModalitaPagamento(fattura);
		String coordinatePagamento = getCoordinatePagamento(fattura);
		String imponibile = formatEuro.format(totale);
		String iva = fattura.getIva().toString();
		String totaleConIva = formatEuro.format(totale * (1 + fattura.getIva().getAliquota() / 100));
		//String nettoAPagare = null; //TODO - calcola solo per determinati tipi di iva, inserirlo come argument nell'oggetto ModalitaPagamentoFattura
		InfoClienteFattura info = new InfoClienteFattura(y - intestazione.getAltezza() - 12, nome, numeroCliente, via, citta, cap, provincia, telefono, piva, numeroFattura, dataFattura);
		aggiungiElemento(info);
		// Dati fattura
		List<String[]> righeFattura = new LinkedList<>();
		String[] rigaDescrizione = { "", fattura.getDescrizioneFattura(), "", "", "", "" };
		righeFattura.add(rigaDescrizione);
		String[] rigaVuota = { "", "", "", "", "", "" };
		righeFattura.add(rigaVuota);
		for (TotaleVoceFatturazionePDF voce : voci) {
			String[] rigaVoce = voce.getRappresentazione();
			righeFattura.add(rigaVoce);
		}
		TabellaFattura tabella = new TabellaFattura(y - intestazione.getAltezza() - info.getAltezza(), righeFattura, fattura.getNote());
		aggiungiElemento(tabella);
		// modalita pagamento
		float yModalita = y - intestazione.getAltezza() - info.getAltezza() - tabella.getAltezza() - 20;
		ModalitaPagamentoFatturaPDF modalita = new ModalitaPagamentoFatturaPDF(yModalita, modalitaPagamento, coordinatePagamento, imponibile, iva, totaleConIva);
		aggiungiElemento(modalita);
	}
	
	
	
	private String getModalitaPagamento(DocumentoFattura fattura) {
		ModalitaPagamentoFattura mp = fattura.getModalitaPagamento();
		String testo = mp != null ? mp.toString() : "";
		return testo;
	}
	
	private String getCoordinatePagamento(DocumentoFattura fattura) {
		CoordinateBancarie cb = ControllerCoordinateBancarie.getInstance().getCoordinata(fattura.getCoordinatePagamento());
		String testo = cb != null ? cb.getEnte() + "\r\n" + cb.getCoordinate() : "";
		return testo;
	}

}

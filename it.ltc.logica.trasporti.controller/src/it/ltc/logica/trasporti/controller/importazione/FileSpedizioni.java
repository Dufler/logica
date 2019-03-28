package it.ltc.logica.trasporti.controller.importazione;

import java.util.Date;

/**
 * Classe che rappresenta e mappa un file contenente informazioni sulle spedizioni da importare a sistema.
 * @author Damiano
 *
 */
public class FileSpedizioni {
	
	private TipoFileImportazioneSpedizioni tipo;
	private String pathFile;
	private String nomeDocumento;
	private Date dataDocumento;
	private String note;
	
	public FileSpedizioni() {}

	public TipoFileImportazioneSpedizioni getTipo() {
		return tipo;
	}

	public void setTipo(TipoFileImportazioneSpedizioni tipo) {
		this.tipo = tipo;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

package it.ltc.logica.database.model.centrale.trasporti;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="spedizione_simulazione_documento")
@NamedQuery(name="SpedizioneSimulazione.findAll", query="SELECT s FROM SpedizioneSimulazione s")
public class DocumentoSpedizioniSimulazione {
	
	@Id
	@Column(unique=true, nullable=false, insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic
	private int id;
	
	@Column(name="tipo", length=5, nullable=false)
	private String tipo;
	
	@Column(name="nome_file", length=100, nullable=false)
	private String nomeFile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_importazione")
	private Date dataImportazione;
	
	@Column(name="descrizione", columnDefinition="TEXT")
	private String descrizione;
	
	public DocumentoSpedizioniSimulazione() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Date getDataImportazione() {
		return dataImportazione;
	}

	public void setDataImportazione(Date dataImportazione) {
		this.dataImportazione = dataImportazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

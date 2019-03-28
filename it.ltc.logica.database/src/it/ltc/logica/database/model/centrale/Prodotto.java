package it.ltc.logica.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="prodotto")
@IdClass(ProdottoPK.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Prodotto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Cassa { NO, STANDARD, BUNDLE }

	@Id
	@Column(unique=true, nullable=false)
	private int id;
	
	@Id
	@Column(unique=true, nullable=false)
	private int commessa;
	
	@Column(length=10)
	private Cassa cassa;
	
	@Column(name="chiave_cliente", length=50, nullable=false)
	private String chiaveCliente;
	
	@Column(name="codice_modello", length=50, nullable=false)
	private String codiceModello;
	
	@Column(length=50, nullable=false)
	private String barcode;
	
	@Column(length=15, nullable=false)
	private String taglia;
	
	@Column(length=50)
	private String colore;
	
	@Column(length=150)
	private String descrizione;
	
	@Column(name="descrizione_aggiuntiva", length=250)
	private String descrizioneAggiuntiva;
	
	@Column(length=50)
	private String composizione;
	
	@Column(length=50)
	private String brand;
	
	@Column(length=20, nullable=false)
	private String categoria;
	
	@Column(name="made_in", length=3)
	private String madeIn;
	
	@Column(name="sotto_categoria", length=30)
	private String sottoCategoria;
	
	@Column(length=4)
	private String stagione;
	
	@Column(precision=10, scale=3)
	private Double valore;
	
	private Integer h;
	private Integer l;
	private Integer z;
	private Integer peso;
	
	@Column(name="sku_fornitore", length=50)
	private String skuFornitore;
	
	@Column(name="barcode_fornitore", length=50)
	private String barcodeFornitore;
	
	@Column(length=250)
	private String note;
	
	@Column(length=50)
	private String particolarita;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica")
	private Date dataUltimaModifica;
	
	@Column(name="pezzi_effettivi", nullable=false)
	private int pezziEffettivi;

	public Prodotto() {}
	
	@PrePersist
	public void prePersist() {
		if (dataUltimaModifica == null) dataUltimaModifica = new Date(0);
		if (pezziEffettivi <= 0) pezziEffettivi = 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if (commessa != other.commessa)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public ProdottoPK getPK() {
		ProdottoPK pk = new ProdottoPK();
		pk.setId(id);
		pk.setCommessa(commessa);
		return pk;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Cassa getCassa() {
		return this.cassa;
	}

	public void setCassa(Cassa cassa) {
		this.cassa = cassa;
	}

	public String getCodiceModello() {
		return this.codiceModello;
	}

	public void setCodiceModello(String codiceModello) {
		this.codiceModello = codiceModello;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getComposizione() {
		return this.composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneAggiuntiva() {
		return descrizioneAggiuntiva;
	}

	public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
		this.descrizioneAggiuntiva = descrizioneAggiuntiva;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMadeIn() {
		return this.madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getSottoCategoria() {
		return this.sottoCategoria;
	}

	public void setSottoCategoria(String sottoCategoria) {
		this.sottoCategoria = sottoCategoria;
	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public Double getValore() {
		return this.valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getSkuFornitore() {
		return skuFornitore;
	}

	public void setSkuFornitore(String skuFornitore) {
		this.skuFornitore = skuFornitore;
	}

	public String getBarcodeFornitore() {
		return barcodeFornitore;
	}

	public void setBarcodeFornitore(String barcodeFornitore) {
		this.barcodeFornitore = barcodeFornitore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getChiaveCliente() {
		return chiaveCliente;
	}

	public void setChiaveCliente(String chiaveCliente) {
		this.chiaveCliente = chiaveCliente;
	}

	public String getStagione() {
		return stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}
	
	public Integer getH() {
		return this.h;
	}

	public void setH(Integer h) {
		this.h = h;
	}
	
	public Integer getL() {
		return this.l;
	}

	public void setL(Integer l) {
		this.l = l;
	}
	
	public Integer getZ() {
		return this.z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}
	
	public Integer getPeso() {
		return this.peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getParticolarita() {
		return particolarita;
	}

	public void setParticolarita(String particolarita) {
		this.particolarita = particolarita;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public int getPezziEffettivi() {
		return pezziEffettivi;
	}

	public void setPezziEffettivi(int pezziEffettivi) {
		this.pezziEffettivi = pezziEffettivi;
	}

	@Override
	public String toString() {
		return "ProdottoJSON [id=" + id + ", chiaveCliente=" + chiaveCliente + ", barcode=" + barcode + ", descrizione=" + descrizione + "]";
	}

}

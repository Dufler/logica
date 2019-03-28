package it.ltc.logica.database.model.centrale.trasporti;

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

@Entity
@Table(name="cap")
@IdClass(CapPK.class)
public class Cap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, length=5)
	private String cap;
	@Id
	@Column(unique=true, nullable=false, length=200)
	private String localita;
	
	@Column(name="brt_disagiate", nullable=false)
	private boolean brtDisagiate;

	@Column(name="brt_isole", nullable=false)
	private boolean brtIsole;

	@Column(name="brt_ztl", nullable=false)
	private boolean brtZtl;

	@Column(nullable=false, length=2)
	private String provincia;

	@Column(nullable=false, length=3)
	private String regione;

	@Column(name="tnt_ore_dieci", nullable=false)
	private boolean tntOreDieci;

	@Column(name="tnt_ore_dodici", nullable=false)
	private boolean tntOreDodici;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica")
	private Date dataUltimaModifica;

	public Cap() {}
	
	@PrePersist
	public void prePersist() {
		if (dataUltimaModifica == null) dataUltimaModifica = new Date(0);
	}
	
	public CapPK getPK() {
		CapPK pk = new CapPK();
		pk.setCap(cap);
		pk.setLocalita(localita);
		return pk;
	}
	
	public String toString() {
		return cap + " - " + localita;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cap == null) ? 0 : cap.hashCode());
		result = prime * result + ((localita == null) ? 0 : localita.hashCode());
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
		Cap other = (Cap) obj;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		if (localita == null) {
			if (other.localita != null)
				return false;
		} else if (!localita.equals(other.localita))
			return false;
		return true;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public boolean getBrtDisagiate() {
		return this.brtDisagiate;
	}

	public void setBrtDisagiate(boolean brtDisagiate) {
		this.brtDisagiate = brtDisagiate;
	}

	public boolean getBrtIsole() {
		return this.brtIsole;
	}

	public void setBrtIsole(boolean brtIsole) {
		this.brtIsole = brtIsole;
	}

	public boolean getBrtZtl() {
		return this.brtZtl;
	}

	public void setBrtZtl(boolean brtZtl) {
		this.brtZtl = brtZtl;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return this.regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public boolean getTntOreDieci() {
		return this.tntOreDieci;
	}

	public void setTntOreDieci(boolean tntOreDieci) {
		this.tntOreDieci = tntOreDieci;
	}

	public boolean getTntOreDodici() {
		return this.tntOreDodici;
	}

	public void setTntOreDodici(boolean tntOreDodici) {
		this.tntOreDodici = tntOreDodici;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

}

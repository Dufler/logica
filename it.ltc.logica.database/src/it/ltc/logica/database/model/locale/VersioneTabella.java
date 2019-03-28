package it.ltc.logica.database.model.locale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="versione_tabella")
public class VersioneTabella implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="name", unique=true, nullable=false)
	private String tabella;
	
	@Column(name="data_versione")
	private Date dataVersione;
	
	public VersioneTabella() {}

	public String getTabella() {
		return tabella;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

	public Date getDataVersione() {
		return dataVersione;
	}

	public void setDataVersione(Date dataVersione) {
		this.dataVersione = dataVersione;
	}

}

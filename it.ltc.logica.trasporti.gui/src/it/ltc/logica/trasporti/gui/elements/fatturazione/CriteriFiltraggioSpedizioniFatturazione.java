package it.ltc.logica.trasporti.gui.elements.fatturazione;

import java.util.Date;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioSpedizioniFatturazione extends CriteriFiltraggio {
	
	private Date da;
	private Date a;
	private Boolean contrassegno;
	private Boolean datiCompleti;
	private String riferimento;
	
	public Date getDa() {
		return da;
	}

	public void setDa(Date da) {
		this.da = da;
	}

	public Date getA() {
		return a;
	}

	public void setA(Date a) {
		this.a = a;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Boolean getDatiCompleti() {
		return datiCompleti;
	}

	public void setDatiCompleti(Boolean datiCompleti) {
		this.datiCompleti = datiCompleti;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

}

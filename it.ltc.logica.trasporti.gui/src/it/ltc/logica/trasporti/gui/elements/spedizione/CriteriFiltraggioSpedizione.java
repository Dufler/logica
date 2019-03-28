package it.ltc.logica.trasporti.gui.elements.spedizione;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;

public class CriteriFiltraggioSpedizione extends CriteriFiltraggio {
	
	private Date da;
	private Date a;
	private Integer idCommessa;
	private Integer idCorriere;
	private Boolean contrassegno;
	private String riferimento;
	private Boolean datiCompleti;
	private Boolean giacenza;
	private Boolean ritardo;
	
	public CriteriFiltraggioSpedizione() {}
	
	public CriteriSelezioneSpedizioni getCriteriDiSelezione() {
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setDataDa(da);
		criteri.setDataA(a);
		criteri.setCommessa(idCommessa);
		criteri.setCorriere(idCorriere);
		criteri.setContrassegno(contrassegno);
		criteri.setRiferimento(riferimento);
		criteri.setDatiCompleti(datiCompleti);
		criteri.setGiacenza(giacenza);
		criteri.setInRitardo(ritardo);
		return criteri;
	}
	
	public Date getDa() {
		return da;
	}

	public void setDa(Date da) {
		Date inizio = null;
		if (da != null) {
			Calendar d = new GregorianCalendar();
			d.setTime(da);
			d.set(Calendar.DAY_OF_YEAR, d.get(Calendar.DAY_OF_YEAR) - 1);
			d.set(Calendar.HOUR_OF_DAY, 23);
			d.set(Calendar.MINUTE, 59);
			d.set(Calendar.SECOND, 59);
			inizio = d.getTime();
		}
		this.da = inizio;
	}

	public Date getA() {
		return a;
	}

	public void setA(Date a) {
		Date fine = null;
		if (a != null) {
			Calendar d = new GregorianCalendar();
			d.setTime(a);
			d.set(Calendar.HOUR_OF_DAY, 23);
			d.set(Calendar.MINUTE, 59);
			d.set(Calendar.SECOND, 59);
			fine = d.getTime();
		}
		this.a = fine;
	}

	public Integer getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(Integer idCommessa) {
		this.idCommessa = idCommessa;
	}

	public Integer getIdCorriere() {
		return idCorriere;
	}

	public void setIdCorriere(Integer idCorriere) {
		this.idCorriere = idCorriere;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public Boolean getDatiCompleti() {
		return datiCompleti;
	}

	public void setDatiCompleti(Boolean datiCompleti) {
		this.datiCompleti = datiCompleti;
	}

	public Boolean getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(Boolean giacenza) {
		this.giacenza = giacenza;
	}

	public Boolean getRitardo() {
		return ritardo;
	}

	public void setRitardo(Boolean ritardo) {
		this.ritardo = ritardo;
	}

}

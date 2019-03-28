package it.ltc.database.dao.locali;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;

public class CriteriSelezioneSpedizioni {
	
	private Date dataDa;
	private Date dataA;
	private Integer commessa;
	private Integer corriere;
	private Integer idDocumento;
	private Boolean contrassegno;
	private Boolean giacenza;
	private Boolean datiCompleti;
	private Boolean inRitardo;
	private String riferimento;
	private String letteraDiVettura;
	private String codiceCliente;
	private Integer minimoPezzi;
	private Integer massimoPezzi;
	private Integer pezziUguali;
	private Integer minimoColli;
	private Integer massimoColli;
	private Integer colliUguali;
	private Double minimoPeso;
	private Double massimoPeso;
	private Double minimoVolume;
	private Double massimoVolume;
	private Fatturazione fatturazione;
	private String servizio;
	private TipoSpedizione tipo;
	
	private boolean pezziNecessari;
	private boolean colliNecessari;
	private boolean pesoNecessario;
	private boolean volumeNecessario;
	
	public CriteriSelezioneSpedizioni() {}
	
	protected Predicate[] getCriteri(CriteriaBuilder cb, CriteriaQuery<?> criteria, Root<?> member) {
		ArrayList<Predicate> predicati = new ArrayList<>();
		//Creo le condizioni
		if (dataDa != null) {
			Predicate predicato = cb.greaterThanOrEqualTo(member.get("dataPartenza"), dataDa);
			predicati.add(predicato);
		}
		if (dataA != null) {
			Predicate predicato = cb.lessThanOrEqualTo(member.get("dataPartenza"), dataA);
			predicati.add(predicato);
		}
		if (commessa != null) {
			Predicate predicato = cb.equal(member.get("idCommessa"), commessa);
			predicati.add(predicato);
		}
		if (corriere != null) {
			Predicate predicato = cb.equal(member.get("idCorriere"), corriere);
			predicati.add(predicato);
		}
		if (idDocumento != null) {
			Predicate predicato = cb.equal(member.get("idDocumento"), idDocumento);
			predicati.add(predicato);
		}
		if (contrassegno != null) {
			Predicate predicato = cb.equal(member.get("contrassegno"), contrassegno);
			predicati.add(predicato);
		}
		if (giacenza != null) {
			Predicate predicato = cb.equal(member.get("giacenza"), giacenza);
			predicati.add(predicato);
		}
		if (datiCompleti != null) {
			Predicate predicato = cb.equal(member.get("datiCompleti"), datiCompleti);
			predicati.add(predicato);
		}
		if (inRitardo != null) {
			Predicate predicato = cb.equal(member.get("inRitardo"), inRitardo);
			predicati.add(predicato);
		}
		if (riferimento != null && !riferimento.isEmpty()) {
			Predicate predicatoCliente = cb.like(member.get("riferimentoCliente"), "%" + riferimento + "%");
			Predicate predicatoMittente = cb.like(member.get("riferimentoMittente"), "%" + riferimento + "%");
			Predicate predicatoRagioneSociale = cb.like(member.get("ragioneSocialeDestinatario"), "%" + riferimento + "%");
			Predicate predicato = cb.or(predicatoCliente, predicatoMittente, predicatoRagioneSociale);
			predicati.add(predicato);
		}
		if (letteraDiVettura != null && !letteraDiVettura.isEmpty()) {
			Predicate predicato = cb.equal(member.get("letteraDiVettura"), letteraDiVettura);
			predicati.add(predicato);
		}
		if (codiceCliente != null && !codiceCliente.isEmpty()) {
			Predicate predicato = cb.equal(member.get("codiceCliente"), codiceCliente);
			predicati.add(predicato);
		}
		if (minimoPezzi != null) {
			Predicate predicato = cb.greaterThanOrEqualTo(member.get("pezzi"), minimoPezzi);
			predicati.add(predicato);
		}
		if (massimoPezzi != null) {
			Predicate predicato = cb.lessThanOrEqualTo(member.get("pezzi"), massimoPezzi);
			predicati.add(predicato);
		}
		if (pezziUguali != null) {
			Predicate predicato = cb.equal(member.get("pezzi"), pezziUguali);
			predicati.add(predicato);
		}
		if (minimoColli != null) {
			Predicate predicato = cb.greaterThanOrEqualTo(member.get("colli"), minimoColli);
			predicati.add(predicato);
		}
		if (massimoColli != null) {
			Predicate predicato = cb.lessThanOrEqualTo(member.get("colli"), massimoColli);
			predicati.add(predicato);
		}
		if (colliUguali != null) {
			Predicate predicato = cb.equal(member.get("colli"), colliUguali);
			predicati.add(predicato);
		}
		if (minimoPeso != null) {
			Predicate predicato = cb.greaterThanOrEqualTo(member.get("peso"), minimoPeso);
			predicati.add(predicato);
		}
		if (massimoPeso != null) {
			Predicate predicato = cb.lessThanOrEqualTo(member.get("peso"), massimoPeso);
			predicati.add(predicato);
		}
		if (minimoVolume != null) {
			Predicate predicato = cb.greaterThanOrEqualTo(member.get("volume"), minimoVolume);
			predicati.add(predicato);
		}
		if (massimoVolume != null) {
			Predicate predicato = cb.lessThanOrEqualTo(member.get("volume"), massimoVolume);
			predicati.add(predicato);
		}
		if (fatturazione != null) {
			Predicate predicato = cb.equal(member.get("fatturazione"), fatturazione);
			predicati.add(predicato);
		}
		if (servizio != null && !servizio.isEmpty()) {
			Predicate predicato = cb.equal(member.get("servizio"), servizio);
			predicati.add(predicato);
		}
		if (tipo != null) {
			Predicate predicato = cb.equal(member.get("tipo"), tipo);
			predicati.add(predicato);
		}
		//Trasformo in array e restituisco
		Predicate[] array = new Predicate[predicati.size()];
		return predicati.toArray(array);
	}
	
//	protected Predicate[] getCriteriSimulazione(CriteriaBuilder cb, CriteriaQuery<SpedizioneSimulazione> criteria, Root<SpedizioneSimulazione> member) {
//		ArrayList<Predicate> predicati = new ArrayList<>();
//		//Creo le condizioni
//		if (dataDa != null) {
//			Predicate predicato = cb.greaterThanOrEqualTo(member.get("dataPartenza"), dataDa);
//			predicati.add(predicato);
//		}
//		if (dataA != null) {
//			Predicate predicato = cb.lessThanOrEqualTo(member.get("dataPartenza"), dataA);
//			predicati.add(predicato);
//		}
//		if (commessa != null) {
//			Predicate predicato = cb.equal(member.get("idCommessa"), commessa);
//			predicati.add(predicato);
//		}
//		if (corriere != null) {
//			Predicate predicato = cb.equal(member.get("idCorriere"), corriere);
//			predicati.add(predicato);
//		}
//		if (contrassegno != null) {
//			Predicate predicato = cb.equal(member.get("contrassegno"), contrassegno);
//			predicati.add(predicato);
//		}
//		if (giacenza != null) {
//			Predicate predicato = cb.equal(member.get("giacenza"), giacenza);
//			predicati.add(predicato);
//		}
//		if (datiCompleti != null) {
//			Predicate predicato = cb.equal(member.get("datiCompleti"), datiCompleti);
//			predicati.add(predicato);
//		}
//		if (inRitardo != null) {
//			Predicate predicato = cb.equal(member.get("inRitardo"), inRitardo);
//			predicati.add(predicato);
//		}
//		if (riferimento != null && !riferimento.isEmpty()) {
//			Predicate predicatoCliente = cb.like(member.get("riferimentoCliente"), "%" + riferimento + "%");
//			Predicate predicatoMittente = cb.like(member.get("riferimentoMittente"), "%" + riferimento + "%");
//			Predicate predicatoRagioneSociale = cb.like(member.get("ragioneSocialeDestinatario"), "%" + riferimento + "%");
//			Predicate predicato = cb.or(predicatoCliente, predicatoMittente, predicatoRagioneSociale);
//			predicati.add(predicato);
//		}
//		if (letteraDiVettura != null && !letteraDiVettura.isEmpty()) {
//			Predicate predicato = cb.equal(member.get("letteraDiVettura"), letteraDiVettura);
//			predicati.add(predicato);
//		}
//		if (codiceCliente != null && !codiceCliente.isEmpty()) {
//			Predicate predicato = cb.equal(member.get("codiceCliente"), codiceCliente);
//			predicati.add(predicato);
//		}
//		if (minimoPezzi != null) {
//			Predicate predicato = cb.greaterThanOrEqualTo(member.get("pezzi"), minimoPezzi);
//			predicati.add(predicato);
//		}
//		if (massimoPezzi != null) {
//			Predicate predicato = cb.lessThanOrEqualTo(member.get("pezzi"), massimoPezzi);
//			predicati.add(predicato);
//		}
//		if (pezziUguali != null) {
//			Predicate predicato = cb.equal(member.get("pezzi"), pezziUguali);
//			predicati.add(predicato);
//		}
//		if (minimoColli != null) {
//			Predicate predicato = cb.greaterThanOrEqualTo(member.get("colli"), minimoColli);
//			predicati.add(predicato);
//		}
//		if (massimoColli != null) {
//			Predicate predicato = cb.lessThanOrEqualTo(member.get("colli"), massimoColli);
//			predicati.add(predicato);
//		}
//		if (colliUguali != null) {
//			Predicate predicato = cb.equal(member.get("colli"), colliUguali);
//			predicati.add(predicato);
//		}
//		if (minimoPeso != null) {
//			Predicate predicato = cb.greaterThanOrEqualTo(member.get("peso"), minimoPeso);
//			predicati.add(predicato);
//		}
//		if (massimoPeso != null) {
//			Predicate predicato = cb.lessThanOrEqualTo(member.get("peso"), massimoPeso);
//			predicati.add(predicato);
//		}
//		if (minimoVolume != null) {
//			Predicate predicato = cb.greaterThanOrEqualTo(member.get("volume"), minimoVolume);
//			predicati.add(predicato);
//		}
//		if (massimoVolume != null) {
//			Predicate predicato = cb.lessThanOrEqualTo(member.get("volume"), massimoVolume);
//			predicati.add(predicato);
//		}
//		if (fatturazione != null) {
//			Predicate predicato = cb.equal(member.get("fatturazione"), fatturazione);
//			predicati.add(predicato);
//		}
//		if (servizio != null && !servizio.isEmpty()) {
//			Predicate predicato = cb.equal(member.get("servizio"), servizio);
//			predicati.add(predicato);
//		}
//		if (tipo != null) {
//			Predicate predicato = cb.equal(member.get("tipo"), tipo);
//			predicati.add(predicato);
//		}
//		//Trasformo in array e restituisco
//		Predicate[] array = new Predicate[predicati.size()];
//		return predicati.toArray(array);
//	}

	public Date getDataDa() {
		return dataDa;
	}

	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}

	public Date getDataA() {
		return dataA;
	}

	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public void setCommessa(Integer commessa) {
		this.commessa = commessa;
	}

	public Integer getCorriere() {
		return corriere;
	}

	public void setCorriere(Integer corriere) {
		this.corriere = corriere;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Boolean getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(Boolean giacenza) {
		this.giacenza = giacenza;
	}

	public Boolean getDatiCompleti() {
		return datiCompleti;
	}

	public void setDatiCompleti(Boolean datiCompleti) {
		this.datiCompleti = datiCompleti;
	}

	public Boolean getInRitardo() {
		return inRitardo;
	}

	public void setInRitardo(Boolean inRitardo) {
		this.inRitardo = inRitardo;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public String getLetteraDiVettura() {
		return letteraDiVettura;
	}

	public void setLetteraDiVettura(String letteraDiVettura) {
		this.letteraDiVettura = letteraDiVettura;
	}

	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public Integer getMinimoPezzi() {
		return minimoPezzi;
	}

	public void setMinimoPezzi(Integer minimoPezzi) {
		this.minimoPezzi = minimoPezzi;
	}

	public Integer getMassimoPezzi() {
		return massimoPezzi;
	}

	public void setMassimoPezzi(Integer massimoPezzi) {
		this.massimoPezzi = massimoPezzi;
	}

	public Integer getPezziUguali() {
		return pezziUguali;
	}

	public void setPezziUguali(Integer pezziUguali) {
		this.pezziUguali = pezziUguali;
	}

	public Integer getMinimoColli() {
		return minimoColli;
	}

	public void setMinimoColli(Integer minimoColli) {
		this.minimoColli = minimoColli;
	}

	public Integer getMassimoColli() {
		return massimoColli;
	}

	public void setMassimoColli(Integer massimoColli) {
		this.massimoColli = massimoColli;
	}

	public Integer getColliUguali() {
		return colliUguali;
	}

	public void setColliUguali(Integer colliUguali) {
		this.colliUguali = colliUguali;
	}

	public Double getMinimoPeso() {
		return minimoPeso;
	}

	public void setMinimoPeso(Double minimoPeso) {
		this.minimoPeso = minimoPeso;
	}

	public Double getMassimoPeso() {
		return massimoPeso;
	}

	public void setMassimoPeso(Double massimoPeso) {
		this.massimoPeso = massimoPeso;
	}

	public Double getMinimoVolume() {
		return minimoVolume;
	}

	public void setMinimoVolume(Double minimoVolume) {
		this.minimoVolume = minimoVolume;
	}

	public Double getMassimoVolume() {
		return massimoVolume;
	}

	public void setMassimoVolume(Double massimoVolume) {
		this.massimoVolume = massimoVolume;
	}

	public Fatturazione getFatturazione() {
		return fatturazione;
	}

	public void setFatturazione(Fatturazione fatturazione) {
		this.fatturazione = fatturazione;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public TipoSpedizione getTipo() {
		return tipo;
	}

	public void setTipo(TipoSpedizione tipo) {
		this.tipo = tipo;
	}

	public boolean isPezziNecessari() {
		return pezziNecessari;
	}

	public void setPezziNecessari(boolean pezziNecessari) {
		this.pezziNecessari = pezziNecessari;
	}

	public boolean isColliNecessari() {
		return colliNecessari;
	}

	public void setColliNecessari(boolean colliNecessari) {
		this.colliNecessari = colliNecessari;
	}

	public boolean isPesoNecessario() {
		return pesoNecessario;
	}

	public void setPesoNecessario(boolean pesoNecessario) {
		this.pesoNecessario = pesoNecessario;
	}

	public boolean isVolumeNecessario() {
		return volumeNecessario;
	}

	public void setVolumeNecessario(boolean volumeNecessario) {
		this.volumeNecessario = volumeNecessario;
	}

}

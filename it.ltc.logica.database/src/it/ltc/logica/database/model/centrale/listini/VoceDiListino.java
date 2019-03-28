package it.ltc.logica.database.model.centrale.listini;

import java.util.List;

public class VoceDiListino {

	private Integer id;
	private Integer idListino;
	private Integer idSottoAmbito;
	private String valoreSottoAmbito;
	private String nome;
	private String descrizione;
	private String tipoCalcolo;
	private String strategiaCalcolo;
	
	//Test
	private VoceDiListinoFissa fissa;
	private VoceDiListinoPercentuale percentuale;
	private VoceDiListinoProporzionale proporzionale;
	private VoceDiListinoScaglioniRipetuti ripetuti;
	private List<VoceDiListinoScaglioni> scaglioni;	

	public VoceDiListinoFissa getFissa() {
		return fissa;
	}

	public void setFissa(VoceDiListinoFissa fissa) {
		this.fissa = fissa;
	}

	public VoceDiListinoPercentuale getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(VoceDiListinoPercentuale percentuale) {
		this.percentuale = percentuale;
	}

	public VoceDiListinoProporzionale getProporzionale() {
		return proporzionale;
	}

	public void setProporzionale(VoceDiListinoProporzionale proporzionale) {
		this.proporzionale = proporzionale;
	}

	public VoceDiListinoScaglioniRipetuti getRipetuti() {
		return ripetuti;
	}

	public void setRipetuti(VoceDiListinoScaglioniRipetuti ripetuti) {
		this.ripetuti = ripetuti;
	}

	public List<VoceDiListinoScaglioni> getScaglioni() {
		return scaglioni;
	}

	public void setScaglioni(List<VoceDiListinoScaglioni> scaglioni) {
		this.scaglioni = scaglioni;
	}
	
	//Fine test

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		VoceDiListino other = (VoceDiListino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdListino() {
		return idListino;
	}

	public void setIdListino(Integer idListino) {
		this.idListino = idListino;
	}

	public Integer getIdSottoAmbito() {
		return idSottoAmbito;
	}

	public void setIdSottoAmbito(Integer idsottoAmbito) {
		this.idSottoAmbito = idsottoAmbito;
	}

	public String getValoreSottoAmbito() {
		return valoreSottoAmbito;
	}

	public void setValoreSottoAmbito(String valoreSottoAmbito) {
		this.valoreSottoAmbito = valoreSottoAmbito;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTipoCalcolo() {
		return tipoCalcolo;
	}

	public void setTipoCalcolo(String tipo) {
		this.tipoCalcolo = tipo;
	}

	public String getStrategiaCalcolo() {
		return strategiaCalcolo;
	}

	public void setStrategiaCalcolo(String strategia) {
		this.strategiaCalcolo = strategia;
	}

}

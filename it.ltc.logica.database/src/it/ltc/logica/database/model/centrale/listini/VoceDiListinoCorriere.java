package it.ltc.logica.database.model.centrale.listini;

import java.util.List;

public class VoceDiListinoCorriere {

	private Integer id;
	private Integer idListino;
	private String nome;
	private String descrizione;
	private String tipoCalcolo;
	private String strategiaCalcolo;
	private Integer idSottoAmbito;
	private String valoreSottoAmbito;
	
	
	//Aggiunte
	private VoceDiListinoCorriereFissa fissa;
	private VoceDiListinoCorrierePercentuale percentuale;
	private VoceDiListinoCorriereProporzionale proporzionale;
	private VoceDiListinoCorriereScaglioniRipetuti ripetuti;
	private List<VoceDiListinoCorriereScaglioni> scaglioni;

	public VoceDiListinoCorriereFissa getFissa() {
		return fissa;
	}

	public void setFissa(VoceDiListinoCorriereFissa fissa) {
		this.fissa = fissa;
	}

	public VoceDiListinoCorrierePercentuale getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(VoceDiListinoCorrierePercentuale percentuale) {
		this.percentuale = percentuale;
	}

	public VoceDiListinoCorriereProporzionale getProporzionale() {
		return proporzionale;
	}

	public void setProporzionale(VoceDiListinoCorriereProporzionale proporzionale) {
		this.proporzionale = proporzionale;
	}

	public VoceDiListinoCorriereScaglioniRipetuti getRipetuti() {
		return ripetuti;
	}

	public void setRipetuti(VoceDiListinoCorriereScaglioniRipetuti ripetuti) {
		this.ripetuti = ripetuti;
	}

	public List<VoceDiListinoCorriereScaglioni> getScaglioni() {
		return scaglioni;
	}

	public void setScaglioni(List<VoceDiListinoCorriereScaglioni> scaglioni) {
		this.scaglioni = scaglioni;
	}
	
	//Fine
	
	@Override
	public String toString() {
		return "VoceDiListinoCorriere [nome=" + nome + ", strategia=" + strategiaCalcolo + ", idSottoAmbito=" + idSottoAmbito + "]";
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
		VoceDiListinoCorriere other = (VoceDiListinoCorriere) obj;
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
	
	public void setTipoCalcolo(String tipo) {
		this.tipoCalcolo = tipo;
	}

	public String getTipoCalcolo() {
		return tipoCalcolo;
	}

	public String getStrategiaCalcolo() {
		return strategiaCalcolo;
	}

	public void setStrategiaCalcolo(String strategia) {
		this.strategiaCalcolo = strategia;
	}

	public Integer getIdSottoAmbito() {
		return idSottoAmbito;
	}

	public void setIdSottoAmbito(Integer idSottoAmbito) {
		this.idSottoAmbito = idSottoAmbito;
	}

	public String getValoreSottoAmbito() {
		return valoreSottoAmbito;
	}

	public void setValoreSottoAmbito(String valoreAmbito) {
		this.valoreSottoAmbito = valoreAmbito;
	}

}

package it.ltc.logica.database.model.locale;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="listino_simulazione_voce")
public class ListinoSimulazioneVoce implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic
	private int id;
	
	@Column(name="id_listino", nullable=false)
	private int idListino;
	
	@Column(name="id_sotto_ambito", nullable=false)
	private int idsottoAmbito;
	
	@Column(name="valore_sotto_ambito")
	private String valoreSottoAmbito;
	
	@Column(name="nome", nullable=false)
	private String nome;
	private String descrizione;
	
	@Column(name="tipo_calcolo")
	private String tipo;
	
	@Column(name="strategia_calcolo")
	private String strategia;
	
	//Valori con entity a parte
	@Transient
	private ListinoSimulazioneVoceFissa fissa;
	@Transient
	private ListinoSimulazioneVocePercentuale percentuale;
	@Transient
	private ListinoSimulazioneVoceProporzionale proporzionale;
	@Transient
	private List<ListinoSimulazioneVoceScaglioni> scaglioni;
	@Transient
	private ListinoSimulazioneVoceScaglioniRipetuti ripetuti;

	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS listino_simulazione_voce ("
                + "	id INTEGER PRIMARY KEY, "
				+ " id_listino INTEGER NOT NULL REFERENCES listino_simulazione(id) ON UPDATE CASCADE ON DELETE CASCADE, "
				+ " id_sotto_ambito INTEGER NOT NULL, "
				+ "	valore_sotto_ambito text,"
				+ "	tipo_calcolo text NOT NULL,"
				+ "	strategia_calcolo text,"
                + "	nome text NOT NULL,"
                + " descrizione text );";
        return tableDefinition;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ListinoSimulazioneVoce other = (ListinoSimulazioneVoce) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdListino() {
		return idListino;
	}

	public void setIdListino(int idListino) {
		this.idListino = idListino;
	}

	public int getIdsottoAmbito() {
		return idsottoAmbito;
	}

	public void setIdsottoAmbito(int idsottoAmbito) {
		this.idsottoAmbito = idsottoAmbito;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStrategia() {
		return strategia;
	}

	public void setStrategia(String strategia) {
		this.strategia = strategia;
	}

	public ListinoSimulazioneVoceFissa getFissa() {
		return fissa;
	}

	public void setFissa(ListinoSimulazioneVoceFissa fissa) {
		this.fissa = fissa;
	}

	public ListinoSimulazioneVocePercentuale getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(ListinoSimulazioneVocePercentuale percentuale) {
		this.percentuale = percentuale;
	}

	public ListinoSimulazioneVoceProporzionale getProporzionale() {
		return proporzionale;
	}

	public void setProporzionale(ListinoSimulazioneVoceProporzionale proporzionale) {
		this.proporzionale = proporzionale;
	}

	public List<ListinoSimulazioneVoceScaglioni> getScaglioni() {
		return scaglioni;
	}

	public void setScaglioni(List<ListinoSimulazioneVoceScaglioni> scaglioni) {
		this.scaglioni = scaglioni;
	}

	public ListinoSimulazioneVoceScaglioniRipetuti getRipetuti() {
		return ripetuti;
	}

	public void setRipetuti(ListinoSimulazioneVoceScaglioniRipetuti ripetuti) {
		this.ripetuti = ripetuti;
	}

}

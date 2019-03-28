package it.ltc.logica.database.model.centrale.utenti;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Utente {

	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String risorsaTemporanea;
	private Date scadenzaRisorsa;

	// Aggiunte
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<Integer> sedi;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<Integer> commesse;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<Integer> permessi;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<String> features;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nuovaPassword;
	
	public Set<Integer> getSedi() {
		return sedi;
	}

	public void setSedi(Set<Integer> sedi) {
		this.sedi = sedi;
	}

	public Set<Integer> getCommesse() {
		return commesse;
	}

	public void setCommesse(Set<Integer> commesse) {
		this.commesse = commesse;
	}

	public Set<Integer> getPermessi() {
		return permessi;
	}

	public void setPermessi(Set<Integer> permessi) {
		this.permessi = permessi;
	}

	public Set<String> getFeatures() {
		return features;
	}

	public void setFeatures(Set<String> features) {
		this.features = features;
	}

	public String getNuovaPassword() {
		return nuovaPassword;
	}

	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
	}
	// Fine

	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRisorsaTemporanea() {
		return risorsaTemporanea;
	}

	public void setRisorsaTemporanea(String risorsaTemporanea) {
		this.risorsaTemporanea = risorsaTemporanea;
	}

	public Date getScadenzaRisorsa() {
		return scadenzaRisorsa;
	}

	public void setScadenzaRisorsa(Date scadenzaRisorsa) {
		this.scadenzaRisorsa = scadenzaRisorsa;
	}

}

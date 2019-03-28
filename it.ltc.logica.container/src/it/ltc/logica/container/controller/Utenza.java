package it.ltc.logica.container.controller;

public class Utenza {
	
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private int[] sedi;
	private int[] commesse;
	private int[] permessi;
	private String[] features;
	
	public Utenza() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int[] getSedi() {
		return sedi;
	}

	public void setSedi(int[] sedi) {
		this.sedi = sedi;
	}

	public int[] getCommesse() {
		return commesse;
	}

	public void setCommesse(int[] commesse) {
		this.commesse = commesse;
	}

	public int[] getPermessi() {
		return permessi;
	}

	public void setPermessi(int[] permessi) {
		this.permessi = permessi;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email;
	}
	
}

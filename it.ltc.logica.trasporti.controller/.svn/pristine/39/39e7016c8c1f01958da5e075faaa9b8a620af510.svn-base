package it.ltc.logica.trasporti.controller;

import java.util.Date;

public class FiltroSpedizioni {
	
	public static final long GIORNO = 86400000;
	
	private Date da;
	private Date a;
	private Integer commessa;
	private Integer sede;
	
	public static FiltroSpedizioni getFiltroSpedizioniRecenti() {
		FiltroSpedizioni filtro = new FiltroSpedizioni();
		Date oggi = new Date();
		filtro.setA(oggi);
		long dateUnMeseFa = oggi.getTime() - (GIORNO*30);
		Date unMeseFa = new Date(dateUnMeseFa);
		filtro.setDa(unMeseFa);
		return filtro;
	}
	
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
	public Integer getCommessa() {
		return commessa;
	}
	public void setCommessa(Integer commessa) {
		this.commessa = commessa;
	}
	public Integer getSede() {
		return sede;
	}
	public void setSede(Integer sede) {
		this.sede = sede;
	}

}

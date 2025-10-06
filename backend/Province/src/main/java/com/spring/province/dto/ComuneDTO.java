package com.spring.province.dto;



public class ComuneDTO {
	
	private ProvinciaDTO provincia;
	private Integer codice_istat;
	private String denominazione_ita_altra,denominazione_ita,denominazione_altra, flag_capoluogo, codice_belfiore, lat, lon, superficie_kmq;
	private Integer codice_sovracomunale;
	public ComuneDTO(ProvinciaDTO provincia, Integer codice_istat, String denominazione_ita_altra, String denominazione_ita,
			String denominazione_altra, String flag_capoluogo, String codice_belfiore, String lat, String lon,
			String superficie_kmq, Integer codice_sovracomunale) {
		super();
		this.provincia = provincia;
		this.codice_istat = codice_istat;
		this.denominazione_ita_altra = denominazione_ita_altra;
		this.denominazione_ita = denominazione_ita;
		this.denominazione_altra = denominazione_altra;
		this.flag_capoluogo = flag_capoluogo;
		this.codice_belfiore = codice_belfiore;
		this.lat = lat;
		this.lon = lon;
		this.superficie_kmq = superficie_kmq;
		this.codice_sovracomunale = codice_sovracomunale;
	}
	public ComuneDTO() {
		super();
	}
	public ProvinciaDTO getProvincia() {
		return provincia;
	}
	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}
	public Integer getCodice_istat() {
		return codice_istat;
	}
	public void setCodice_istat(Integer codice_istat) {
		this.codice_istat = codice_istat;
	}
	public String getDenominazione_ita_altra() {
		return denominazione_ita_altra;
	}
	public void setDenominazione_ita_altra(String denominazione_ita_altra) {
		this.denominazione_ita_altra = denominazione_ita_altra;
	}
	public String getDenominazione_ita() {
		return denominazione_ita;
	}
	public void setDenominazione_ita(String denominazione_ita) {
		this.denominazione_ita = denominazione_ita;
	}
	public String getDenominazione_altra() {
		return denominazione_altra;
	}
	public void setDenominazione_altra(String denominazione_altra) {
		this.denominazione_altra = denominazione_altra;
	}
	public String getFlag_capoluogo() {
		return flag_capoluogo;
	}
	public void setFlag_capoluogo(String flag_capoluogo) {
		this.flag_capoluogo = flag_capoluogo;
	}
	public String getCodice_belfiore() {
		return codice_belfiore;
	}
	public void setCodice_belfiore(String codice_belfiore) {
		this.codice_belfiore = codice_belfiore;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getSuperficie_kmq() {
		return superficie_kmq;
	}
	public void setSuperficie_kmq(String superficie_kmq) {
		this.superficie_kmq = superficie_kmq;
	}
	public Integer getCodice_sovracomunale() {
		return codice_sovracomunale;
	}
	public void setCodice_sovracomunale(Integer codice_sovracomunale) {
		this.codice_sovracomunale = codice_sovracomunale;
	}
	
	
	
}

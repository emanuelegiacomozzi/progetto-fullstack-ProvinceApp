package com.spring.province.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="gi_province")
public class Provincia {
	
	private int codice_regione;
	
	@Id
	private String sigla_provincia;
	
	private String denominazione_provincia, tipologia_provincia;
	
	private int numero_comuni;
	
	private String superficie_kmq;
	
	private int codice_sovracomunale;
	
	@OneToMany(mappedBy = "provincia")
	private List<Comune> comuni;

	public Provincia(int codice_regione, String sigla_provincia, String denominazione_provincia,
			String tipologia_provincia, int numero_comuni, String superficie_kmq, int codice_sovracomunale) {
		super();
		this.codice_regione = codice_regione;
		this.sigla_provincia = sigla_provincia;
		this.denominazione_provincia = denominazione_provincia;
		this.tipologia_provincia = tipologia_provincia;
		this.numero_comuni = numero_comuni;
		this.superficie_kmq = superficie_kmq;
		this.codice_sovracomunale = codice_sovracomunale;
	}

	public Provincia() {
		super();
	}

	public int getCodice_regione() {
		return codice_regione;
	}

	public void setCodice_regione(int codice_regione) {
		this.codice_regione = codice_regione;
	}

	public String getSigla_provincia() {
		return sigla_provincia;
	}

	public void setSigla_provincia(String sigla_provincia) {
		this.sigla_provincia = sigla_provincia;
	}

	public String getDenominazione_provincia() {
		return denominazione_provincia;
	}

	public void setDenominazione_provincia(String denominazione_provincia) {
		this.denominazione_provincia = denominazione_provincia;
	}

	public String getTipologia_provincia() {
		return tipologia_provincia;
	}

	public void setTipologia_provincia(String tipologia_provincia) {
		this.tipologia_provincia = tipologia_provincia;
	}

	public int getNumero_comuni() {
		return numero_comuni;
	}

	public void setNumero_comuni(int numero_comuni) {
		this.numero_comuni = numero_comuni;
	}

	public String getSuperficie_kmq() {
		return superficie_kmq;
	}

	public void setSuperficie_kmq(String superficie_kmq) {
		this.superficie_kmq = superficie_kmq;
	}

	public int getCodice_sovracomunale() {
		return codice_sovracomunale;
	}

	public void setCodice_sovracomunale(int codice_sovracomunale) {
		this.codice_sovracomunale = codice_sovracomunale;
	}

	public List<Comune> getComuni() {
		return comuni;
	}

	public void setComuni(List<Comune> comuni) {
		this.comuni = comuni;
	}
	
	
	
}

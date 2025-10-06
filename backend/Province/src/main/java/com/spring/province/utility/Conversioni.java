package com.spring.province.utility;

import com.spring.province.dto.ComuneDTO;
import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Comune;
import com.spring.province.entity.Provincia;

public class Conversioni {
	
	public static ProvinciaDTO daProvinciaAProvinciaDTO(Provincia provincia) {
		return new ProvinciaDTO(provincia.getCodice_regione(), provincia.getSigla_provincia(), provincia.getDenominazione_provincia(), provincia.getTipologia_provincia(), provincia.getNumero_comuni(), provincia.getSuperficie_kmq(), provincia.getCodice_sovracomunale());
	}
	
	public static Provincia daProvinciaDTOAProvincia(ProvinciaDTO provinciaDTO) {
		return new Provincia(provinciaDTO.getCodice_regione(), provinciaDTO.getSigla_provincia(), provinciaDTO.getDenominazione_provincia(), provinciaDTO.getTipologia_provincia(), provinciaDTO.getNumero_comuni(), provinciaDTO.getSuperficie_kmq(), provinciaDTO.getCodice_sovracomunale());
	}
	
	public static ComuneDTO daComuneAComuneDTO(Comune comune) {
		return new ComuneDTO(daProvinciaAProvinciaDTO(comune.getProvincia()), comune.getCodice_istat(), comune.getDenominazione_ita_altra(), comune.getDenominazione_ita(), comune.getDenominazione_altra(), comune.getFlag_capoluogo(), comune.getCodice_belfiore(), comune.getLat(), comune.getLon(), comune.getSuperficie_kmq(), comune.getCodice_sovracomunale());
	}
	
	public static Comune daComuneDTOAComune(ComuneDTO comuneDTO) {
		return new Comune(daProvinciaDTOAProvincia(comuneDTO.getProvincia()), comuneDTO.getCodice_istat(), comuneDTO.getDenominazione_ita_altra(), comuneDTO.getDenominazione_ita(), comuneDTO.getDenominazione_altra(), comuneDTO.getFlag_capoluogo(), comuneDTO.getCodice_belfiore(), comuneDTO.getLat(), comuneDTO.getLon(), comuneDTO.getSuperficie_kmq(), comuneDTO.getCodice_sovracomunale());
	}

}

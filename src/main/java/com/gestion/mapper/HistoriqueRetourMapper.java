package com.gestion.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.dto.HistoriqueRetourDTO;
import com.gestion.entities.HistoriqueRetour;

@Component
public class HistoriqueRetourMapper {

	@Autowired
	private ModelMapper modelMapper;

	public HistoriqueRetourDTO toDTO(HistoriqueRetour historiqueRetour) {
		return modelMapper.map(historiqueRetour, HistoriqueRetourDTO.class);
	}

	public HistoriqueRetour fromDTO(HistoriqueRetourDTO dto) {
		return modelMapper.map(dto, HistoriqueRetour.class);
	}

	public List<HistoriqueRetourDTO> toListDTO(List<HistoriqueRetour> listHistoriqueRetour) {
		return listHistoriqueRetour.stream()
				.map(hr -> modelMapper.map(hr, HistoriqueRetourDTO.class))
				.collect(Collectors.toList());
	}
}
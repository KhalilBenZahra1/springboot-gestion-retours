package com.gestion.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.dto.NonConformiteDTO;
import com.gestion.entities.NonConformite;

@Component
public class NonConformiteMapper {

	@Autowired
	private ModelMapper modelMapper;
	

	public NonConformiteDTO toDTO(NonConformite nonConformite) {
		return modelMapper.map(nonConformite, NonConformiteDTO.class);
	}

	public NonConformite fromDTO(NonConformiteDTO dto) {
		return modelMapper.map(dto, NonConformite.class);
	}

	public List<NonConformiteDTO> toListDTO(List<NonConformite> listNonConformite) {
		return listNonConformite.stream()
				.map(nc -> modelMapper.map(nc, NonConformiteDTO.class))
				.collect(Collectors.toList());
	}
}
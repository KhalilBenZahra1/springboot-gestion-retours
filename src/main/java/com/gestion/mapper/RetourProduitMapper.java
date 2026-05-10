package com.gestion.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.dto.RetourProduitDTO;
import com.gestion.entities.RetourProduit;

@Component
public class RetourProduitMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RetourProduitDTO toDTO(RetourProduit retourProduit) {
		return modelMapper.map(retourProduit, RetourProduitDTO.class);
	}

	public RetourProduit fromDTO(RetourProduitDTO dto) {
		return modelMapper.map(dto, RetourProduit.class);
	}

	public List<RetourProduitDTO> toListDTO(List<RetourProduit> listRetourProduit) {
		return listRetourProduit.stream()
				.map(rp -> modelMapper.map(rp, RetourProduitDTO.class))
				.collect(Collectors.toList());
	}
}
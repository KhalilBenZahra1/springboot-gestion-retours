package com.gestion.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.dto.UtilisateurDTO;
import com.gestion.entities.Utilisateur;

@Component
public class UtilisateurMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UtilisateurDTO toDTO(Utilisateur utilisateur) {
		return modelMapper.map(utilisateur, UtilisateurDTO.class);
	}

	public Utilisateur fromDTO(UtilisateurDTO dto) {
		return modelMapper.map(dto, Utilisateur.class);
	}

	public List<UtilisateurDTO> toListDTO(List<Utilisateur> listUtilisateur) {
		return listUtilisateur.stream()
				.map(utilisateur -> modelMapper.map(utilisateur, UtilisateurDTO.class))
				.collect(Collectors.toList());
	}
}
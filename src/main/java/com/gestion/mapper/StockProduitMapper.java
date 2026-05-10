package com.gestion.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.dto.StockProduitDTO;
import com.gestion.entities.StockProduit;

@Component
public class StockProduitMapper {

	@Autowired
	private ModelMapper modelMapper;

	public StockProduitDTO toDTO(StockProduit stockProduit) {
		return modelMapper.map(stockProduit, StockProduitDTO.class);
	}

	public StockProduit fromDTO(StockProduitDTO dto) {
		return modelMapper.map(dto, StockProduit.class);
	}

	public List<StockProduitDTO> toListDTO(List<StockProduit> listStockProduit) {
		return listStockProduit.stream()
				.map(sp -> modelMapper.map(sp, StockProduitDTO.class))
				.collect(Collectors.toList());
	}
}
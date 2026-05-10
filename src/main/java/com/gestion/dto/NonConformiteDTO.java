package com.gestion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NonConformiteDTO {

	private int id;
	private String description;
	private String gravite;
	private Date date;

	private RetourProduitDTO produit;
}
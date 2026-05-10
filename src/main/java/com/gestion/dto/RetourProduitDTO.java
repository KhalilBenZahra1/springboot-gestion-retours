package com.gestion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RetourProduitDTO {

	private int id;
	private String produit;
	private String client;
	private String raison;
	private String etatTraitement;
	private Date date;
}
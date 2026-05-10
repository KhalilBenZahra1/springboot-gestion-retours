package com.gestion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HistoriqueRetourDTO {

	private int id;
	private RetourProduitDTO retour;
	private String action;
	private UtilisateurDTO employe;
	private Date date;
}
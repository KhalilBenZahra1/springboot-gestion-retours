package com.gestion.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class RetourProduit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Le produit est obligatoire")
	private String produit;

	@NotBlank(message = "Le client est obligatoire")
	private String client;

	@NotBlank(message = "La raison est obligatoire")
	private String raison;

	private String etatTraitement = "EN_ATTENTE";

	@NotNull(message = "La date est obligatoire")
	private Date date;
}
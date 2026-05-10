package com.gestion.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class NonConformite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "La description est obligatoire")
	private String description;

	@NotBlank(message = "La gravité est obligatoire")
	private String gravite;

	@NotNull(message = "La date est obligatoire")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "idRetourProduit")
	private RetourProduit produit;
}
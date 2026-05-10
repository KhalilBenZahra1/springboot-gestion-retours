package com.gestion.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class HistoriqueRetour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "idRetourProduit")
	private RetourProduit retour;

	@NotBlank(message = "L'action est obligatoire")
	private String action;

	@ManyToOne
	@JoinColumn(name = "idUtilisateur")
	private Utilisateur employe;

	@NotNull(message = "La date est obligatoire")
	private Date date;
}
package com.gestion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Le nom est obligatoire")
	private String nom;

	@Email(message = "Email invalide")
	@NotBlank(message = "L'email est obligatoire")
	private String email;

	@NotBlank(message = "Le rôle est obligatoire")
	private String role;
}
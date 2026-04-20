package com.gestion.gestionretours.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
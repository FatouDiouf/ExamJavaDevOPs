package com.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nom;
    @Column(length = 150)
    private String prenom;
    @Column(length = 150)
    private int age;
    @Column(length = 150)
    private String adresse;
    @Column(length = 150)
    private String email;
    @Column(length = 150)
    private String telephone;
    @Column(length = 150)
    private String specialite;
    @Column(length = 150)
    private String niveauEtude;
    @Lob
    private String experienceProfessionnelle;
    @Column(length = 150)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection< Role > roles;
}

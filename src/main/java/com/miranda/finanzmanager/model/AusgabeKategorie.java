package com.miranda.finanzmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ausgaben_kategorie")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AusgabeKategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kategorie_name")
    private String kategorieName;
}

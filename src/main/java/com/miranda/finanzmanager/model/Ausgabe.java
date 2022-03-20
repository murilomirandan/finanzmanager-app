package com.miranda.finanzmanager.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="ausgaben")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ausgabe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "wert")
    private BigDecimal wert;

    @Column(name = "datum")
    private Date datum;

    @ManyToOne
    @JoinColumn(name = "kategorie_id", referencedColumnName = "id")
    private AusgabeKategorie kategorie;
}

package com.miranda.finanzmanager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "einnahmen")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Einnahme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "wert")
    private BigDecimal wert;

    @Column(name = "datum")
    private Date datum;
}

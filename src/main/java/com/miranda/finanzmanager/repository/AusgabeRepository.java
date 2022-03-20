package com.miranda.finanzmanager.repository;

import com.miranda.finanzmanager.model.Ausgabe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface AusgabeRepository extends JpaRepository<Ausgabe, Long> {
    Page<Ausgabe> findByBeschreibungContaining(String keyword, Pageable pageable);

    List<Ausgabe> findByDatumBetween(Date startDate, Date endDate);
    Page<Ausgabe> findByDatumBetween(Date startDate, Date endDate, Pageable pageable);

    void deleteById(Long id);

    Optional<Ausgabe> findById(Long id);

}

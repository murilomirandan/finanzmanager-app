package com.miranda.finanzmanager.repository;

import com.miranda.finanzmanager.model.Einnahme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface EinnahmeRepository extends JpaRepository<Einnahme, Long> {
    //    Page<Einnahme> findByBeschreibungContaining(Pageable paging);
    //    @Query("SELECT e FROM Einnahme e WHERE e.beschreibung LIKE CONCAT('%', :keyword, '%')")
    Page<Einnahme> findByBeschreibungContaining(String keyword, Pageable pageable);

    List<Einnahme> findByDatumBetween(Date startDate, Date endDate);
    Page<Einnahme> findByDatumBetween(Date startDate, Date endDate, Pageable pageable);

    void deleteById(Long id);

    Optional<Einnahme> findById(Long id);
}

package com.miranda.finanzmanager.service;

import com.miranda.finanzmanager.exception.AusgabeNotFoundException;
import com.miranda.finanzmanager.model.Ausgabe;
import com.miranda.finanzmanager.repository.AusgabeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AusgabeService {
    private final AusgabeRepository ausgabeRepository;

    @Autowired
    public AusgabeService(AusgabeRepository ausgabeRepository) {
        this.ausgabeRepository = ausgabeRepository;
    }

    public List<Ausgabe> findAllAusgaben() {
        return ausgabeRepository.findAll();
    }

    public List<Ausgabe> findAllAusgabenBetweenDaten(Date startDate, Date endDate) {
        return ausgabeRepository.findByDatumBetween(startDate, endDate);
    }

    public Ausgabe findAusgabeById(Long id){
        return ausgabeRepository.findById(id)
                .orElseThrow(() -> new AusgabeNotFoundException("Ausgabe by id " + id + " was not found"));
    }

    public Ausgabe addAusgabe(Ausgabe ausgabe){
        return ausgabeRepository.save(ausgabe);
    }

    public Ausgabe updateAusgabe(Ausgabe ausgabe){
        return ausgabeRepository.save(ausgabe);
    }

    public void deleteAusgabe(Long id){
        ausgabeRepository.deleteById(id);
    }

    public Page<Ausgabe> findAusgaben(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return ausgabeRepository.findAll(paging);
    }

    public Page<Ausgabe> findAusgabenByBeschreibung(String beschreibung, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return ausgabeRepository.findByBeschreibungContaining(beschreibung, paging);
    }

    public Page<Ausgabe> findAusgabenBetweenDaten(Date startDate, Date endDate, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return ausgabeRepository.findByDatumBetween(startDate, endDate, paging);
    }
}

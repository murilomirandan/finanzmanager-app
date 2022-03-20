package com.miranda.finanzmanager.service;

import com.miranda.finanzmanager.exception.EinnahmeNotFoundException;
import com.miranda.finanzmanager.model.Einnahme;
import com.miranda.finanzmanager.repository.EinnahmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EinnahmeService {
    private final EinnahmeRepository einnahmeRepository;

    @Autowired
    public EinnahmeService(EinnahmeRepository einnahmeRepository) {
        this.einnahmeRepository = einnahmeRepository;
    }

    public List<Einnahme> findAllEinnahmen() {
        return einnahmeRepository.findAll();
    }

    public List<Einnahme> findAllEinnahmenBetweenDaten(Date startDate, Date endDate) {
        return einnahmeRepository.findByDatumBetween(startDate, endDate);
    }

    public Einnahme findEinnahmeById(Long id){
        return einnahmeRepository.findById(id)
                .orElseThrow(() -> new EinnahmeNotFoundException("Einnahme by id " + id + " was not found"));
    }

    public Einnahme addEinnahme(Einnahme einnahme){
        return einnahmeRepository.save(einnahme);
    }

    public Einnahme updateEinnahme(Einnahme einnahme){
        return einnahmeRepository.save(einnahme);
    }

    public void deleteEinnahme(Long id){
        einnahmeRepository.deleteById(id);
    }

    public Page<Einnahme> findEinnahmen(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return einnahmeRepository.findAll(paging);
    }

    public Page<Einnahme> findEinnahmenByBeschreibung(String beschreibung, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return einnahmeRepository.findByBeschreibungContaining(beschreibung, paging);
    }

    public Page<Einnahme> findEinnahmenBetweenDaten(Date startDate, Date endDate, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Einnahme> pagedResult = einnahmeRepository.findByDatumBetween(startDate, endDate, paging);

//        System.out.println("page info - page number: " + pagedResult.getNumber() +
//                ", numberOfElements: " + pagedResult.getNumberOfElements() +
//                ", size: " + pagedResult.getSize() +
//                ", totalElements: " + pagedResult.getTotalElements() +
//                ", totalPages: " + pagedResult.getTotalPages());

        return pagedResult;
    }



}

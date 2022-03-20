package com.miranda.finanzmanager.controller;

import com.miranda.finanzmanager.model.Einnahme;
import com.miranda.finanzmanager.service.EinnahmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/einnahmen")
@CrossOrigin("http://localhost:4242")
public class EinnahmeController {
    private final EinnahmeService einnahmeService;

    @Autowired
    public EinnahmeController(EinnahmeService einnahmeService) {
        this.einnahmeService = einnahmeService;
    }

    @GetMapping("/all")
    // http://localhost:8082/einnahmen/all
    public ResponseEntity<List<Einnahme>> getAllEinnahmen(){
        List<Einnahme> einnahmen = einnahmeService.findAllEinnahmen();
        return new ResponseEntity<>(einnahmen, HttpStatus.OK);
    }

    @GetMapping("/date")
    // http://localhost:8082/einnahmen/date?startDatum=2021-01-01&endDatum=2021-06-31
    public ResponseEntity<List<Einnahme>> getAllEinnahmenBetweenDaten(
            @RequestParam Date startDatum,
            @RequestParam Date endDatum){
        List<Einnahme> einnahmen = einnahmeService.findAllEinnahmenBetweenDaten(startDatum, endDatum);
        return new ResponseEntity<>(einnahmen, HttpStatus.OK);
    }

    @GetMapping
    // http://localhost:8082/einnahmen
    public ResponseEntity<Page<Einnahme>> getEinnahmen(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Einnahme> einnahmen = einnahmeService.findEinnahmen(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(einnahmen, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search")
    // http://localhost:8082/einnahmen/search?beschreibung=bei
    public ResponseEntity<Page<Einnahme>> getEinnahmenByBeschreibung(
            @RequestParam String beschreibung,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Einnahme> einnahmen = einnahmeService.findEinnahmenByBeschreibung(beschreibung, pageNo, pageSize, sortBy);
        return new ResponseEntity<>(einnahmen, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/searchbydatum")
    // http://localhost:8082/einnahmen/searchbydatum?startDatum=2021-01-01&endDatum=2021-06-31
    public ResponseEntity<Page<Einnahme>> getEinnahmenBetweenDaten(
            @RequestParam Date startDatum,
            @RequestParam Date endDatum,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Einnahme> einnahmen = einnahmeService.findEinnahmenBetweenDaten(startDatum, endDatum, pageNo, pageSize, sortBy);
        return new ResponseEntity<>(einnahmen, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    // http://localhost:8082/einnahmen/2
    public ResponseEntity<Einnahme> getEinnahme(@PathVariable("id") Long id){
        Einnahme einnahme = einnahmeService.findEinnahmeById(id);
        return new ResponseEntity<>(einnahme, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Einnahme> addEinnahme(@RequestBody Einnahme einnahme){
        Einnahme newEinnahme = einnahmeService.addEinnahme(einnahme);
        return new ResponseEntity<>(newEinnahme, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Einnahme> updateEinnahme(@RequestBody Einnahme einnahme){
        Einnahme updateEinnahme = einnahmeService.updateEinnahme(einnahme);
        return new ResponseEntity<>(updateEinnahme, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEinnahme(@PathVariable("id") Long id){
        einnahmeService.deleteEinnahme(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

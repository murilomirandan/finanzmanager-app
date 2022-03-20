package com.miranda.finanzmanager.controller;

import com.miranda.finanzmanager.model.Ausgabe;
import com.miranda.finanzmanager.model.Einnahme;
import com.miranda.finanzmanager.service.AusgabeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/ausgaben")
@CrossOrigin("http://localhost:4200")
public class AusgabeController {
    private final AusgabeService ausgabeService;

    @Autowired
    public AusgabeController(AusgabeService ausgabeService) {
        this.ausgabeService = ausgabeService;
    }

    @GetMapping("/all")
    // http://localhost:8080/ausgaben/all
    public ResponseEntity<List<Ausgabe>> getAllAusgaben(){
        List<Ausgabe> ausgaben = ausgabeService.findAllAusgaben();
        return new ResponseEntity<>(ausgaben, HttpStatus.OK);
    }

    @GetMapping("/date")
    // http://localhost:8080/ausgaben/date?startDatum=2021-01-01&endDatum=2021-06-31
    public ResponseEntity<List<Ausgabe>> getAllAusgabenBetweenDaten(
            @RequestParam Date startDatum,
            @RequestParam Date endDatum){
        List<Ausgabe> ausgaben = ausgabeService.findAllAusgabenBetweenDaten(startDatum, endDatum);
        return new ResponseEntity<>(ausgaben, HttpStatus.OK);
    }

    @GetMapping
    // http://localhost:8080/ausgaben
    public ResponseEntity<Page<Ausgabe>> getAusgaben(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Ausgabe> ausgaben = ausgabeService.findAusgaben(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(ausgaben, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search")
    // http://localhost:8080/ausgaben/search?beschreibung=bei
    public ResponseEntity<Page<Ausgabe>> getAusgabenByBeschreibung(
            @RequestParam String beschreibung,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Ausgabe> ausgaben = ausgabeService.findAusgabenByBeschreibung(beschreibung, pageNo, pageSize, sortBy);
        return new ResponseEntity<>(ausgaben, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/searchbydatum")
    // http://localhost:8080/ausgaben/searchbydatum?startDatum=2021-01-01&endDatum=2021-06-31
    public ResponseEntity<Page<Ausgabe>> getAusgabenBetweenDaten(
            @RequestParam Date startDatum,
            @RequestParam Date endDatum,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<Ausgabe> ausgaben = ausgabeService.findAusgabenBetweenDaten(startDatum, endDatum, pageNo, pageSize, sortBy);
        return new ResponseEntity<>(ausgaben, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    // http://localhost:8080/ausgaben/2
    public ResponseEntity<Ausgabe> getAusgabe(@PathVariable("id") Long id){
        Ausgabe ausgabe = ausgabeService.findAusgabeById(id);
        return new ResponseEntity<>(ausgabe, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ausgabe> addAusgabe(@RequestBody Ausgabe ausgabe){
        Ausgabe newAusgabe = ausgabeService.addAusgabe(ausgabe);
        return new ResponseEntity<>(newAusgabe, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Ausgabe> updateAusgabe(@RequestBody Ausgabe ausgabe){
        Ausgabe updateAusgabe = ausgabeService.updateAusgabe(ausgabe);
        return new ResponseEntity<>(updateAusgabe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAusgabe(@PathVariable("id") Long id){
        ausgabeService.deleteAusgabe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.krylova.controller;

import com.krylova.entity.Country;
import com.krylova.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping("/api/countries")
    public ResponseEntity<?> create(@RequestBody Country country){
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/countries")
    public ResponseEntity<List<Country>> findAll(){
        final List<Country> countryList = countryService.findAll();
        return countryList != null && !countryList.isEmpty()
                ? new ResponseEntity<>(countryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/countries/{id}")
    public ResponseEntity<Optional<Country>> find(@PathVariable(name = "id") Long id){
        final Optional<Country> country = countryService.find(id);
        return country != null
                ? new ResponseEntity<>(country, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/countries/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable(name = "id") Long id, @RequestBody Country countryUpdate) {
        return countryService.find(id).map(country -> {
            country.setModels(countryUpdate.getModels());
            country.setName(countryUpdate.getName());
            countryService.update(country);
            return new ResponseEntity<>(country, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/countries/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") Long id) {
        return countryService.find(id).map(country -> {
            countryService.delete(country);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }





}

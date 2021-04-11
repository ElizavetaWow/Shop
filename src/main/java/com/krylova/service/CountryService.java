package com.krylova.service;

import com.krylova.entity.Country;
import com.krylova.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public void create(Country country){
        countryRepository.save(country);
    }

    public void update(Country country) { countryRepository.save(country); }

    public void delete(Country country) { countryRepository.delete(country); }

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Optional<Country> find(Long id){
        return countryRepository.findById(id);
    }
}

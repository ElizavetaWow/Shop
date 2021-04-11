package com.krylova.service;

import com.krylova.entity.Manufacturer;
import com.krylova.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public void create(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public void update(Manufacturer manufacturer) { manufacturerRepository.save(manufacturer); }

    public void delete(Manufacturer manufacturer) { manufacturerRepository.delete(manufacturer); }

    public List<Manufacturer> findAll(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> find(Long id){
        return manufacturerRepository.findById(id);
    }
}

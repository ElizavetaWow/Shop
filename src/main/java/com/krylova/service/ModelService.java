package com.krylova.service;

import com.krylova.entity.Model;
import com.krylova.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    public void create(Model model){
        modelRepository.save(model);
    }

    public void update(Model model) { modelRepository.save(model); }

    public void delete(Model model) { modelRepository.delete(model); }

    public List<Model> findAll(){
        return modelRepository.findAll();
    }

    public Optional<Model> find(Long id){
        return modelRepository.findById(id);
    }
}

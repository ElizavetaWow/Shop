package com.krylova.controller;

import com.krylova.entity.Model;
import com.krylova.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }

    @PostMapping("/api/models")
    public ResponseEntity<?> create(@RequestBody Model model){
        modelService.create(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/models")
    public ResponseEntity<List<Model>> findAll(){
        final List<Model> modelList = modelService.findAll();
        return modelList != null && !modelList.isEmpty()
                ? new ResponseEntity<>(modelList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/models/{id}")
    public ResponseEntity<Optional<Model>> find(@PathVariable(name = "id") Long id){
        final Optional<Model> model = modelService.find(id);
        return model != null
                ? new ResponseEntity<>(model, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/models/{id}")
    public ResponseEntity<?> updateModel(@PathVariable(name = "id") Long id, @RequestBody Model modelUpdate) {
        return modelService.find(id).map(model -> {
            model.setArticle(modelUpdate.getArticle());
            model.setCountry(modelUpdate.getCountry());
            model.setManufacturer(modelUpdate.getManufacturer());
            model.setName(modelUpdate.getName());
            model.setProducts(modelUpdate.getProducts());
            modelService.update(model);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/models/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable(name = "id") Long id) {
        return modelService.find(id).map(model -> {
            modelService.delete(model);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }





}

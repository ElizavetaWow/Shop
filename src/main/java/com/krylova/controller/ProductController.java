package com.krylova.controller;

import com.krylova.entity.Product;
import com.krylova.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<?> create(@RequestBody Product product){
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> findAll(){
        final List<Product> productList = productService.findAll();
        return productList != null && !productList.isEmpty()
                ? new ResponseEntity<>(productList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Optional<Product>> find(@PathVariable(name = "id") Long id){
        final Optional<Product> product = productService.find(id);
        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product productUpdate) {
        return productService.find(id).map(product -> {
            product.setAmount(productUpdate.getAmount());
            product.setColor(productUpdate.getColor());
            product.setModel(productUpdate.getModel());
            product.setPrice(productUpdate.getPrice());
            productService.update(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id) {
        return productService.find(id).map(product -> {
            productService.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }





}

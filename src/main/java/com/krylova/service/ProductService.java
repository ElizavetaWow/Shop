package com.krylova.service;

import com.krylova.entity.Product;
import com.krylova.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void create(Product product){
        productRepository.save(product);
    }

    public void update(Product product) { productRepository.save(product); }

    public void delete(Product product) { productRepository.delete(product); }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> find(Long id){
        return productRepository.findById(id);
    }
}

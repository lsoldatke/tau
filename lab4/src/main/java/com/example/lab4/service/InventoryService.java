package com.example.lab4.service;

import com.example.lab4.model.Product;
import com.example.lab4.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public Product getById(Long id) {
        return productRepository.getById(id);
    }
}

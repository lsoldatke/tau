package com.example.lab4.service;

import com.example.lab4.model.Product;
import com.example.lab4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class InventoryService {
    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such product"));
    }

    public int checkAvailability(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("No such product"));
        return product.getQuantity();
    }

    public void setQuantity(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("No such product"));
        product.setQuantity(quantity);
    }
}

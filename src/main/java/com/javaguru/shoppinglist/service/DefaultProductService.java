package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DefaultProductService implements ProductService {

    private final ProductRepository repository;
    private final ProductValidationService validationService;

    @Autowired
    public DefaultProductService(
            ProductRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    public Long createProduct(Product product) {
        validationService.validate(product);
        return repository.insert(product);
    }

    public Product findProductById(Long id) {
        return repository
                .findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found, id: " + id));
    }
}

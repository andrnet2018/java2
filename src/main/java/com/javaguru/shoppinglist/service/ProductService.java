package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.mapper.ProductConverter;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductValidationService validationService;
    private final ProductConverter productConverter;

    @Autowired
    public ProductService(ProductRepository repository,
                          ProductValidationService validationService,
                          ProductConverter productConverter) {
        this.repository = repository;
        this.validationService = validationService;
        this.productConverter = productConverter;
    }

    public Long createProduct(ProductDto productDto) {
        validationService.validate(productDto);
        Product product = productConverter.convert(productDto);
        return repository.save(product);
    }

    public ProductDto findProductById(Long id) {
        return repository.findProductById(id)
                .map(productConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

    public ProductDto findProductByName(String name) {
        return repository.findProductByName(name)
                .map(productConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Product not found, name: " + name));
    }

    public void updateProduct(ProductDto productDto) {
        Product product = productConverter.convert(productDto);
        repository.update(product);
    }

    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(productConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(Long id) {
        repository.findProductById(id)
                .ifPresent(repository::delete);
    }
}

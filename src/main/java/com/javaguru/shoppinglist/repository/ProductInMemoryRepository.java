package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductInMemoryRepository {

    private Long productIdSequence = 0L;
    private Map<Long, Product> products = new HashMap<>();

    public Map<Long, Product> getProducts() {
        return products;
    }

    public Product insert(Product product) {
        product.setId(productIdSequence);
        products.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public boolean existsByName(String name) {
        return products.values().stream().anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    public Optional<Product> findProductByName(String name) {
        return products.values().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}

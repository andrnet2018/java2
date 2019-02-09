package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {

    private Long PRODUCT_ID_SEQUENCE = 0L;
    private Map<Long, Product> products = new HashMap<>();

    public Product insert(Product product) {
        if (products.containsValue(product.getName())) {
            return null;
        } else {
            product.setId(PRODUCT_ID_SEQUENCE);
            products.put(PRODUCT_ID_SEQUENCE, product);
            PRODUCT_ID_SEQUENCE++;
            return product;
        }
    }

    public Product findProductById(Long id) {
        return products.get(id);
    }
}

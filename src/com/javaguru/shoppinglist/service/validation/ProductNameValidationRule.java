package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {


    @Override
    public void validate(Product product) {
        checkNotNull(product);
        checkNameNotNull(product);
        checkNameLenghtRange(product);
    }

    void checkNameNotNull(Product product) {
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must be not null.");
        }
    }

    void checkNameLenghtRange(Product product) {
        if ((product.getName().length() < 3) || (product.getName().length() > 32)) {
            throw new ProductValidationException("Product name length incorrect.");
        }
    }
}

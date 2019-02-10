package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductDiscountValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        checkNotMoreThanOneHundred(product);
        checkMoreThanZero(product);
    }

    void checkNotMoreThanOneHundred(Product product) {
        if (product.getDiscount().doubleValue() > 100) {
            throw new ProductValidationException("Product discount must be not more than 100%.");
        }
    }

    void checkMoreThanZero(Product product) {
        if (product.getDiscount().doubleValue() < 0) {
            throw new ProductValidationException("Product discount must be not less than 0%.");
        }
    }
}


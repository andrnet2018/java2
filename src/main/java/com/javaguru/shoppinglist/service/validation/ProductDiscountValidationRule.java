package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductDiscountValidationRule implements ProductValidationRule {

    public static final int MIN_DISCOUNT_SIZE = 0;
    public static final int MAX_DISCOUNT_SIZE = 100;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        checkNotMoreThanOneHundred(product);
        checkMoreThanZero(product);
    }

    void checkNotMoreThanOneHundred(Product product) {
        if (product.getDiscount().doubleValue() > MAX_DISCOUNT_SIZE) {
            throw new ProductValidationException("Product discount must be not more than 100%.");
        }
    }

    void checkMoreThanZero(Product product) {
        if (product.getDiscount().doubleValue() < MIN_DISCOUNT_SIZE) {
            throw new ProductValidationException("Product discount must be not less than 0%.");
        }
    }
}


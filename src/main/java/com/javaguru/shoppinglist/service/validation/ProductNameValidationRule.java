package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {

    public static final int MIN_PRODUCT_NAME_LENGTH = 3;
    public static final int MAX_PRODUCT_NAME_LENGTH = 32;

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
        if ((product.getName().length() < MIN_PRODUCT_NAME_LENGTH)
                || (product.getName().length() > MAX_PRODUCT_NAME_LENGTH)) {
            throw new ProductValidationException("Product name length incorrect.");
        }
    }
}

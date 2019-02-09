package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductPriceValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().doubleValue() < 0) {
            throw new ProductValidationException("Product price must be not less than 0.");
        }
    }
}

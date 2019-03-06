package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {

    public static final int MIN_PRICE = 0;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        checkIfPriceMoreThanZero(product);
    }

    void checkIfPriceMoreThanZero(Product product) {
        if (product.getPrice().doubleValue() <= MIN_PRICE) {
            throw new ProductValidationException("Product price must be more than 0.");
        }
    }
}

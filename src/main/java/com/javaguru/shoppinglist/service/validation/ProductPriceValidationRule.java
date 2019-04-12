package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {

    private static final int MIN_PRICE = 0;

    @Override
    public void validate(ProductDto productDto) {
        checkNotNull(productDto);
        checkIfPriceMoreThanZero(productDto);
    }

    private void checkIfPriceMoreThanZero(ProductDto productDto) {
        if (productDto.getPrice().doubleValue() <= MIN_PRICE) {
            throw new ProductValidationException("Product price must be more than 0.");
        }
    }
}

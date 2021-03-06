package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {

    private static final int MIN_PRODUCT_NAME_LENGTH = 3;
    private static final int MAX_PRODUCT_NAME_LENGTH = 32;

    @Override
    public void validate(ProductDto productDto) {
        checkNotNull(productDto);
        checkNameNotNull(productDto);
        checkNameLenghtRange(productDto);
    }

    private void checkNameNotNull(ProductDto productDto) {
        if (productDto.getName() == null) {
            throw new ProductValidationException("Product name must be not null.");
        }
    }

    private void checkNameLenghtRange(ProductDto productDto) {
        if ((productDto.getName().length() < MIN_PRODUCT_NAME_LENGTH)
                || (productDto.getName().length() > MAX_PRODUCT_NAME_LENGTH)) {
            throw new ProductValidationException("Product name length incorrect.");
        }
    }
}

package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;

public interface ProductValidationRule {

    void validate(ProductDto productDto);

    default void checkNotNull(ProductDto productDto) {
        if (productDto == null) {
            throw new ProductValidationException("Product must be not null");
        }
    }
}

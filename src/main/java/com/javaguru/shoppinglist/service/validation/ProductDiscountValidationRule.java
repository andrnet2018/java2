package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {

    public static final int MIN_DISCOUNT_SIZE = 0;
    public static final int MAX_DISCOUNT_SIZE = 100;
    public static final int MIN_PRICE_FOR_DISCOUNT_SIZE = 20;

    @Override
    public void validate(ProductDto productDto) {
        checkNotNull(productDto);
        checkNotMoreThanOneHundred(productDto);
        checkMoreThanZero(productDto);
        checkMoreThanMinPriceForDiscount(productDto);
    }

    void checkNotMoreThanOneHundred(ProductDto productDto) {
        if (productDto.getDiscount().doubleValue() > MAX_DISCOUNT_SIZE) {
            throw new ProductValidationException("Product discount must be not more than 100%.");
        }
    }

    void checkMoreThanZero(ProductDto productDto) {
        if (productDto.getDiscount().doubleValue() < MIN_DISCOUNT_SIZE) {
            throw new ProductValidationException("Product discount must be not less than 0%.");
        }
    }

    void checkMoreThanMinPriceForDiscount(ProductDto productDto) {
        if (productDto.getPrice().doubleValue() < MIN_PRICE_FOR_DISCOUNT_SIZE && productDto.getDiscount().doubleValue() > MIN_DISCOUNT_SIZE) {
            throw new ProductValidationException("Product discount must be 0% for this product price.");
        }
    }
}

package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {

    private Set<ProductNameValidationRule> validationRulesName = new HashSet<>();
    private Set<ProductDiscountValidationRule> validationRulesDisc = new HashSet<>();
    private Set<ProductPriceValidationRule> validationRulesPrice = new HashSet<>();


    public ProductValidationService() {
        validationRulesName.add(new ProductNameValidationRule());
        validationRulesDisc.add(new ProductDiscountValidationRule());
        validationRulesPrice.add(new ProductPriceValidationRule());
    }

    public void validate(Product product) {
        validationRulesName.forEach(s -> s.validate(product));
        validationRulesDisc.forEach(s -> s.validate(product));
        validationRulesPrice.forEach(s -> s.validate(product));
    }
}

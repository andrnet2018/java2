package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductPriceValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductPriceValidationRule victim = new ProductPriceValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductPriceValidationException() {
        input = product(new BigDecimal(0));

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product price must be more than 0.");
        victim.validate(input);
    }

    @Test
    public void shouldPriceValidateSuccess() {
        input = product(new BigDecimal(50));
        victim.validate(input);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }

}
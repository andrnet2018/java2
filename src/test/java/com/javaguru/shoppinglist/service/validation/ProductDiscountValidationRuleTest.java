package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductMaxDiscountValidationException() {
        input = product(new BigDecimal(20), new BigDecimal(101));

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount must be not more than 100%.");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductMinDiscountValidationException() {
        input = product(new BigDecimal(20), new BigDecimal(-10));

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount must be not less than 0%.");
        victim.validate(input);
    }

    @Test
    public void shouldDiscountValidateSuccess() {
        input = product(new BigDecimal(20), new BigDecimal(55));

        victim.validate(input);
    }

    @Test
    public void shouldDiscountToZeroIfPriceLessThanTwentyValidateSuccess() {
        input = product(new BigDecimal(19), new BigDecimal(55));

        assertEquals(input.getDiscount(), new BigDecimal(0));
    }

    private Product product(BigDecimal price, BigDecimal discount) {
        Product product = new Product();
        product.setPrice(price);
        product.setDiscount(discount);
        return product;
    }

}
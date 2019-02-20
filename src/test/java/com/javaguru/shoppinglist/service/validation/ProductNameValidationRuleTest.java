package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.sun.deploy.util.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.util.StringUtil;

import static org.junit.Assert.*;

public class ProductNameValidationRuleTest {
    public static final String TOO_SHORT_PRODUCT_NAME = "XX";
    public static final String VALIDATED_PRODUCT_NAME = "It's good name";
    public static final String TOO_LONG_PRODUCT_NAME = "Too long name 1hFY5Acy23iCwFBd4RgSwQpZhuvBwmu3RXHu";

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductNameValidationRule victim = new ProductNameValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product must be not null");
        victim.validate(null);
    }

    @Test
    public void shouldThrowProductNameValidationException() {
        input = product(null);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name must be not null.");
        victim.validate(input);
    }

    @Test
    public void shouldNameValidateSuccess() {
        input = product(VALIDATED_PRODUCT_NAME);

        victim.validate(input);
    }

    @Test
    public void shouldThrowProductNameMinLengthValidationException() {
        input = product(TOO_SHORT_PRODUCT_NAME);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name length incorrect.");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductNameMaxLengthValidationException() {
        input = product(TOO_LONG_PRODUCT_NAME);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name length incorrect.");
        victim.validate(input);
    }

    @Test
    public void shouldNameLenghtValidateSuccess() {
        input = product("Correct name length");

        victim.validate(input);
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

}
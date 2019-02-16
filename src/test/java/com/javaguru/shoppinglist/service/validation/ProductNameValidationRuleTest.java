package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.sun.deploy.util.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.util.StringUtil;

import static org.junit.Assert.*;

public class ProductNameValidationRuleTest {

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
        input = product("Some test name");

        victim.validate(input);
    }

    @Test
    public void shouldThrowProductNameMinLengthValidationException() {
        input = product("X"); // Too short product name

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name length incorrect.");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductNameMaxLengthValidationException() {
        input = product("e7RF1hFY5Acy23iCwFBd4RgSwQpZhuvBwmu3RXHu"); // length 40

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
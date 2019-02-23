package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {

    @Spy
    private ProductPriceValidationRule victim;

    private Product input;

    @Test
    public void shouldThrowProductPriceValidationException() {
        input = product(new BigDecimal(0));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price must be more than 0.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldPriceValidateSuccess() {
        input = product(new BigDecimal(50));
        victim.validate(input);
        verify(victim).checkNotNull(input);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }
}
package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {

    @Spy
    private ProductDiscountValidationRule victim;

    private Product input;

    @Test
    public void shouldThrowProductMaxDiscountValidationException() {
        input = product(new BigDecimal(20), new BigDecimal(101));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not more than 100%.");
    }

    @Test
    public void shouldThrowProductMinDiscountValidationException() {
        input = product(new BigDecimal(20), new BigDecimal(-10));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not less than 0%.");
    }

    @Test
    public void shouldDiscountValidateSuccess() {
        input = product(new BigDecimal(20), new BigDecimal(55));

        victim.validate(input);
    }

    @Test
    public void shouldDiscountToZeroIfPriceLessThanTwentyValidateSuccess() {
        input = product(new BigDecimal(19), new BigDecimal(55));

        assertThat(input.getDiscount()).isEqualTo(new BigDecimal(0));
    }

    private Product product(BigDecimal price, BigDecimal discount) {
        Product product = new Product();
        product.setPrice(price);
        product.setDiscount(discount);
        return product;
    }
}
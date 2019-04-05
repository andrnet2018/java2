package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
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

    private ProductDto input;

    @Test
    public void shouldThrowProductMaxDiscountValidationException() {
        input = productDto(new BigDecimal(20), new BigDecimal(101));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not more than 100%.");
    }

    @Test
    public void shouldThrowProductMinDiscountValidationException() {
        input = productDto(new BigDecimal(20), new BigDecimal(-10));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not less than 0%.");
    }

    @Test
    public void shouldThrowProductIfPriceIsLessTwentyDiscountValidationException() {
        input = productDto(new BigDecimal(19), new BigDecimal(55));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be 0% for this product price.");
    }

    @Test
    public void shouldDiscountValidateSuccess() {
        input = productDto(new BigDecimal(20), new BigDecimal(55));

        victim.validate(input);
    }

    private ProductDto productDto(BigDecimal price, BigDecimal discount) {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(price);
        productDto.setDiscount(discount);
        return productDto;
    }
}

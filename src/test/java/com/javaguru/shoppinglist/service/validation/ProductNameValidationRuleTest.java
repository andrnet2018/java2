package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {

    public static final String TOO_SHORT_PRODUCT_NAME = "XX";
    public static final String VALIDATED_PRODUCT_NAME = "It's name with correct lenght";
    public static final String TOO_LONG_PRODUCT_NAME =
            "Too long name 1hFY5Acy23iCwFBd4RgSwQpZhuvBwmu3RXHu";

    @Spy
    private ProductNameValidationRule victim;

    private ProductDto input;

    @Test
    public void shouldThrowProductNameValidationException() {
        input = productDto(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldNameLenghtValidateSuccess() {
        input = productDto(VALIDATED_PRODUCT_NAME);

        victim.validate(input);
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldThrowProductNameMinLengthValidationException() {
        input = productDto(TOO_SHORT_PRODUCT_NAME);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name length incorrect.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldThrowProductNameMaxLengthValidationException() {
        input = productDto(TOO_LONG_PRODUCT_NAME);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name length incorrect.");
        verify(victim).checkNotNull(input);
    }

    private ProductDto productDto(String name) {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        return productDto;
    }
}

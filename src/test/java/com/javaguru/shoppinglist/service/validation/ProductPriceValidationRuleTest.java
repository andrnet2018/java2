package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
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

    private ProductDto input;

    @Test
    public void shouldThrowProductPriceValidationException() {
        input = productDto(new BigDecimal(0));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price must be more than 0.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldPriceValidateSuccess() {
        input = productDto(new BigDecimal(50));
        victim.validate(input);
        verify(victim).checkNotNull(input);
    }

    private ProductDto productDto(BigDecimal price) {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(price);
        return productDto;
    }
}

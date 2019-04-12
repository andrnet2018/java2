package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationRuleTest {

    @Spy
    private ProductValidationRule victim;

    @Test
    public void shouldThrowValidationException() {
        assertThatThrownBy(() -> victim.checkNotNull(null))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product must be not null");
    }

    @Test
    public void shouldCheckNotNull() {
        ProductDto productDto = new ProductDto();

        victim.checkNotNull(productDto);
    }
}
